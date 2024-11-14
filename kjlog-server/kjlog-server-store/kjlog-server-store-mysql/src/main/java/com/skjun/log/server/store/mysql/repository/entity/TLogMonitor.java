package com.skjun.log.server.store.mysql.repository.entity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Slf4j
@Entity
@Table(name = "t_log_monitor")
public class TLogMonitor implements Serializable {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "auto_id")
    private String autoId;
    @Column(name = "content")
    private String content;

    @Column(name = "mechine_id")
    private String mechineId;
    @Column(name = "log_time")
    private Date logTime;

}

