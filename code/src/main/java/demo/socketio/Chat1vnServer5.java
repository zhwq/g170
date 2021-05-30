package demo.socketio;

import lombok.SneakyThrows;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Chat1vnServer5 {

  public static void main(String[] args) throws IOException {
    System.out.println("server 5.0");
    int port = 8080;
    ServerSocket server = new ServerSocket(port, 5);
    System.out.println("定长数据 socket服务: localhost:" + port);

    while (true) {
      Socket socket = server.accept();
      System.out.println("connect: " + socket);
      new Thread(new Runnable() {
        @SneakyThrows
        @Override
        public void run() {
          try {
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            DataInputStream dataInputStream = new DataInputStream(is);
            DataOutputStream dataOutputStream = new DataOutputStream(os);
            while (true) {
              String clientMsg = dataInputStream.readUTF();
              System.out.println("client: " + clientMsg);
              dataOutputStream.writeUTF("echo " + clientMsg);
            }
          } finally {
            socket.close();
          }
        }
      }).start();
    }
  }
}
