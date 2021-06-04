package demo.socketio;

import java.io.*;
import java.net.Socket;

public class Client {
  public static void main(String[] args) throws IOException {
    int port = 8080;
    Socket socket = new Socket("localhost", port);
    BufferedWriter bufferedWriter = null;
    try {
      OutputStream outputStream = socket.getOutputStream();
      OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
      bufferedWriter = new BufferedWriter(outputStreamWriter);
      bufferedWriter.write("clent send ==>hello socket");
      bufferedWriter.flush();
    } finally {
      if (bufferedWriter != null) {
        bufferedWriter.close();
      }
//      if (socket != null) {
//        // 关闭socket的输出流
//        socket.shutdownOutput();
//      }
    }
  }
}
