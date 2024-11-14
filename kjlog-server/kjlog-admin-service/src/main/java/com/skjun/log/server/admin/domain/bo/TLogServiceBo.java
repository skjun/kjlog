package com.skjun.log.server.admin.domain.bo;

import com.skjun.log.server.admin.domain.TLogService;
import com.skjun.log.server.admin.domain.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * 应用服务业务对象 t_log_service
 *
 * @author skjun
 * @date 2024-10-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TLogServiceBo extends BaseEntity {

    /**
     * 
     */
    @NotBlank(message = "不能为空")
    private String autoId;

    /**
     * 应用名称
     */
    @NotBlank(message = "应用名称不能为空")
    private String serviceName;

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

    /**
     * 所属应用
     */
    @NotBlank(message = "所属应用不能为空")
    private String appCode;


}
