package com.organics.search;

import com.organics.cache.StatsCache;
import com.organics.utils.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TransferQueue;

/**
 * Created by organics on 2016/3/12.
 */
public class DockerSearchStats implements Runnable {

    private final String path;
    private final TransferQueue<Map<String, String>> queue;

    public DockerSearchStats(String path) {
        this.path = path;
        this.queue = StatsCache.getQueue();
    }

    private void search(final String path) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            // 创建httpget.
            HttpGet httpget = new HttpGet(path);
            System.out.println("executing request " + httpget.getURI());
            // 执行get请求.
            try (CloseableHttpResponse response = httpclient.execute(httpget)) {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                // 打印响应状态
                System.out.println(response.getStatusLine());
                if (entity != null) {
                    try (BufferedReader bufr = new BufferedReader(new InputStreamReader(entity.getContent(), Consts.UTF_8))) {
                        while (true) {
                            HashMap<String, String> map = new HashMap<>();
                            map.put(this.path, bufr.readLine());
                            queue.put(map);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        this.search(this.path);
    }
}
