package com.skjun.log.server.core.dto;

import com.alibaba.fastjson2.JSON;
import com.skjun.log.server.core.util.ZstdUtils;

import java.util.Map;

public class LogUpMessage {
    /**
     * tracerId
     */
    private String traceId;
    /**
     * 时间创建时间
     */
    private long createTime;
    /**
     * 日志内容
     */
    private String content;
    /**
     * info、error
     */
    private String logLevel;
    /**
     * 类名
     */
    private String className;
    /**
     * 方法名
     */
    private String methodName;
    /**
     * 线程名
     */
    private String threadName;

    /**
     * 标签map
     */
    private Map<String,Object> tagMap;


    private String appName;


    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    public Map<String, Object> getTagMap() {
        return tagMap;
    }

    public void setTagMap(Map<String, Object> tagMap) {
        this.tagMap = tagMap;
    }


    @Override
    public String toString() {
        return "RunLogMessage{" +
                "tracerId=" + traceId +
                ", createTime=" + createTime +
                ", content=" + content +
                ", logLevel='" + logLevel + '\'' +
                ", className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", threadName='" + threadName + '\'' +
                ", tagMap=" + tagMap +
                '}';
    }
}
