package com.skjun.log.agent.core.config;

import java.io.Serializable;
import java.util.List;

public class LogConfig implements Serializable {

    /**
     * 类型 ： redis , udp
     */
    private String type;

    private List<String> udpServers;
    private int localUdpPort;
    private List<String>  redisServers;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getUdpServers() {
        return udpServers;
    }

    public void setUdpServers(List<String> udpServers) {
        this.udpServers = udpServers;
    }

    public int getLocalUdpPort() {
        return localUdpPort;
    }

    public void setLocalUdpPort(int localUdpPort) {
        this.localUdpPort = localUdpPort;
    }

    public List<String> getRedisServers() {
        return redisServers;
    }

    public void setRedisServers(List<String> redisServers) {
        this.redisServers = redisServers;
    }
}
