package p20160313;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.organics.dockercontrol.utils.Consts;

import java.io.IOException;

/**
 * Created by organics on 2016/3/13.
 */
public class DockerAPITest {
    public static void main(String[] args) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            // 创建httpget.
//            HttpGet httpget = new HttpGet("http://10.10.10.61:2375/containers/c5b6684d559d51037791d1ae22a90b9be5abde0ac3c76f0f82519afa635431a7/stats");
            HttpGet httpget = new HttpGet("http://10.10.10.61:2375/containers/json");
            System.out.println("executing request " + httpget.getURI());
            // 执行get请求.
            try (CloseableHttpResponse response = httpclient.execute(httpget)) {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                // 打印响应状态
                System.out.println(response.getStatusLine());
                if (entity != null) {
//                    try (BufferedReader bufr = new BufferedReader(new InputStreamReader(entity.getContent(), Consts.UTF_8))) {
//                        while (true) {
//                            System.out.println(bufr.readLine());
//                        }
//                    }
                    System.out.println(EntityUtils.toString(entity));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
