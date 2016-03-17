package com.organics.dockercontrol.task;

import com.organics.dockercontrol.cache.ContainersCache;
import com.organics.dockercontrol.search.Search;

import java.util.TimerTask;

/**
 * Created by wanghc on 2016/3/17.
 */
public class SearchTask extends TimerTask {

    private final Search search;

    public SearchTask(Search search) {
        this.search = search;
    }

    @Override
    public void run() {
        ContainersCache.getIdPathAndImage().forEach((idPath, image) -> {
            search.search(idPath, true);
        });
    }
}
