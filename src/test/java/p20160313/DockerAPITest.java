package p20160313;

import com.organics.utils.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by organics on 2016/3/13.
 */
public class DockerAPITest {
    public static void main(String[] args) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            // 创建httpget.
            HttpGet httpget = new HttpGet("http://192.168.1.108:6732/containers/47a46d9c1a6abb79936a6efd73ddd73dc38eeef930df143c0f2242bd55738628/stats");
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
                            System.out.println(bufr.readLine());
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
