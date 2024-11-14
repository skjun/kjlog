package com.skjun.log.agent.core.data;


import com.alibaba.fastjson2.JSON;
import com.skjun.log.agent.core.config.LogConfig;
import com.skjun.log.agent.core.exception.base.AgentException;
import com.skjun.log.agent.core.handler.AbstractUpHandler;
import com.skjun.log.agent.core.montor.HardWareUtil;
import com.skjun.log.agent.core.montor.SystemMonitorTask;
import com.skjun.log.agent.core.util.AsyncPool;
import com.skjun.log.agent.core.util.PropertiesUtil;
import com.skjun.log.server.lib.dto.LogUpMessage;
import com.skjun.log.server.lib.dto.TraceUpData;
import com.skjun.log.server.lib.dto.detail.MonitorMessage;
import com.skjun.log.server.lib.dto.detail.mon.*;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;


/**
 * udp对外发消息
 *
 * @author wuweifeng
 * @version 1.0
 * @date 2021-08-16
 */

public class DataSenderInit {
    private static final Logger log = Logger.getLogger(AbstractUpHandler.class.getName());

    /**
     * 本地队列满了后丢弃的数量
     */
    private static final AtomicLong FAIL_OFFER_COUNT = new AtomicLong();
    /**
     * 本地队列，已写入的总数量
     */
    private static final AtomicLong SUCCESS_OFFER_COUNT = new AtomicLong();
    /**
     * 本地logger日志队列，已写入的总数量
     */
    private static final AtomicLong SUCCESS_LOGGER_OFFER_COUNT = new AtomicLong();
    /**
     * 日志集中营，最多积压5万条
     */
    private static final LinkedBlockingQueue<LogUpMessage> logBeanQueue = new LinkedBlockingQueue<>(50000);


    private static LogConfig logConfig;
    private static AbstractUpHandler abstractUpHandler;

    static {

        log.info("DataSenderInit start");
        // 初始化
        try {
            logConfig = PropertiesUtil.inintLogConfig();
            if(logConfig.isSysMonitor()){
                sysMonitorWorker(logConfig.getDelayTime());
            }
        } catch (AgentException e) {
            e.printStackTrace();
            log.severe("DataSenderInit log config fail");
        }
        if(AbstractUpHandler.TypeUdp.equals(logConfig.getType())){
            abstractUpHandler = AbstractUpHandler.getUdpHandler();
        } else if (AbstractUpHandler.TypeRedis.equals(logConfig.getType())) {
            abstractUpHandler = AbstractUpHandler.getUdpHandler();
        }else {
            log.severe("DataSenderInit log type error , only support redis / udp");
        }
        if(abstractUpHandler != null){
            abstractUpHandler.init(logConfig);
            uploadToWorker();
        }
    }



    /**
     * 写入log队列
     */
    public static void offerLogger(LogUpMessage runLogMessage)  {
        //容量是否已满
        runLogMessage.setServiceCode(logConfig.getServiceCode());
        boolean success = logBeanQueue.offer(runLogMessage);
        if (!success) {
            long failCount = FAIL_OFFER_COUNT.incrementAndGet();
            if (failCount % 10 == 0) {
                log.info("用户Logger队列已满，当前丢弃的数量为: " + failCount);
            }
        } else {
            long successCount = SUCCESS_LOGGER_OFFER_COUNT.incrementAndGet();
            if (successCount % 10000 == 0) {
                log.info("用户Logger已产生数量：" + successCount + "，当前队列积压数量：" + logBeanQueue.size());
            }
        }
    }

    /**
     * 日志系统监控定时采集
     */
    public static void sysMonitorWorker(int delay) {
        //用户中途打的各日志

        SystemMonitorTask systemMonitorTask =  new SystemMonitorTask();

        Runnable task = () -> {
            // 这里是异步执行的任务

            CpuInfo cpuInfo = HardWareUtil.getCpuInfo();
            JvmInfo jvmInfo = HardWareUtil.getJvmInfo();
            SystemDetails systemInfo = HardWareUtil.getSystemInfo();
            MemoryInfo memoryInfo = HardWareUtil.getMemoryInfo(SizeEnum.GB);
            List<SysFile> sysFiles = HardWareUtil.getSysFiles();
            MonitorMessage monitorDo = new MonitorMessage(cpuInfo, jvmInfo, memoryInfo, sysFiles, systemInfo,logConfig.getMechineId());
            System.out.println("异步定时任务执行: " + JSON.toJSON(monitorDo));
            LogUpMessage<MonitorMessage> monitorMessageLogUpMessage = new LogUpMessage<>();
            monitorMessageLogUpMessage.setType(LogUpMessage.MONITOR_YPE);
            monitorMessageLogUpMessage.setMessage(monitorDo);
            offerLogger(monitorMessageLogUpMessage);

        };

        // 启动任务，初始延迟1秒，之后每3秒执行一次
        systemMonitorTask.startAsyncTask(task, 5, delay);
    }

    /**
     * 定时向worker发送
     */
    public static void uploadToWorker() {
        //用户中途打的各日志
        AsyncPool.asyncDo(() -> {
            while (true) {
                try {
                    //要么key达到500个，要么达到1秒，就汇总上报给worker一次
                    List<LogUpMessage> tempLogs = new ArrayList<>();
                    drain(logBeanQueue, tempLogs, 500, 1, TimeUnit.SECONDS);
                    if (tempLogs.isEmpty()) {
                        continue;
                    }
                    TraceUpData traceUpData = new TraceUpData();
                    traceUpData.setUpLogs(tempLogs);
                    abstractUpHandler.sendData(traceUpData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 提取队列中的    日志数量
     *
     * @param q
     * @param buffer
     * @param numElements
     * @param timeout
     * @param unit
     * @return
     * @param <E>
     * @throws Exception
     */
    public static <E> int drain(BlockingQueue<E> q, Collection<? super E> buffer, int numElements, long timeout, TimeUnit unit) throws Exception {
        if(buffer == null){
            throw new Exception("[Assertion failed] - the buffer argument cannot be null");
        }
        long deadline = System.nanoTime() + unit.toNanos(timeout);
        int added = 0;

        while(added < numElements) {
            added += q.drainTo(buffer, numElements - added);
            if (added < numElements) {
                E e = q.poll(deadline - System.nanoTime(), TimeUnit.NANOSECONDS);
                if (e == null) {
                    break;
                }
                buffer.add(e);
                ++added;
            }
        }
        return added;
    }

}
