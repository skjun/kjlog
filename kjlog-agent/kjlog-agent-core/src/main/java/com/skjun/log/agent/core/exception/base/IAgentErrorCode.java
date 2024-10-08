package com.skjun.log.agent.core.exception.base;

/**
 * 业务错误码
 *
 */
public interface IAgentErrorCode {
    /**
     * 获取错误码
     * @return
     */
    String getCode();

    String getMessage();
}
