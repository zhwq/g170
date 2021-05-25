package demo.socketio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
  public static void main(String[] args) throws IOException {
    System.out.println("server 1.0");
    int port = 8080;
    ServerSocket serverSocket = new ServerSocket(port);
    System.out.println("socket服务: localhost:" + port);
    BufferedReader bufferedReader = null;
    Socket socket = null;
    try {
      socket = serverSocket.accept();
      InputStream is = socket.getInputStream();
      InputStreamReader inputStreamReader = new InputStreamReader(is);
      bufferedReader = new BufferedReader(inputStreamReader);
      // 读取一行
      String input = bufferedReader.readLine();
      System.out.println(input);
    } finally {
      if (bufferedReader != null) {
        bufferedReader.close();
      }
//      if (socket != null) {
//        socket.shutdownInput();
//      }
    }
  }
}
