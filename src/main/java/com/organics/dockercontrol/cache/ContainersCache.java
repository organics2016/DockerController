package com.organics.dockercontrol.cache;

import com.organics.dockercontrol.entity.Container;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * Created by organics on 2016/3/13.
 */
public class ContainersCache {

    /**
     * 用于获取所有容器(包括停止)信息
     */
    private static final List<Container> containers = new CopyOnWriteArrayList<>();

    /**
     * 用于获取Docker容器的状态信息
     */
    private static final TransferQueue<Map<String, String>> queue = new LinkedTransferQueue<>();

    /**
     * 缓存容器ID的URL和images字符串,以便通过URL取到images
     */
    private static final Map<String, String> idPathAndImage = new ConcurrentHashMap<>();

    public static TransferQueue<Map<String, String>> getQueue() {
        return queue;
    }

    public static Map<String, String> getIdPathAndImage() {
        return idPathAndImage;
    }

    public static List<Container> getContainers() {
        return containers;
    }
}
