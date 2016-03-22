package com.organics.dockercontrol.search;

import com.organics.dockercontrol.cache.ContainersCache;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;

/**
 * Created by organics on 2016/3/12.
 */
public abstract class Search {

    private static final Logger logger = LoggerFactory.getLogger(Search.class);

    public void search(final String path, final boolean isAbort) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            // 创建httpget.
            HttpGet httpget = new HttpGet(path);
            httpget.setConfig(this.getRequestConfig());
            // 执行get请求.
            try (CloseableHttpResponse response = httpclient.execute(httpget)) {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                if (isAbort) {
                    httpget.abort();
                }
                if (entity != null) {
                    this.searchProcess(path, entity);
                }
            } catch (SocketTimeoutException e) {
                String imageName = ContainersCache.getIdPathAndImage().remove(path);
                logger.warn("This service is dead imageName : [{}]", imageName);
            } catch (InterruptedException e) {
                logger.error("error :", e);
            }
        } catch (HttpHostConnectException e) {
            logger.warn("Connect to ({}) failed", path);
        } catch (IOException e) {
            logger.error("error :", e);
        }
    }

    public void search(final String path) {
        this.search(path, false);
    }

    private RequestConfig getRequestConfig() {
        return RequestConfig.custom().setSocketTimeout(5000).build();
    }

    protected abstract void searchProcess(final String path, final HttpEntity entity) throws IOException, InterruptedException;
}
