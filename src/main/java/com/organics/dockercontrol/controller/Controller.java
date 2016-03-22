package com.organics.dockercontrol.controller;

import com.organics.dockercontrol.cache.ContainersCache;
import com.organics.dockercontrol.entity.Container;
import com.organics.dockercontrol.search.ContainerSearch;
import com.organics.dockercontrol.search.Search;
import com.organics.dockercontrol.search.StatsSearch;
import com.organics.dockercontrol.service.DockerStatsListener;
import com.organics.dockercontrol.utils.Consts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by organics on 2016/3/13.
 */
public class Controller {
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);
    private final Search containerSearch;
    private final Search statsSearch;

    public Controller() {
        containerSearch = new ContainerSearch();
        statsSearch = new StatsSearch();
    }

    public void searchStats() {
        // 启动容器刷新任务
        containerRefreshTask();
        // 启动状态刷新任务
        statsQueueTask();
        // 启动状态输出线程
        new Thread(new DockerStatsListener()).start();
    }

    /**
     * 容器刷新任务
     * <p>
     * 每隔一段时间会对docker中所有容器的状态进行一次检查,并将容器状态的 查询路径 与 镜像名称 的映射存入缓存
     */
    private void containerRefreshTask() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                List<Container> list = ContainersCache.getContainers();
                list.clear();

                Consts.getDockerHostMap().keySet().forEach(host ->
                        containerSearch.search(Consts.getAllContainerPath(host)));

                Map<String, String> idPathAndImage = ContainersCache.getIdPathAndImage();
                //
                if (idPathAndImage.size() <= 0) {
                    list.forEach(container ->
                            idPathAndImage.put(Consts.getContainerStatsPath(container.getHost(), container.getId()), container.getImage()));
                    return;
                }

                list.forEach(container -> {
                    if (idPathAndImage.get(Consts.getContainerStatsPath(container.getHost(), container.getId())) == null) {
                        logger.warn("This Service is start the host [{}] , imageName : [{}]", container.getHost(), container.getImage());
                        idPathAndImage.put(Consts.getContainerStatsPath(container.getHost(), container.getId()), container.getImage());
                    }
                });
            }
        }, 0L, 10000L);
    }

    /**
     * 状态刷新任务
     * <p>
     * 从缓存中获取时时更新的 查询路径进行状态查询
     */
    private void statsQueueTask() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                ContainersCache.getIdPathAndImage().keySet().forEach(idPath -> statsSearch.search(idPath, true));
            }
        }, 500L, 500L);
    }
}
