package com.skjun.log.server.store.mysql.repository;

import com.skjun.log.server.store.mysql.repository.entity.TLogEntity;
import com.skjun.log.server.store.mysql.repository.entity.TLogMonitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface TLogMonitorDao extends JpaRepository<TLogMonitor, String>, JpaSpecificationExecutor<TLogMonitor> {

}
