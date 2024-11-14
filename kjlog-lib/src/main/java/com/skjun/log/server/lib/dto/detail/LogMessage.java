package com.skjun.log.server.lib.dto.detail;


import java.util.Map;

public class LogMessage {
    /**
     * tracerId
     */
    private String tracerId;
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
    private String serviceCode;

    /**
     * 标签map
     */
    private Map<String,Object> tagMap;

    public String getTracerId() {
        return tracerId;
    }

    public void setTracerId(String tracerId) {
        this.tracerId = tracerId;
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

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    @Override
    public String toString() {
        return "RunLogMessage{" +
                "tracerId=" + tracerId +
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
