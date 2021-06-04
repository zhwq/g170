package demo.socketio;
/*
- socket.close()
- socket.shutdownOutput()
- socket.shutdownInput()
- socket 输出流关闭
- socket 输入流关闭
 */

/*
Software caused connection abort: socket write error
 */

// - ExecutorService

/*
 * 数据流协议 数据长度 类型 数据
 * 半包 粘包 分包
 * 长连接 短连接
 */

// 长连接 短连接
// 心跳包 链路检测包
// 非阻塞ServerSocketChannel通信
// socket服务端接受信息后反馈给客户端

/*    while (true) {
//      a signed value Reads and returns one input byte
      byte b = dataInputStream.readByte();
      // Bytes for this operation are read from the contained input stream.
//      Reads four input bytes and returns an {@code int} value
      int len = dataInputStream.readInt();
      byte[] data = new byte[len - 5];
//      Reads some bytes from an input stream and stores them into the buffer array {@code b}.
//      The number of bytes read is equal to the length of {@code b}.
      dataInputStream.readFully(data);
      String input = new String(data);
    }*/
