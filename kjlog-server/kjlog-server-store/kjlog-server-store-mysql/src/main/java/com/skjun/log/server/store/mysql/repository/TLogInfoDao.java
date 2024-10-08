package com.skjun.log.server.store.mysql.repository;

import com.skjun.log.server.store.mysql.repository.entity.TLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


public interface TLogInfoDao extends JpaRepository<TLogEntity, String>, JpaSpecificationExecutor<TLogEntity> {

}
