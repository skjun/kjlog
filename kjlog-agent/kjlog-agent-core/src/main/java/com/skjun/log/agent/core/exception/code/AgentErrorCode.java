package com.skjun.log.agent.core.exception.code;


import com.skjun.log.agent.core.exception.base.IAgentErrorCode;

public enum AgentErrorCode implements IAgentErrorCode {
    ConfigError("AE_001", "没有可用的密码机"),
    ConfigTypeError("AE_002", "不是有效的类型，仅支持 redis udp"),
    ;

    private final String code;
    private final String message;

    AgentErrorCode(final String code, final String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }
}
