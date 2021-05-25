package demo.socketio;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client2 {
  public static void main(String[] args) throws IOException {
    int port = 8080;
    Socket socket = new Socket("localhost", port);
    BufferedWriter bufferedWriter;
    try {
      OutputStream outputStream = socket.getOutputStream();
      OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
      bufferedWriter = new BufferedWriter(outputStreamWriter);
      // 从stdin读取输入
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
      while (true) {
        String input = reader.readLine();
        bufferedWriter.write(input);
        // 行结束标识
        bufferedWriter.write("\n");
        bufferedWriter.flush();
      }
      // @TODO: 没有输入判断自行中止
    } finally {
      socket.shutdownOutput();
    }
  }
}
