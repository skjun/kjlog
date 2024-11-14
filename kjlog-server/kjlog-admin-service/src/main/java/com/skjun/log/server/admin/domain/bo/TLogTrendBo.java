package com.skjun.log.server.admin.domain.bo;

import com.skjun.log.server.admin.domain.TLogTrend;
import com.skjun.log.server.admin.domain.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 日志趋势业务对象 t_log_trend
 *
 * @author skjun
 * @date 2024-10-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TLogTrendBo extends BaseEntity {

    /**
     * 
     */
    @NotBlank(message = "不能为空")
    private String autoId;

    /**
     * 应用编号
     */
    @NotBlank(message = "应用编号不能为空")
    private String appCode;

    /**
     * 备注
     */
    private String remark;

    /**
     * 数量
     */
    @NotNull(message = "数量不能为空")
    private Long count;

    /**
     * 时间
     */
    @NotNull(message = "时间不能为空")
    private Long logMinute;


}
