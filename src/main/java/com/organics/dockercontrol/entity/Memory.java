package com.organics.dockercontrol.entity;

import java.io.Serializable;

/**
 * Created by wanghc on 2016/3/14.
 */
public class Memory implements Serializable {

    private long usage;

    private long max_usage;

    private long limit;

    public long getUsage() {
        return usage;
    }

    public void setUsage(long usage) {
        this.usage = usage;
    }

    public long getMax_usage() {
        return max_usage;
    }

    public void setMax_usage(long max_usage) {
        this.max_usage = max_usage;
    }

    public long getLimit() {
        return limit;
    }

    public void setLimit(long limit) {
        this.limit = limit;
    }
}
