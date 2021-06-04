package demo.socketio;

import lombok.SneakyThrows;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server4 {

  public static void main(String[] args) throws IOException {
    System.out.println("server 4.0");
    int port = 8080;
    ServerSocket serverSocket = new ServerSocket(port);
    System.out.println("socket服务: localhost:" + port);
    // 多线程版本2
    ExecutorService executor = Executors.newFixedThreadPool(10);
    while (true) {
      Socket socket = serverSocket.accept();
      Runnable runnable = new Runnable() {
        @SneakyThrows
        @Override
        public void run() {
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
      };
      executor.submit(runnable);
    }
  }
}
