package com.organics.search;

import com.organics.cache.ContainersCache;
import com.organics.entity.Container;
import com.organics.utils.Consts;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by organics on 2016/3/13.
 */
public class DockerSearchHandler {

    private static final ExecutorService executor = Executors.newCachedThreadPool();

    public void start() {
        // 获得容器集合
        List<Container> containerList = ContainersCache.getCache();
        // 构建容器ID集合
        List<String> containersId = new ArrayList<>();
        containerList.forEach(container -> containersId.add(container.getId()));
        // 开始获取状态
        containersId.forEach(containerId ->
                executor.submit(new DockerSearchStats(Consts.getContainerStatsPath(containerId))));
    }
}
