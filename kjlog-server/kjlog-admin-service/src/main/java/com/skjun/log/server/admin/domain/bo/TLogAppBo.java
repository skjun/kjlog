package com.skjun.log.server.admin.domain.bo;

import com.skjun.log.server.admin.domain.TLogApp;
import com.skjun.log.server.admin.domain.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotBlank;
/**
 * 应用管理业务对象 t_log_app
 *
 * @author skjun
 * @date 2024-10-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TLogAppBo extends BaseEntity {

    /**
     * 
     */
    @NotBlank(message = "不能为空")
    private String autoId;

    /**
     * 应用名称
     */
    @NotBlank(message = "应用名称不能为空")
    private String appName;

    /**
     * 应用编号
     */
    @NotBlank(message = "应用编号不能为空")
    private String appCode;

    /**
     * 备注
     */
    private String remark;


}
