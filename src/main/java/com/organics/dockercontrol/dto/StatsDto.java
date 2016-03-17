package com.organics.dockercontrol.dto;

import java.util.Date;

/**
 * Created by wanghc on 2016/3/14.
 */
public class StatsDto {

    private String imageName;

    private Date date;

    private String cpu_usage;

    private String memory_usage;

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCpu_usage() {
        return cpu_usage;
    }

    public void setCpu_usage(String cpu_usage) {
        this.cpu_usage = cpu_usage + " %";
    }

    public String getMemory_usage() {
        return memory_usage;
    }

    public void setMemory_usage(String memory_usage) {
        this.memory_usage = memory_usage + " MB";
    }
}
