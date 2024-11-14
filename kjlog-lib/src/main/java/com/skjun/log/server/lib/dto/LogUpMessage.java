package com.skjun.log.server.lib.dto;

import com.alibaba.fastjson2.JSON;
import com.skjun.log.server.lib.util.ZstdUtils;

public class LogUpMessage<T> {

    public static final String LOG_TYPE = "log_type_message";
    public static final String MONITOR_YPE = "monitor_type_message";

    /**
     * tracerId
     */
    private String type;

    private T message;

    private String serviceCode;

    public void setMessage(T message) {
        this.message = message;
    }

    public void setType(String type) {
        this.type = type;
    }

    public T getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }

    public byte[] getUploadData(){
        return ZstdUtils.compress(JSON.toJSONBytes(this));
    }

    public String getServiceCode() {
        return serviceCode;
    }
    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }
}
