package org.example.server;
import org.example.codec.Envelope;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundMessageHandlerAdapter;

public class ServerEnvelopeHandler extends ChannelInboundMessageHandlerAdapter<Envelope> {

    @Override
    public void messageReceived(ChannelHandlerContext ctx, Envelope msg) throws Exception {
        System.out.println("MessageReceived: " + new String(msg.getPayload()));
    }

    @Override
    public void exceptionCaught(final ChannelHandlerContext ctx, final Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
    
}
