package com.skjun.log.server.receive.udp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UdpConfig {
    @Value("${system.udp.port}")
    private int port;

    public int getPort() {
        return port;
    }
}
