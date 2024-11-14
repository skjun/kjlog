package com.skjun.log.server.lib.dto.detail.mon;


/**
 * 大小枚举
 *
 * @Author xincheng.du
 * @Date 2023/7/6 11:42
 */
public enum SizeEnum {

    /**
     * 1KB = 1024B
     */
    KB(1024),

    /**
     * 1MB = 1024KB
     */
    MB(KB.size * 1024),

    /**
     * 1GB = 1024MB
     */
    GB(MB.size * 1024);

    /**
     * 1(K/M/G)B = ? B
     */
    private final long size;

    SizeEnum(long size) {
        this.size = size;
    }

    public long getSize() {
        return size;
    }
}

