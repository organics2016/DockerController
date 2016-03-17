package com.organics.dockercontrol.entity;

import java.io.Serializable;

/**
 * Created by wanghc on 2016/3/14.
 */
public class CPU implements Serializable {

    private CPUUsage cpu_usage;

    private long system_cpu_usage;

    public CPUUsage getCpu_usage() {
        return cpu_usage;
    }

    public void setCpu_usage(CPUUsage cpu_usage) {
        this.cpu_usage = cpu_usage;
    }

    public long getSystem_cpu_usage() {
        return system_cpu_usage;
    }

    public void setSystem_cpu_usage(long system_cpu_usage) {
        this.system_cpu_usage = system_cpu_usage;
    }
}
