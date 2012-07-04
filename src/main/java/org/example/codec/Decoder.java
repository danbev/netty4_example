package org.example.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

public class Decoder extends ReplayingDecoder<Object, DecodingState> {
    
    private Envelope.Builder builder;
    
    public Decoder() {
        reset();
    }

    @Override
    public Object decode(final ChannelHandlerContext ctx, final ByteBuf in) throws Exception {
        switch(state()) {
            case VERSION: 
                builder.version(in.readByte());
                checkpoint(DecodingState.TYPE);
            case TYPE:
                builder.type(in.readByte());
                checkpoint(DecodingState.PAYLOAD_LENGTH);
            case PAYLOAD_LENGTH:
                final int size = in.readInt();
                if (size <= 0) {
                    throw new Exception("Invalid content size");
                }
                builder.payloadSize(size);
                checkpoint(DecodingState.PAYLOAD);
            case PAYLOAD:
                final byte[] content = new byte[builder.payloadSize()];
                in.readBytes(content, 0, content.length);
                builder.payload(content);
                try {
                    return builder.build();
                } finally {
                    reset();
                }
            default:
                throw new Exception("Unknown decoding state: " + state());
        }
    }
    
    private void reset() {
        checkpoint(DecodingState.VERSION);
        builder = new Envelope.Builder();
    }

}
