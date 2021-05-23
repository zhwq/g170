package homework.week03;

import homework.utils.OkHttpRequest;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.ReferenceCountUtil;

import java.io.IOException;

/**
 * 后端服务1 http://localhost:8801/api/hello
 * 代理服务 http://localhost:8080/api/hello
 * <p/>
 * λ curl http://localhost:8080/api/hello
 * hello world
 */
public class NettyHttpServer1 {
  private int port;

  public NettyHttpServer1(int port) {
    this.port = port;
  }
  // 调用服务
  public String service(String uri) throws IOException {
    OkHttpRequest okHttpRequest = new OkHttpRequest();
    return okHttpRequest.get(uri);
  }

  public void run(String proxyServer) throws InterruptedException {
    // 1
    EventLoopGroup bossGroup = new NioEventLoopGroup();
    EventLoopGroup workGroup = new NioEventLoopGroup();
    try {
      ServerBootstrap b = new ServerBootstrap();

      b.option(ChannelOption.SO_BACKLOG, 128)
        .childOption(ChannelOption.TCP_NODELAY, true)
        .childOption(ChannelOption.SO_KEEPALIVE, true)
        .childOption(ChannelOption.SO_REUSEADDR, true)
        .childOption(ChannelOption.SO_RCVBUF, 32 * 1024)
        .childOption(ChannelOption.SO_SNDBUF, 32 * 1024)
        .childOption(EpollChannelOption.SO_REUSEPORT, true)
        .childOption(ChannelOption.SO_KEEPALIVE, true)
        .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

      b.group(bossGroup, workGroup)
        .channel(NioServerSocketChannel.class)
        .handler(new LoggingHandler(LogLevel.INFO))
        .childHandler(new ChannelInitializer<SocketChannel>() {
          @Override
          protected void initChannel(SocketChannel socketChannel) throws Exception {
            ChannelPipeline channelPipeline = socketChannel.pipeline();
            channelPipeline.addLast(new HttpServerCodec());
            channelPipeline.addLast(new HttpObjectAggregator(1024 * 1024));
            channelPipeline.addLast(new ChannelInboundHandlerAdapter() {
              @Override
              public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                try {
                  FullHttpRequest fullHttpRequest = (FullHttpRequest) msg;
                  String uri = fullHttpRequest.uri();
                  FullHttpResponse fullHttpResponse = null;
                  try {
                    if (uri.contains("/api")) {
                      final String value = service(proxyServer + uri);
//                      fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
//                       fullHttpResponse.replace(Unpooled.wrappedBuffer(value.getBytes("UTF-8")));
                      fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,
                          Unpooled.wrappedBuffer(value.getBytes("UTF-8")));
                      fullHttpResponse.headers().set("Content-Type", "application/json");
                      fullHttpResponse.headers().setInt("Content-Length", fullHttpResponse.content().readableBytes());
                    } else {
                      fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NO_CONTENT);
                    }
                  } finally {
                    if (HttpUtil.isKeepAlive(fullHttpRequest)) {
                      fullHttpResponse.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
                      ctx.write(fullHttpResponse);
                    } else {
                      ctx.write(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
                    }
                  }
                } finally {
                  ReferenceCountUtil.release(msg);
                }
              }

              @Override
              public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                ctx.flush();
              }

              @Override
              public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                cause.printStackTrace();
                ctx.close();
              }
            });
          }
        });
      ChannelFuture f = b.bind(port).sync();
      // wait until the server socket is closed
      f.channel().closeFuture().sync();
    } finally {
      workGroup.shutdownGracefully();
      bossGroup.shutdownGracefully();
    }
  }

  public static void main(String[] args) throws InterruptedException {
    //  gateway-server 服务
    final String proxyServer = "http://localhost:8801";
    int port = 8080;
    if (args.length > 0) {
      port = Integer.parseInt(args[0]);
    }
    NettyHttpServer1 server = new NettyHttpServer1(port);
    System.out.println("开启netty http服务器，监听地址和端口为 http://127.0.0.1:" + port + '/');
    server.run(proxyServer);
  }
}
