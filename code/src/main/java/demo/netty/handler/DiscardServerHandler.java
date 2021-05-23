package demo.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/*
Handles a server-side channel.
<p/>
a protocal
  that discards any received data without any response
<p/>
-- md
- ChannelInboundHandler
- ByteBuf a reference-couted object which has to be released explicitly via the release() method
 */
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {
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
}
