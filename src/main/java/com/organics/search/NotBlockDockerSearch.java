package com.organics.search;

import com.alibaba.fastjson.JSONArray;
import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by organics on 2016/3/13.
 */
public class NotBlockDockerSearch extends DockerSearch{

    private final List<String> list = new CopyOnWriteArrayList<>();

    @Override
    protected void parse(HttpEntity entity) {
        try {
            list.clear();
            String data = EntityUtils.toString(entity);
            if (data.startsWith("{")) {
                list.add(data);
            } else if (data.startsWith("[")) {
                JSONArray.parseArray(data).forEach(jsonObj -> list.add(jsonObj.toString()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getList() {
        return list;
    }
}
