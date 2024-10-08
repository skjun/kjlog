package com.skjun.log.agent.core.util;

import com.skjun.log.agent.core.config.LogConfig;
import com.skjun.log.agent.core.exception.code.AgentErrorCode;
import com.skjun.log.agent.core.exception.base.AgentException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Properties;

public class PropertiesUtil {
   public static LogConfig inintLogConfig() throws AgentException {

        LogConfig config = new LogConfig();
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "kjLog.properties";
        Properties appProps = new Properties();
        try {
            appProps.load(Files.newInputStream(Paths.get(appConfigPath)));
            Properties catalogProps = new Properties();
            config.setType((String) appProps.get("type"));
            config.setLocalUdpPort(Integer.parseInt((String) appProps.get("localUdpPort")));
            String udpServers = (String) appProps.get("udpServers");
            if(!udpServers.isEmpty()){
                config.setUdpServers(Arrays.asList(udpServers.split(",")));
            }
            String redisServers = (String) appProps.get("redisServers");
            if(!redisServers.isEmpty()){
                config.setRedisServers(Arrays.asList(redisServers.split(",")));
            }
        } catch (IOException e) {
            throw new AgentException(AgentErrorCode.ConfigError);
        }

        return config;
    }
}
