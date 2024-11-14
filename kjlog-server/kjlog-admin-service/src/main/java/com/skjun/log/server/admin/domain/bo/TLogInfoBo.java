package com.skjun.log.server.admin.domain.bo;

import com.skjun.log.server.admin.domain.TLogInfo;
import com.skjun.log.server.admin.domain.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 日志查询业务对象 t_log_info
 *
 * @author skjun
 * @date 2024-10-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TLogInfoBo extends BaseEntity {

    /**
     * 
     */
    @NotBlank(message = "不能为空")
    private String autoId;

    /**
     * log级别
     */
    @NotBlank(message = "log级别不能为空")
    private String logLevel;

    /**
     * 追踪码
     */
    @NotBlank(message = "追踪码不能为空")
    private String traceId;

    /**
     * 执行类名
     */
    @NotBlank(message = "执行类名不能为空")
    private String className;

    /**
     * 方法
     */
    @NotBlank(message = "方法不能为空")
    private String methodName;

    /**
     * 线程
     */
    @NotBlank(message = "线程不能为空")
    private String threadName;

    /**
     * 内容
     */
    @NotBlank(message = "内容不能为空")
    private String content;

    /**
     * 耗时
     */
    @NotNull(message = "耗时不能为空")
    private Date logTime;

    /**
     * 应用编号
     */
    @NotBlank(message = "应用编号不能为空")
    private String serviceCode;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空")
    private String remark;


}
