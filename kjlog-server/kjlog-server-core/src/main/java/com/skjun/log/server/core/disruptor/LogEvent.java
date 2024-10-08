package com.skjun.log.server.core.disruptor;

import com.skjun.log.server.core.dto.LogUpMessage;
import com.skjun.log.server.core.dto.TraceUpData;
import lombok.Data;

import java.io.Serializable;

@Data
public class LogEvent implements Serializable {
    private TraceUpData traceUpData;
    private long sequence;

}
