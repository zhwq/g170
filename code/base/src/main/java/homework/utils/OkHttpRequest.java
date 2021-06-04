package homework.utils;

import okhttp3.*;

import java.io.IOException;

/**
 *
 */
public class OkHttpRequest {
  final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
  OkHttpClient client = new OkHttpClient();
  public String get(final String url) throws IOException {
    Request request = new Request.Builder()
      .url(url)
      .build();
    Response response = client.newCall(request).execute();
    return response.body().string();
  }
  public String post(String url, String json) throws IOException {
    RequestBody body = RequestBody.create(JSON, json);
    Request request = new Request.Builder()
      .url(url).post(body).build();
    return client.newCall(request).execute().body().string();
  }
}
