package com.skjun.log.agent.core.util;

public class CommonUtil {

    /**
     *
     * @param name
     * @return
     */
    public static Object  getInstance(String name){
        try {
            // 类的完全限定名
            String className = "com.example.MyClass";
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
