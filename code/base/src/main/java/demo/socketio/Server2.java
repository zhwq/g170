package demo.socketio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Server2 {
  public static void main(String[] args) throws IOException {
    System.out.println("server 2.0");
    int port = 8080;
    ServerSocket serverSocket = new ServerSocket(port);
    System.out.println("socket服务: localhost:" + port);
    BufferedReader bufferedReader = null;
    Socket socket = null;
    try {
      socket = serverSocket.accept();
      InputStream is = socket.getInputStream();
      InputStreamReader inputStreamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
      bufferedReader = new BufferedReader(inputStreamReader);
      // 读取一行
      String input = bufferedReader.readLine();
      while (input != null && !Objects.equals("", input)) {
        System.out.println(input);
        input = bufferedReader.readLine();
      }
    } finally {
      if (socket != null) {
        socket.shutdownInput();
      }
    }
  }
}
