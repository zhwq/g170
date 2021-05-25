package demo.socketio;

import lombok.SneakyThrows;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Server3 {

  public static void main(String[] args) throws IOException {
    System.out.println("server 3.0");
    int port = 8080;
    ServerSocket serverSocket = new ServerSocket(port);
    System.out.println("socket服务: localhost:" + port);
    while (true) {
      service(serverSocket);
    }
  }

  private static void service(ServerSocket serverSocket) throws IOException {
    Socket socket = serverSocket.accept();
    new Thread(new Runnable() {
      @SneakyThrows
      @Override
      public void run() {
        System.out.println(this);
        BufferedReader bufferedReader = null;
        try {
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
          if (bufferedReader != null) {
            bufferedReader.close();
          }
        }
      }
    }).start();
  }
}
