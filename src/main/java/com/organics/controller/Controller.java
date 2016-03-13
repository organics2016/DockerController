package com.organics.controller;

import com.alibaba.fastjson.JSONObject;
import com.organics.search.BlockDockerSearch;
import com.organics.search.NotBlockDockerSearch;
import com.organics.utils.Consts;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * Created by organics on 2016/3/13.
 */
public class Controller {

    private static final TransferQueue<String> queue = new LinkedTransferQueue<>();

    private NotBlockDockerSearch notBlockDockerSearch;
    private BlockDockerSearch blockDockerSearch;
    private ExecutorService service = Executors.newFixedThreadPool(2);

    public Controller() {
        notBlockDockerSearch = new NotBlockDockerSearch();
        blockDockerSearch = new BlockDockerSearch(queue);
    }

    public void searchStats() {
        ArrayList<String> list = new ArrayList<>();
        notBlockDockerSearch.search(Consts.getAllContainerPath());
        notBlockDockerSearch.getList().forEach(container ->
                list.add(JSONObject.parseObject(container).getString("Id")));
        for (Iterator<String> in = list.iterator(); in.hasNext(); ) {
            blockDockerSearch.search(Consts.getContainerStatsPath(in.next()));
        }
    }
}
