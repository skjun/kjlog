package com.skjun.log.server.store.mysql.handler;

import com.alibaba.fastjson2.JSON;
import com.skjun.log.server.core.common.SpringBeanUtil;
import com.skjun.log.server.core.deal.LogDataDealHandler;
import com.skjun.log.server.lib.dto.LogUpMessage;
import com.skjun.log.server.lib.dto.TraceUpData;
import com.skjun.log.server.lib.dto.detail.LogMessage;
import com.skjun.log.server.lib.dto.detail.MonitorMessage;
import com.skjun.log.server.store.mysql.repository.TLogInfoDao;
import com.skjun.log.server.store.mysql.repository.TLogMonitorDao;
import com.skjun.log.server.store.mysql.repository.entity.TLogEntity;
import com.skjun.log.server.store.mysql.repository.entity.TLogMonitor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MysqlLogDataHandler implements LogDataDealHandler {

    @Autowired
    public EntityManager entityManager;


    @Override
    public void init() {

    }

    @Override
    public void handler(TraceUpData traceUpData) {
        List<TLogEntity> tLogEntities = new ArrayList<>();
        List<TLogMonitor> monitorMessages = new ArrayList<>();
        traceUpData.getUpLogs().forEach(logUpMessage -> {
            if(LogUpMessage.LOG_TYPE.equals(logUpMessage.getType())){
                TLogEntity tLogEntity = new TLogEntity();
                BeanUtils.copyProperties(logUpMessage.getMessage(),tLogEntity);
                tLogEntity.setServiceCode(logUpMessage.getServiceCode());
                tLogEntities.add(tLogEntity);
            }else if(LogUpMessage.MONITOR_YPE.equals(logUpMessage.getType())){
                TLogMonitor logMonitor = new TLogMonitor();
                logMonitor.setContent(JSON.toJSONString(logUpMessage.getMessage()));
                logMonitor.setLogTime(new Date());
                logMonitor.setMechineId(logUpMessage.getServiceCode());
                monitorMessages.add(logMonitor);
            }
        });

        if(!tLogEntities.isEmpty()){
            TLogInfoDao bean = SpringBeanUtil.getBean(TLogInfoDao.class);
            bean.saveAll(tLogEntities);
        }
        if(!monitorMessages.isEmpty()){
            TLogMonitorDao bean = SpringBeanUtil.getBean(TLogMonitorDao.class);
            bean.saveAll(monitorMessages);
        }
    }

    @Override
    public void query() {

    }

}
