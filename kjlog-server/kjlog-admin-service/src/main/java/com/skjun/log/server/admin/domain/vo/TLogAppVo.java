package com.skjun.log.server.admin.domain.vo;

import com.skjun.log.server.admin.domain.TLogApp;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;



/**
 * 应用管理视图对象 t_log_app
 *
 * @author skjun
 * @date 2024-10-17
 */
@Data
public class TLogAppVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
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
