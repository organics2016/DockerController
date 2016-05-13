package com.organics.dockercontrol.search;

import com.organics.dockercontrol.cache.ContainersCache;
import com.organics.dockercontrol.utils.Consts;
import com.organics.dockercontrol.utils.OutputUtils;
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

    public void search(final String path) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            // 创建httpget.
            HttpGet httpget = new HttpGet(path);
            httpget.setConfig(this.getRequestConfig());
            // 执行get请求.
            try (CloseableHttpResponse response = httpclient.execute(httpget)) {
                // 获取响应实体
                HttpEntity entity = response.getEntity();

                if (entity != null) {
                    this.searchProcess(path, entity, httpget);
                }
            } catch (SocketTimeoutException e) {
                String imageName = ContainersCache.getIdPathAndImage().remove(path);
                OutputUtils.save("Warning ! This service is dead the host : [" + Consts.allContainerPathToHost(path) + "], imageName : [" + imageName + "]");
                logger.warn("This service is dead the host : [{}] , imageName : [{}]", Consts.allContainerPathToHost(path), imageName);
            } catch (InterruptedException e) {
                logger.error("error :", e);
            }
        } catch (HttpHostConnectException e) {
            OutputUtils.save("Warning ! Connect to (" + path + ") failed");
            logger.warn("Connect to ({}) failed", path);
        } catch (IOException e) {
            logger.error("error :", e);
        }
    }

    private RequestConfig getRequestConfig() {
        return RequestConfig.custom().setSocketTimeout(5000).build();
    }

    protected abstract void searchProcess(final String path, final HttpEntity entity, HttpGet httpget) throws IOException, InterruptedException;
}
