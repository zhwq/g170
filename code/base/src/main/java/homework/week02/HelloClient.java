package homework.week02;

import homework.utils.OkHttpRequest;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/*
工具类: https://square.github.io/okhttp/
<p/>
服务启动: java -jar gateway-server-0.0.1-SNAPSHOT.jar
提供服务接口地址: http://localhost:8088/api/hello
<p/>
写一段代码，使用 HttpClient 或 OkHttp 访问  http://localhost:8801 ，代码提交到 GitHub
 */
public class HelloClient {
  public static void main(String[] args) throws IOException {
    final String helloUrl = "http://localhost:8088/api/hello";
    OkHttpRequest okHttpRequest = new OkHttpRequest();
    System.out.println(okHttpRequest.get(helloUrl));
  }
}
