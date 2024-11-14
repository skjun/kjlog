package com.skjun.log.server.admin.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.skjun.log.server.admin.domain.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 应用服务对象 t_log_service
 *
 * @author skjun
 * @date 2024-10-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_log_service")
public class TLogService extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId(value = "auto_id")
    private String autoId;

    /**
     * 应用名称
     */
    private String serviceName;

    /**
     * 应用编号
     */
    private String serviceCode;

    /**
     * 备注
     */
    private String remark;

    /**
     * 所属应用
     */
    private String appCode;


}
