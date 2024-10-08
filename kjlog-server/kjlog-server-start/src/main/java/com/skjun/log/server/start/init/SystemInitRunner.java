package com.skjun.log.server.start.init;

import com.skjun.log.server.core.context.ServiceLoadContextAware;
import com.skjun.log.server.core.deal.LogDataDealHandler;
import com.skjun.log.server.core.inte.IDbStorageHandler;
import com.skjun.log.server.core.inte.ISearchStorageHandler;
import com.skjun.log.server.core.inte.IServerReceiveHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class SystemInitRunner implements CommandLineRunner {

    private final Logger logger = Logger.getLogger(SystemInitRunner.class.getName());


    @Autowired
    private ServiceLoadContextAware serviceLoadContextAware;


    @Override
    public void run(String... args) {
        logger.info("system init runner start");
        //加载接收服务包
        serviceLoadContextAware.getServerReceiveHandlerList().forEach(IServerReceiveHandler::init);

        serviceLoadContextAware.getiDbStorageHandlers().forEach(IDbStorageHandler::init);

        serviceLoadContextAware.getiSearchStorageHandlers().forEach(ISearchStorageHandler::init);

        serviceLoadContextAware.getLogDataDealHandlers().forEach(LogDataDealHandler::init);
        logger.info("system init runner end");
    }

}
