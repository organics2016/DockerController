package p20160317;

import com.organics.dockercontrol.utils.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by wanghc on 2016/3/17.
 */
public class HttpClient {

    public static void main(String[] args) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            // 创建httpget.
            HttpGet httpget = new HttpGet("http://10.10.10.61:2375/containers/c2f54351d32ba669eae5d8b9c9e1ff8439916449c616b008eca8cc82fbffa7a7/stats");
//            HttpGet httpget = new HttpGet("http://10.10.10.61:2375/containers/json");
//            httpget.setHeader("Connection", "close");
            RequestConfig config = RequestConfig.custom().setSocketTimeout(5000).build();

            httpget.setConfig(config);

            // 执行get请求.
            try (CloseableHttpResponse response = httpclient.execute(httpget)) {
                for (Header header : response.getAllHeaders()) {
                    System.out.println("name : " + header.getName() + "\t value : " + header.getValue());
                }
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                // 打印响应状态
//                httpget.abort();
                if (entity != null) {
                    try (BufferedReader bufr = new BufferedReader(new InputStreamReader(entity.getContent(), Consts.UTF_8))) {
                        while (true)
                            System.out.println(bufr.readLine());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
