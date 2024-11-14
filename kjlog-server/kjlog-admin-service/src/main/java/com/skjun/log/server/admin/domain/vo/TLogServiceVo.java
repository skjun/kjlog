package com.skjun.log.server.admin.domain.vo;

import com.skjun.log.server.admin.domain.TLogService;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;



/**
 * 应用服务视图对象 t_log_service
 *
 * @author skjun
 * @date 2024-10-17
 */
@Data
public class TLogServiceVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
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
