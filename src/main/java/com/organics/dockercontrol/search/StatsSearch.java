package com.organics.dockercontrol.search;

import com.organics.dockercontrol.cache.ContainersCache;
import com.organics.dockercontrol.utils.Consts;
import org.apache.http.HttpEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TransferQueue;

/**
 * Created by wanghc on 2016/3/17.
 */
public class StatsSearch extends Search {

    private final TransferQueue<Map<String, String>> queue = ContainersCache.getQueue();

    @Override
    protected void searchProcess(final String path, final HttpEntity entity) throws IOException, InterruptedException {
        try (BufferedReader bufr = new BufferedReader(new InputStreamReader(entity.getContent(), Consts.UTF_8))) {
            Map<String, String> map = new HashMap<>();
            map.put(path, bufr.readLine());
            queue.put(map);
        }
    }
}