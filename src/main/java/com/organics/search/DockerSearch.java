package com.organics.search;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * Created by organics on 2016/3/12.
 */
public abstract class DockerSearch {

    public void search(final String path) {
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
                    this.parse(entity);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract void parse(HttpEntity entity);

    public static void main(String[] args) {
//        search(Consts.DOCKER_HOST + Consts.CONTAINERS + "762104eb472966354b5791852ccf8050d516675ee609c5245110e3a08b0a861b" + Consts.STATS);
    }
}
