package com.organics.dockercontrol.utils;


import com.organics.dockercontrol.config.ServiceConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by organics on 2016/3/12.
 */
public class Consts {
    public static final String UTF_8 = "UTF-8";

    public static final String CONTAINERS = "/containers/";

    public static final String STATS = "/stats";

    public static final String JSON = "json";

    public static final Map<String, String> DOCKER_HOST_MAP = new HashMap<>();

    static {
        ServiceConfig.getDockerHost().forEach(host -> DOCKER_HOST_MAP.put(host, "http://" + host));
    }

    private Consts() {
    }

    public static Map<String, String> getDockerHostMap() {
        return DOCKER_HOST_MAP;
    }

    public static String getAllContainerPath(String host) {
        return DOCKER_HOST_MAP.get(host) + CONTAINERS + JSON;
    }

    public static String getContainerStatsPath(String host, String containerId) {
        return DOCKER_HOST_MAP.get(host) + CONTAINERS + containerId + STATS;
    }

    // http://10.10.10.61:2372/containers/json
    public static String allContainerPathToHost(String path) {
        return path.substring(path.indexOf("//") + 2, path.indexOf("/", path.indexOf("//") + 2));
    }
}
