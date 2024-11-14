package com.skjun.log.server.admin.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.skjun.log.server.admin.domain.TLogInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;



/**
 * 日志查询视图对象 t_log_info
 *
 * @author skjun
 * @date 2024-10-17
 */
@Data
public class TLogInfoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
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
     * 耗时
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
