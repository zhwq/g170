package demo.netty;

import demo.netty.handler.DiscardServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class DiscardServer {
  private int port;

  public DiscardServer(int port) {
    this.port = port;
  }

  public void run() throws InterruptedException {
    // 1
    EventLoopGroup bossGroup = new NioEventLoopGroup();
    EventLoopGroup workGroup = new NioEventLoopGroup();

    try {
      // 2
      ServerBootstrap b = new ServerBootstrap();
      b.group(bossGroup, workGroup)
        // 3
        .channel(NioServerSocketChannel.class)
        // 4
        .childHandler(new ChannelInitializer<SocketChannel>() {
          @Override
          protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline().addLast(new DiscardServerHandler());
          }
        })
        // 5
        .option(ChannelOption.SO_BACKLOG, 128)
        // 6
        .childOption(ChannelOption.SO_KEEPALIVE, true);

      // bind & start to accept incoming connections
      // 7
      ChannelFuture f = b.bind(port).sync();
      // wait until the server socket is closed
      f.channel().closeFuture().sync();
    } finally {
      workGroup.shutdownGracefully();
      bossGroup.shutdownGracefully();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    int port = 8080;
    if (args.length > 0) {
      port = Integer.parseInt(args[0]);
    }
    new DiscardServer(port).run();
  }
}
