package com.skjun.log.server.core.deal;


import com.skjun.log.server.lib.dto.TraceUpData;

public interface LogDataAnalysisHandler {
    /**
     * 业务数据处理
     */
    public void handler(TraceUpData traceUpData);

    public void init();

}
