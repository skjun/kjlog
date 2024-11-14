package com.skjun.log.agent.collect.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import com.skjun.log.agent.core.data.DataSenderInit;
import com.skjun.log.server.lib.dto.LogUpMessage;
import com.skjun.log.server.lib.dto.detail.LogMessage;

public class KjLogBackAppender  extends AppenderBase<ILoggingEvent> {



    @Override
    public void append(ILoggingEvent event) {
        try {
            LogUpMessage logMessage = getLogMessage(event);
            DataSenderInit.offerLogger(logMessage);
        } catch (Exception e) {
            //do Nothing
            e.printStackTrace();
        }
    }


    /**
     * 转化为对象
     */
    private LogUpMessage getLogMessage(ILoggingEvent loggingEvent) {
        LogUpMessage<LogMessage> logUpMessage = new LogUpMessage<>();
        LogMessage logMessage = new LogMessage();
        //设置链路唯一id
//        logMessage.setTracerId(TracerHolder.getTracerId());
        logMessage.setClassName(loggingEvent.getLoggerName());
        logMessage.setThreadName(loggingEvent.getThreadName());

        String method = loggingEvent.getLoggerName();
        logMessage.setMethodName(method);
        logMessage.setLogLevel(loggingEvent.getLevel().toString());
        logMessage.setCreateTime(loggingEvent.getTimeStamp());
        logMessage.setContent(loggingEvent.getFormattedMessage());
        logUpMessage.setMessage(logMessage);
        logUpMessage.setType(LogUpMessage.LOG_TYPE);
        return logUpMessage;
    }


}
