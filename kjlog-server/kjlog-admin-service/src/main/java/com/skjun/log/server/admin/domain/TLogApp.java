package com.skjun.log.server.admin.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.skjun.log.server.admin.domain.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 应用管理对象 t_log_app
 *
 * @author skjun
 * @date 2024-10-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_log_app")
public class TLogApp extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId(value = "auto_id")
    private String autoId;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 应用编号
     */
    private String appCode;

    /**
     * 备注
     */
    private String remark;


}
