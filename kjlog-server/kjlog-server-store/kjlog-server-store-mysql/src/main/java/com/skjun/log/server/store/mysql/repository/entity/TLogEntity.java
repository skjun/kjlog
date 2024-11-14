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
@Table(name = "t_log_info")
public class TLogEntity implements Serializable {
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "auto_id")
    private String autoId;
    @Column(name = "log_level")
    private String logLevel;
    @Column(name = "trace_id")
    private String traceId;


    @Column(name = "class_name")
    private String className;

    @Column(name = "method_name")
    private String methodName;


    @Column(name = "thread_name")
    private String threadName;

        @Column(name = "content")
    private String content;

    @Column(name = "log_time")

    private Date logTime;

    @Column(name = "service_code")
    private String serviceCode;

}

