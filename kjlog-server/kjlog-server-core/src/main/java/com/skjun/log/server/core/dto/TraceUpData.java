package com.skjun.log.server.core.dto;



import java.io.Serializable;
import java.net.InetSocketAddress;
import java.util.List;

/**
 * 客户端-服务端彼此传输的数据
 * @author wuweifeng
 * @version 1.0
 * @date 2021-08-17
 */
public class TraceUpData implements Serializable {
            
    /**
     * span日志                  
     */
    List<LogUpMessage> upLogs;
   
    //发送地址（仅多播时候使用）
    private transient InetSocketAddress address;

    public InetSocketAddress getAddress() { return address; }

    public void setAddress(InetSocketAddress address) {
        this.address = address;
    }

    public void setUpLogs(List<LogUpMessage> upLogs) {
        this.upLogs = upLogs;
    }

    public List<LogUpMessage> getUpLogs() {
        return upLogs;
    }
}
