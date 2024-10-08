package com.skjun.log.agent.core.exception.base;

public class AgentException extends Exception  {
    String code;
    String message;

    public AgentException(String code) {
        this.code = code;
    }

    public AgentException(String code, String errorMsg) {
        this.code = code;
        this.message = errorMsg;
    }

    public AgentException(IAgentErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public String getCode() {
        return this.code;
    }


    public String getErrorMsg() {
        return this.message;
    }
}
