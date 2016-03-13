package com.organics.cache;

import java.util.Map;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * Created by organics on 2016/3/13.
 */
public class StatsCache {

    private static final TransferQueue<Map<String, String>> queue = new LinkedTransferQueue<>();

    public static TransferQueue<Map<String, String>> getQueue() {
        return queue;
    }
}
