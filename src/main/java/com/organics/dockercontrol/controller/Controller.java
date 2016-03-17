package com.organics.dockercontrol.controller;

import com.organics.dockercontrol.search.ContainerSearch;
import com.organics.dockercontrol.search.Search;
import com.organics.dockercontrol.search.StatsSearch;
import com.organics.dockercontrol.service.DockerStatsListener;
import com.organics.dockercontrol.task.CheckContainersTask;
import com.organics.dockercontrol.task.SearchTask;

import java.util.Timer;


/**
 * Created by organics on 2016/3/13.
 */
public class Controller {

    private final Search containerSearch;
    private final Search statsSearch;

    public Controller() {
        containerSearch = new ContainerSearch();
        statsSearch = new StatsSearch();
    }

    public void searchStats() {
        new Timer().schedule(new CheckContainersTask(containerSearch), 0L, 10000L);

        new Timer().schedule(new SearchTask(statsSearch), 500L, 500L);

        new Thread(new DockerStatsListener()).start();
    }
}
