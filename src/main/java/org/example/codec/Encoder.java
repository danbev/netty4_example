package org.example.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

public class Encoder extends MessageToMessageEncoder<Envelope, ByteBuf> {

    @Override
    public ByteBuf encode(final ChannelHandlerContext ctx, final Envelope msg) throws Exception {
        // version(1) + type(1) + payload length(4) + payload.length
        final int payloadLength = msg.getPayload().length;
        int size = 6 + payloadLength;
        
        final ByteBuf buffer = Unpooled.buffer(size);
        buffer.writeByte(msg.getVersion().getByteValue());
        buffer.writeByte(msg.getType().getByteValue());
        buffer.writeInt(payloadLength);
        buffer.writeBytes(msg.getPayload());
        return buffer;
    }

}
