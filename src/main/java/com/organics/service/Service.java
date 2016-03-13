package com.organics.service;

import com.organics.cache.StatsCache;

import java.util.Map;
import java.util.concurrent.TransferQueue;

/**
 * Created by organics on 2016/3/12.
 */
public class Service {

    private final TransferQueue<Map<String, String>> queue;

    public Service() {
        queue = StatsCache.getQueue();
    }

    public void start() {
        new Thread(() -> {
            try {
                Map<String, String> map = queue.take();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
