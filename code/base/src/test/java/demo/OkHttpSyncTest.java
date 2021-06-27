package demo;

import homework.utils.JSONHelper;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.concurrent.TimeUnit;

@RunWith(JUnit4.class)
public class OkHttpSyncTest {

  @SneakyThrows
  @Test public void getEntinfo() {
    // "dc53d2e7-7488-434d-b44c-6ef7c595176d", "151318"
    // params.addHeader("Authorization","Bearer " + accessToken);
    final String accessToken = "8404bb49-ac2e-441d-a032-7e6553a0bc5c";
    final String url = "http://172.16.1.16:9999/ent/entinfo/get/151318";
    OkHttpClient client;
//    client = new OkHttpClient();
    client = new OkHttpClient.Builder()
      .readTimeout(5, TimeUnit.SECONDS)
      .build();
    Request request = new Request.Builder()
      .addHeader("Authorization", "Bearer " + accessToken)
      .url(url)
      .build();
    Response response = client.newCall(request).execute();
    String beanStr = response.body().string();
    System.out.println(beanStr);
    Entinfo entinfo = JSONHelper.getObject(beanStr, Entinfo.class);
   Entinfo.DataBean dataBean = entinfo.getData();
    System.out.println(dataBean.getCode());
    System.out.println(dataBean.getName());
    System.out.println(entinfo.getCode());
  }
}
