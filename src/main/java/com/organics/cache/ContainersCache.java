package com.organics.cache;

import com.organics.entity.Container;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by organics on 2016/3/13.
 */
public class ContainersCache {

    private ContainersCache() {
    }

    private static List<Container> cache = new CopyOnWriteArrayList<>();

    public static List<Container> getCache() {
        return cache;
    }

    public static void setCache(List<Container> cache) {
        ContainersCache.cache = cache;
    }
}
