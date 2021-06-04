package demo.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;

public class TimeServerHandler extends ChannelInboundHandlerAdapter {
  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
//    super.channelActive(ctx);
    final ByteBuf time = ctx.alloc().buffer(4);
    time.writeInt((int)(System.currentTimeMillis() / 1000L  + 220898880L));

    final ChannelFuture f = ctx.writeAndFlush(time);

    f.addListener((ChannelFutureListener) channelFuture -> {
      assert f == channelFuture;
      ctx.close();
    });
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
//    super.exceptionCaught(ctx, cause);
    cause.printStackTrace();
    ctx.close();
  }
}
