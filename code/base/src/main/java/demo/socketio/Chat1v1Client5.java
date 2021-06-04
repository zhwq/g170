package demo.socketio;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Chat1v1Client5 {
  public static void main(String[] args) throws IOException, InterruptedException {
    int port = 8080;
    Socket socket = new Socket("localhost.", port);
    try {
      OutputStream os = socket.getOutputStream();
      InputStream is = socket.getInputStream();
      DataInputStream dataInputStream = new DataInputStream(is);
      DataOutputStream dataOutputStream = new DataOutputStream(os);
      Scanner scanner = new Scanner(System.in);
//      System.out.println("当前分隔符:" + scanner.delimiter());
      while (true) {
        String input = scanner.nextLine();
        dataOutputStream.writeUTF(input);
        String receivedMsg = dataInputStream.readUTF();
        System.out.println("server: " + receivedMsg);
      }
    } finally {
      socket.close();
    }
  }
}
