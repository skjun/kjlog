package com.skjun.log.agent.core.util;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.skjun.log.agent.core.config.LogConfig;
import com.skjun.log.agent.core.exception.code.AgentErrorCode;
import com.skjun.log.agent.core.exception.base.AgentException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Properties;

public class PropertiesUtil {
   public static LogConfig inintLogConfig() throws AgentException {

        LogConfig config = new LogConfig();
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "kjLog.json";
        Properties appProps = new Properties();
        try {
            File file = new File(appConfigPath);
            FileInputStream fileInputStream = new FileInputStream(file);
            config = JSON.parseObject(fileInputStream,LogConfig.class);
//            appProps.load(Files.newInputStream(Paths.get(appConfigPath)));
//            Properties catalogProps = new Properties();
//            config.setType((String) appProps.get("type"));
//            config.setLocalUdpPort(Integer.parseInt((String) appProps.get("localUdpPort")));
//            config.setSysMonitor(Boolean.parseBoolean((String) appProps.get("sysMonitor")));
//            config.setDelayTime(Integer.parseInt((String) appProps.get("delayTime")));
//            config.setServiceCode((String) appProps.get("serviceCode"));
//            config.setMechineId((String) appProps.get("mechineId"));
//            String udpServers = (String) appProps.get("udpServers");
//            if(!config.get.isEmpty()){
//                config.setUdpServers(Arrays.asList(udpServers.split(",")));
//            }
//            String redisServers = (String) appProps.get("redisServers");
//            if(!redisServers.isEmpty()){
//                config.setRedisServers(Arrays.asList(redisServers.split(",")));
//            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new AgentException(AgentErrorCode.ConfigError);
        }

        return config;
    }
}
