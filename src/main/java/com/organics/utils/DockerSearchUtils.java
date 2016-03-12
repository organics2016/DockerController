package com.organics.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by organics on 2016/3/12.
 */
public class DockerSearchUtils {

    public static String getSearch(final String path) {
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
                    // 打印响应内容
                    try (BufferedReader bufr = new BufferedReader(new InputStreamReader(entity.getContent(), "UTf-8"));) {
                        while (true) {
                            System.out.println(bufr.readLine());
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        getSearch(Consts.DOCKER_HOST + Consts.CONTAINERS + "762104eb472966354b5791852ccf8050d516675ee609c5245110e3a08b0a861b" + Consts.STATS);
    }
}
