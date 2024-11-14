package com.skjun.log.agent.collect.log4j2;

import com.skjun.log.agent.core.data.DataSenderInit;
import com.skjun.log.server.lib.dto.LogUpMessage;
import com.skjun.log.server.lib.dto.detail.LogMessage;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.Property;

import java.io.Serializable;

public class KjLog4j2Appender extends AbstractAppender {

    protected KjLog4j2Appender(String name, Filter filter, Layout<? extends Serializable> layout, boolean ignoreExceptions, Property[] properties) {
        super(name, filter, layout, ignoreExceptions, properties);
        System.out.println("--------");
    }

    @Override
    public void append(LogEvent event) {
        try {
            LogUpMessage logMessage = getLogMessage(event);
            DataSenderInit.offerLogger(logMessage);
        } catch (Exception e) {
            //do Nothing
        }
    }


    /**
     * 转化为对象
     */
    private LogUpMessage getLogMessage(LogEvent loggingEvent) {

        LogUpMessage<LogMessage> logUpMessage = new LogUpMessage<>();
        LogMessage logMessage = new LogMessage();
        //设置链路唯一id
//        logMessage.setTracerId(TracerHolder.getTracerId());
        logMessage.setClassName(loggingEvent.getLoggerName());
        logMessage.setThreadName(loggingEvent.getThreadName());

        String method = loggingEvent.getLoggerName();
        logMessage.setMethodName(method);
        logMessage.setLogLevel(loggingEvent.getLevel().toString());
        logMessage.setCreateTime(loggingEvent.getNanoTime());
        logMessage.setContent(loggingEvent.getMessage().getFormattedMessage());
        logUpMessage.setMessage(logMessage);
        logUpMessage.setType(LogUpMessage.LOG_TYPE);
        return logUpMessage;
    }
}
