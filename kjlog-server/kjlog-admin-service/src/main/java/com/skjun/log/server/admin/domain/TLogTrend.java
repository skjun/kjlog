package com.skjun.log.server.admin.domain;

import com.baomidou.mybatisplus.annotation.*;
import com.skjun.log.server.admin.domain.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 日志趋势对象 t_log_trend
 *
 * @author skjun
 * @date 2024-10-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_log_trend")
public class TLogTrend extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    @TableId(value = "auto_id")
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
