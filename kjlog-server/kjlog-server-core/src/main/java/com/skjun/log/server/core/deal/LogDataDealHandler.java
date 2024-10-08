package com.skjun.log.server.core.deal;

import com.skjun.log.server.core.dto.LogUpMessage;
import com.skjun.log.server.core.dto.TraceUpData;

public interface LogDataDealHandler {
    /**
     * 业务数据处理
     */
    public void handler(TraceUpData traceUpData);

    public void init();

}
