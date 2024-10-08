package com.skjun.log.agent.core.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 异步线程池AsyncPool
 */
public class AsyncPool {
    private static final ExecutorService threadPoolExecutor = Executors.newCachedThreadPool();
    public static void asyncDo(Runnable runnable) {
        threadPoolExecutor.submit(runnable);
    }
    public static void shutDown() {
        threadPoolExecutor.shutdown();
    }

}
