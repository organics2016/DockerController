package com.organics.dockercontrol.config;

import java.util.Arrays;
import java.util.List;

/**
 * Created by organics on 2016/3/12.
 */
public class ServiceConfig {

    // 10.10.10.61:2375;10.10.9.103:2375;
    public static List<String> getDockerHost() {
        String dockerHost = System.getenv("docker.host");
        if (dockerHost == null || dockerHost.equals("")) {
            throw new RuntimeException("docker host error");
        }
        return Arrays.asList(dockerHost.split(";"));
    }
}
