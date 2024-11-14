package com.skjun.log.agent.core.handler;

import com.skjun.log.agent.core.config.LogConfig;
import com.skjun.log.server.lib.dto.TraceUpData;

import java.util.logging.Logger;

public abstract class AbstractUpHandler {

    private static final Logger log = Logger.getLogger(AbstractUpHandler.class.getName());

    public static String TypeRedis= "redis";
    public static String TypeUdp= "udp";

    public  static  AbstractUpHandler getUdpHandler(){
        log.info("create abstract udp handler");
        return (AbstractUpHandler) getInstance("com.skjun.log.agent.upload.udp.AbstractUpUdpHandler");
    }

    public  static  AbstractUpHandler getRedisHandler(){
        log.info("create abstract udp handler");
        return (AbstractUpHandler) getInstance("com.skjun.log.agent.upload.redis.AbstractUpRedisHandler");
    }

    public   abstract void init(LogConfig logConfig);

    public abstract void sendData(TraceUpData data) throws InterruptedException;

    /**
     */
    private static Object getInstance(String className){
        try {
            // 类的完全限定名
            // 使用Class.forName加载类
            Class<?> clazz = Class.forName(className);
            // 通过clazz可以获取类的信息或者创建实例
            // 比如创建实例
            return clazz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
