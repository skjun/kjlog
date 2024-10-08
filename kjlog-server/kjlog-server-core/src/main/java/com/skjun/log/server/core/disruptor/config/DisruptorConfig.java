package com.skjun.log.server.core.disruptor.config;

import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.skjun.log.server.core.disruptor.LogEvent;
import com.skjun.log.server.core.disruptor.handler.LogEventHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

@Configuration
public class DisruptorConfig {
    @Bean
    public RingBuffer<LogEvent> disruptor() {


//        // 配置Disruptor
        Disruptor<LogEvent> disruptor = new Disruptor<>(LogEvent::new, 1024, Executors.defaultThreadFactory());
        //3 创建含有10个消费者的数组:
        LogEventHandler[] consumers = new LogEventHandler[10];
        for(int i = 0; i < consumers.length; i++) {
            consumers[i] = new LogEventHandler();
        }
        disruptor.handleEventsWithWorkerPool(consumers);

        disruptor.start();

        return disruptor.getRingBuffer();
    }

    static class EventExceptionHandler implements ExceptionHandler<LogEvent> {

        public void handleEventException(Throwable ex, long sequence, LogEvent event) {
        }

        public void handleOnStartException(Throwable ex) {
        }

        public void handleOnShutdownException(Throwable ex) {
        }

    }
}


