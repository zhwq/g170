package demo.netty;

import demo.netty.handler.DiscardServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

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
        .handler(new LoggingHandler(LogLevel.INFO))
        // 4
        .childHandler(new ChannelInitializer<SocketChannel>() {
          @Override
          protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
              //  it is the handler's responsibility to release any reference-counted object passed to the handler
              @Override
              public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//    super.channelRead(ctx, msg);
//    Discard the received data silently.
                ((ByteBuf) msg).release();
              }

              @Override
              public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//    super.exceptionCaught(ctx, cause);
//    Close the connection when an exception is raised.
                cause.printStackTrace();
                ctx.close();
              }
            });
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
