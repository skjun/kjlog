package com.skjun.log.server.core.context;

import com.skjun.log.server.core.deal.LogDataDealHandler;
import com.skjun.log.server.core.inte.IDbStorageHandler;
import com.skjun.log.server.core.inte.ISearchStorageHandler;
import com.skjun.log.server.core.inte.IServerReceiveHandler;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceLoadContextAware implements ApplicationContextAware {

    private List<IServerReceiveHandler> serverReceiveHandlerList;
    private List<IDbStorageHandler> iDbStorageHandlers;
    private List<ISearchStorageHandler> iSearchStorageHandlers;


    private List<LogDataDealHandler> logDataDealHandlers;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        serverReceiveHandlerList = new ArrayList<>(applicationContext.getBeansOfType(IServerReceiveHandler.class).values());
        iDbStorageHandlers = new ArrayList<>(applicationContext.getBeansOfType(IDbStorageHandler.class).values());
        iSearchStorageHandlers = new ArrayList<>(applicationContext.getBeansOfType(ISearchStorageHandler.class).values());
        logDataDealHandlers = new ArrayList<>(applicationContext.getBeansOfType(LogDataDealHandler.class).values());
    }

    public List<LogDataDealHandler> getLogDataDealHandlers() {
        return logDataDealHandlers;
    }
    public List<IServerReceiveHandler> getServerReceiveHandlerList() {
        return serverReceiveHandlerList;
    }

    public List<IDbStorageHandler> getiDbStorageHandlers() {
        return iDbStorageHandlers;
    }


    public List<ISearchStorageHandler> getiSearchStorageHandlers() {
        return iSearchStorageHandlers;
    }


}
