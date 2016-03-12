package com.organics.search;

import com.organics.utils.Consts;
import com.organics.utils.DockerSearchUtils;

/**
 * Created by organics on 2016/3/12.
 */
public interface DockerSearch {

    default String searchAll() {
        return DockerSearchUtils.getSearch(Consts.CONTAINER_ALL_PATH);
    }

    default String searchStats(String id) {
        final String path = Consts.DOCKER_HOST + Consts.CONTAINERS + id + Consts.STATS;
        return DockerSearchUtils.getSearch(path);
    }
}
