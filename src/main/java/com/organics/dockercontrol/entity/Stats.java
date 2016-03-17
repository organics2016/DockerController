package com.organics.dockercontrol.entity;

import java.io.Serializable;

/**
 * Created by wanghc on 2016/3/14.
 */
public class Stats implements Serializable {

    private String read;

    private NetWork network;

    private CPU cpu_stats;

    private Memory memory_stats;

    public String getRead() {
        return read;
    }

    public void setRead(String read) {
        this.read = read;
    }

    public NetWork getNetwork() {
        return network;
    }

    public void setNetwork(NetWork network) {
        this.network = network;
    }

    public CPU getCpu_stats() {
        return cpu_stats;
    }

    public void setCpu_stats(CPU cpu_stats) {
        this.cpu_stats = cpu_stats;
    }

    public Memory getMemory_stats() {
        return memory_stats;
    }

    public void setMemory_stats(Memory memory_stats) {
        this.memory_stats = memory_stats;
    }
}
