package com.organics.search;

import com.organics.utils.Consts;
import org.apache.http.HttpEntity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TransferQueue;

/**
 * Created by organics on 2016/3/13.
 */
public class BlockDockerSearch extends DockerSearch {

    private final TransferQueue<String> queue;

    public BlockDockerSearch(TransferQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    protected void parse(HttpEntity entity) {
        try (BufferedReader bufr = new BufferedReader(new InputStreamReader(entity.getContent(), Consts.UTF_8))) {
            queue.put(bufr.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
