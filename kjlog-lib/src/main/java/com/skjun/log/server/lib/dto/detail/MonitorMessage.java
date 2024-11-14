package com.skjun.log.server.lib.dto.detail;

import com.skjun.log.server.lib.dto.detail.mon.*;

import java.io.Serializable;
import java.util.List;

public class MonitorMessage implements Serializable {
    private CpuInfo cpuInfo;
    private JvmInfo jvmInfo;

    private MemoryInfo memoryInfo;

    private List<SysFile> sysFile;

    private SystemDetails systemDetails;

    private String mechineId;

    public MonitorMessage(CpuInfo cpuInfo, JvmInfo jvmInfo, MemoryInfo memoryInfo, List<SysFile> sysFile, SystemDetails systemDetails, String mechineId) {
        this.cpuInfo = cpuInfo;
        this.jvmInfo = jvmInfo;
        this.memoryInfo = memoryInfo;
        this.sysFile = sysFile;
        this.systemDetails = systemDetails;
        this.mechineId = mechineId;
    }

    public CpuInfo getCpuInfo() {
        return cpuInfo;
    }
    public void setCpuInfo(CpuInfo cpuInfo) {
        this.cpuInfo = cpuInfo;
    }
    public JvmInfo getJvmInfo() {
        return jvmInfo;
    }
    public void setJvmInfo(JvmInfo jvmInfo) {
        this.jvmInfo = jvmInfo;
    }

    public MemoryInfo getMemoryInfo() {
        return memoryInfo;
    }
    public void setMemoryInfo(MemoryInfo memoryInfo) {
        this.memoryInfo = memoryInfo;
    }

    public List<SysFile> getSysFile() {
        return sysFile;
    }
    public void setSysFile(List<SysFile> sysFile) {
        this.sysFile = sysFile;
    }

    public SystemDetails getSystemDetails() {
        return systemDetails;
    }
    public void setSystemDetails(SystemDetails systemDetails) {
        this.systemDetails = systemDetails;
    }

    public String getMechineId() {
        return mechineId;
    }

    public void setMechineId(String mechineId) {
        this.mechineId = mechineId;
    }
}
