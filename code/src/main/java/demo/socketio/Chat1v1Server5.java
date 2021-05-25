package demo.socketio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Chat1v1Server5 {
  public static void main(String[] args) throws IOException {
    System.out.println("server 5.0");
    int port = 8080;
    ServerSocket server = new ServerSocket(port);
    System.out.println("定长数据 socket服务: localhost:" + port);

    Socket socket = server.accept();
    try {
      InputStream is = socket.getInputStream();
      OutputStream os = socket.getOutputStream();
      DataInputStream dataInputStream = new DataInputStream(is);
      DataOutputStream dataOutputStream = new DataOutputStream(os);
      while (true) {
        String clientMsg = dataInputStream.readUTF();
        System.out.println("client: " + clientMsg);
        dataOutputStream.writeUTF("server echo ==>" + clientMsg);
      }
    } finally {
      socket.close();
    }
  }
}
