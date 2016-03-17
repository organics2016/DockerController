package com.organics.dockercontrol.config;

/**
 * Created by organics on 2016/3/12.
 */
public class ServiceConfig {
    public static String getDockerHost() {
        String dockerHost = System.getenv("docker.host");
        if (dockerHost == null || dockerHost.equals("")) {
            throw new RuntimeException("docker host error");
        }
        return dockerHost;
    }
}
