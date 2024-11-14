package com.skjun.log.server.core.deal;


import com.skjun.log.server.lib.dto.TraceUpData;

import java.util.List;

public interface LogDataDealHandler {
    /**
     * 业务数据处理
     */
    public void handler(TraceUpData traceUpData);


    public void query();

    public void init();

}
