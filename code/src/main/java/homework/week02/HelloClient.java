package homework.week02;

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
  private OkHttpClient client = new OkHttpClient();
  private String run(String url) throws IOException {
    Request request = new Request.Builder()
      .url(url)
      .build();
    try (Response response = client.newCall(request).execute()) {
      return response.body().string();
    }
  }
  public static void main(String[] args) throws IOException {
    final String helloUrl = "http://localhost:8088/api/hello";
    HelloClient helloClient = new HelloClient();
    String resp = helloClient.run(helloUrl);
    System.out.println(resp);
  }
}
