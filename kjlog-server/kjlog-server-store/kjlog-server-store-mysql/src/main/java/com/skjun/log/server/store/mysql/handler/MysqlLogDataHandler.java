package com.skjun.log.server.store.mysql.handler;

import com.skjun.log.server.core.common.SpringBeanUtil;
import com.skjun.log.server.core.context.ServiceLoadContextAware;
import com.skjun.log.server.core.deal.LogDataDealHandler;
import com.skjun.log.server.core.dto.TraceUpData;
import com.skjun.log.server.store.mysql.repository.TLogInfoDao;
import com.skjun.log.server.store.mysql.repository.entity.TLogEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class MysqlLogDataHandler implements LogDataDealHandler {

    @Autowired
    public EntityManager entityManager;


    @Override
    public void init() {

    }

    @Override
    public void handler(TraceUpData traceUpData) {
        System.out.println("mysql -------------"+traceUpData.getUpLogs().toString());

        List<TLogEntity> tLogEntities = new ArrayList<>();

        traceUpData.getUpLogs().forEach(logUpMessage -> {
            TLogEntity tLogEntity = new TLogEntity();
            BeanUtils.copyProperties(logUpMessage,tLogEntity);
            tLogEntities.add(tLogEntity);
        });

        TLogInfoDao bean = SpringBeanUtil.getBean(TLogInfoDao.class);

        bean.saveAll(tLogEntities);
    }

}
