package com.skjun.log.agent.core.util;


import com.skjun.log.agent.core.dto.RunLogMessage;
import com.skjun.log.agent.core.dto.TraceLogMessage;

/**
 * className：TraceLogMessageFactory
 * description： TODO
 * time：2020-05-13.14:04
 *
 * @author Tank
 * @version 1.0.0
 */
public class TraceLogMessageFactory<T> {


    public static RunLogMessage getLogMessage(String appName, String env, String message, long time) {
        RunLogMessage logMessage = new RunLogMessage();
        logMessage.setServerName(IpGetter.CURRENT_IP);
        logMessage.setAppName(appName);
        logMessage.setEnv(env);
        logMessage.setContent(message);
        logMessage.setDtTime(time);
//        logMessage.setTraceId(TraceId.logTraceID.get());
        return logMessage;
    }

    public static String packageMessage(String message, Object[] args) {
        StringBuilder builder = new StringBuilder(128);
        builder.append(message);
        for (Object arg : args) {
            builder.append("\n").append(arg);
        }
        return builder.toString();
    }

}
