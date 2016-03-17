package com.organics.dockercontrol.entity;

import java.io.Serializable;

/**
 * Created by wanghc on 2016/3/14.
 */
public class CPUUsage  implements Serializable {

    private long total_usage;

    public long getTotal_usage() {
        return total_usage;
    }

    public void setTotal_usage(long total_usage) {
        this.total_usage = total_usage;
    }
}
