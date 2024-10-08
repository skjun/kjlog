package com.skjun.log.server.core.disruptor.handler;

import com.lmax.disruptor.WorkHandler;
import com.skjun.log.server.core.common.SpringBeanUtil;
import com.skjun.log.server.core.context.ServiceLoadContextAware;
import com.skjun.log.server.core.disruptor.LogEvent;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

@Log4j2
public class LogEventHandler implements WorkHandler<LogEvent> {

    private  ServiceLoadContextAware serviceLoadContextAware;
    @Override
    public void onEvent(LogEvent logEvent) {
        if(serviceLoadContextAware == null){
            serviceLoadContextAware = SpringBeanUtil.getBean(ServiceLoadContextAware.class);
        }

        System.out.println(Thread.currentThread().getName()+logEvent.getTraceUpData().toString());

        serviceLoadContextAware.getLogDataDealHandlers().forEach(logDataDealHandler -> logDataDealHandler.handler(logEvent.getTraceUpData()));
    }
}
