package com.skjun.log.server.admin.domain.vo;

import com.skjun.log.server.admin.domain.TLogTrend;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;



/**
 * 日志趋势视图对象 t_log_trend
 *
 * @author skjun
 * @date 2024-10-17
 */
@Data
public class TLogTrendVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    private String autoId;

    /**
     * 应用编号
     */
    private String appCode;

    /**
     * 备注
     */
    private String remark;

    /**
     * 数量
     */
    private Long count;

    /**
     * 时间
     */
    private Long logMinute;


}
