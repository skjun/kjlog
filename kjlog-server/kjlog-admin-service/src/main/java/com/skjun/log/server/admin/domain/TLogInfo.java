package com.skjun.log.server.admin.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.skjun.log.server.admin.domain.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * 日志查询对象 t_log_info
 *
 * @author skjun
 * @date 2024-10-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_log_info")
public class TLogInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId(value = "auto_id")
    private String autoId;

    /**
     * log级别
     */
    private String logLevel;

    /**
     * 追踪码
     */
    private String traceId;

    /**
     * 执行类名
     */
    private String className;

    /**
     * 方法
     */
    private String methodName;

    /**
     * 线程
     */
    private String threadName;

    /**
     * 内容
     */
    private String content;

    /**
     * 时间
     */
    private Date logTime;

    /**
     * 应用编号
     */
    private String serviceCode;

    /**
     * 备注
     */
    private String remark;


}
