package com.organics.utils;

import com.organics.config.ServiceConfig;

/**
 * Created by organics on 2016/3/12.
 */
public class Consts {
    public static final String DOCKER_HOST = "http://" + ServiceConfig.getDockerHost();
    public static final String CONTAINER_ALL_PATH = DOCKER_HOST + "/containers/json";
    public static final String CONTAINERS = "/containers/";

    public static final String STATS = "/stats";
    private Consts() {
    }
}
