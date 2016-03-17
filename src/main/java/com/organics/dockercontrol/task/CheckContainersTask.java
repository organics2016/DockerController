package com.organics.dockercontrol.task;

import com.organics.dockercontrol.cache.ContainersCache;
import com.organics.dockercontrol.entity.Container;
import com.organics.dockercontrol.search.Search;
import com.organics.dockercontrol.utils.Consts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.TimerTask;

/**
 * Created by wanghc on 2016/3/17.
 */
public class CheckContainersTask extends TimerTask {

    private static final Logger logger = LoggerFactory.getLogger(CheckContainersTask.class);

    private final Search search;

    public CheckContainersTask(Search search) {
        this.search = search;
    }

    @Override
    public void run() {
        List<Container> list = ContainersCache.getContainers();
        list.clear();

        search.search(Consts.getAllContainerPath());

        Map<String, String> map = ContainersCache.getIdPathAndImage();
        //
        if (map.size() <= 0) {
            list.forEach(container ->
                    map.put(Consts.getContainerStatsPath(container.getId()), container.getImage()));
            return;
        }

        list.forEach(container -> {
            if (map.get(Consts.getContainerStatsPath(container.getId())) == null) {
                logger.warn("This Service is start imageName : [{}]", container.getImage());
                map.put(Consts.getContainerStatsPath(container.getId()), container.getImage());
            }
        });
    }
}
