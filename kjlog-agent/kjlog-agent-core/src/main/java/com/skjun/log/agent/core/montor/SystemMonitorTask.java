package com.skjun.log.agent.core.montor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SystemMonitorTask {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    public void startAsyncTask(Runnable task, long initialDelay, long period) {
        scheduler.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);
    }

    public void getSystemInfoLog(){

    }

}
