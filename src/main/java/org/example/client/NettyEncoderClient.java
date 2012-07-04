package org.example.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioEventLoop;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

import org.example.codec.Encoder;
import org.example.codec.Envelope;
import org.example.codec.TypeImpl;
import org.example.codec.VersionImpl;

public class NettyEncoderClient {
    
    public static void main(final String[] args) throws Exception {
        final Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.eventLoop(new NioEventLoop())
            .channel(new NioSocketChannel())
            .option(ChannelOption.TCP_NODELAY, true)
            .remoteAddress(new InetSocketAddress("localhost", 8080))
            .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new ClientHandler(), new Encoder());
                }
            });
            
            final ChannelFuture f = bootstrap.connect().sync();
            f.await();
            
            final Channel channel = f.channel();
            final Envelope envelope = new Envelope(new VersionImpl((byte)1), new TypeImpl((byte)2), "bajja".getBytes());
            channel.write(envelope);
        } finally {
            bootstrap.shutdown();
        }
    }

}
