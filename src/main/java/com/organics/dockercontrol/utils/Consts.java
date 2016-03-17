package com.organics.dockercontrol.utils;


import com.organics.dockercontrol.config.ServiceConfig;

/**
 * Created by organics on 2016/3/12.
 */
public class Consts {
    public static final String UTF_8 = "UTF-8";
    public static final String DOCKER_HOST = "http://" + ServiceConfig.getDockerHost();
    public static final String CONTAINERS = "/containers/";

    public static final String STATS = "/stats";
    public static final String JSON = "json";

    private Consts() {
    }

    public static String getAllContainerPath() {
        return DOCKER_HOST + CONTAINERS + JSON;
    }

    public static String getContainerStatsPath(String containerId) {
        return DOCKER_HOST + CONTAINERS + containerId + STATS;
    }
}
