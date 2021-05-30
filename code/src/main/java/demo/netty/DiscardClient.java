package demo.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.SneakyThrows;

// 不考虑ssl
public class DiscardClient {
  @SneakyThrows
  public static void main(String[] args) {
    EventLoopGroup group = new NioEventLoopGroup();
    try {
      Bootstrap b = new Bootstrap();
      b.group(group)
        .channel(NioSocketChannel.class)
        .handler(new ChannelInitializer<SocketChannel>() {
          @Override
          protected void initChannel(SocketChannel ch) throws Exception {
            ChannelPipeline p = ch.pipeline();
            // 如果ssl
            p.addLast(new SimpleChannelInboundHandler<Object>() {
              private ByteBuf content;
              private ChannelHandlerContext ctx;
              @Override
              public void channelActive(ChannelHandlerContext ctx) throws Exception {
                this.ctx = ctx;
                // 初始化信息
                ByteBufAllocator alloc = ctx.alloc();
                final int SIZE = 256;
                // 分配并使用空值填充
                content = alloc.directBuffer(SIZE).writeZero(SIZE);
                // 发送信息
                ctx.writeAndFlush(content.retainedDuplicate())
                    .addListener((ChannelFutureListener) future -> {
                      if (future.isSuccess()) {
                        // ...
                        System.out.println("发送成功");
                        // @TODO: 递归调用
                      } else {
                        future.cause().printStackTrace();
                        future.channel().close();
                      }
                    });
              }

              @Override
              public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                content.release();
              }

              @Override
              public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                cause.printStackTrace();
                ctx.close();
              }

              @Override
              protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
                // discard 直接不读
              }
            });
          }
        });
      // 尝试连接
      ChannelFuture f = b.connect("localhost", 8080).sync();
      // 等待 一直到连接关闭
      f.channel().closeFuture().sync();
    } finally {
      group.shutdownGracefully();
    }
  }
}
