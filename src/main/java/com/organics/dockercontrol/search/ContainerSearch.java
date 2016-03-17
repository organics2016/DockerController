package com.organics.dockercontrol.search;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.organics.dockercontrol.cache.ContainersCache;
import com.organics.dockercontrol.entity.Container;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by wanghc on 2016/3/17.
 */
public class ContainerSearch extends Search {

    @Override
    protected void searchProcess(final String path, final HttpEntity entity) throws IOException {
        JSONArray containersJson = JSONArray.parseArray(EntityUtils.toString(entity));
        containersJson.forEach(containerJson ->
                ContainersCache.getContainers().add(JSONObject.parseObject(containerJson.toString(), Container.class)));
    }
}
