package com.organics.controller;

import com.alibaba.fastjson.JSONArray;
import com.organics.cache.ContainersCache;
import com.organics.entity.Container;
import com.organics.search.DockerSearch;
import com.organics.search.DockerSearchHandler;
import com.organics.service.Service;
import com.organics.utils.Consts;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by organics on 2016/3/13.
 */
public class Controller {
    private DockerSearch search;
    private DockerSearchHandler handler;
    private Service service;

    public Controller() {
        search = new DockerSearch();
        handler = new DockerSearchHandler();
        service = new Service();
    }

    public void searchStats() {
        ContainersCache.setCache(JSONArray.parseArray(search.search(Consts.getAllContainerPath()), Container.class));
        handler.start();
        service.start();
    }

    public static void main(String[] args) {
        Controller c = new Controller();
        c.searchStats();
    }
}
