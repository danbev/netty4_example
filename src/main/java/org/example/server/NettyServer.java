package org.example.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioEventLoop;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

import org.example.codec.Decoder;


public class NettyServer {
    
    public static void main(String[] args) throws Exception {
        final ServerBootstrap sb = new ServerBootstrap();
        try {
            sb.eventLoop(new NioEventLoop(), new NioEventLoop())
            .channel(new NioServerSocketChannel())
            
            // Set the number of outstanding connection request that can be waiting on the TCP port
            .option(ChannelOption.SO_BACKLOG, 100)
            .localAddress(new InetSocketAddress(8080))
            
            // batch TCP packets.
            .childOption(ChannelOption.TCP_NODELAY, true)
            
            .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(final SocketChannel ch) throws Exception {
                    //ch.pipeline().addLast(new DelimiterBasedFrameDecoder(512, true, Delimiters.lineDelimiter()),
                    //new StringDecoder(CharsetUtil.UTF_8),
                    ch.pipeline().addLast(new Decoder(),
                    //new StringDecoder(CharsetUtil.UTF_8),
                    new ServerEnvelopeHandler());
                }
            });
            
            final ChannelFuture f = sb.bind().sync();
            System.out.println("Server started.");
            f.channel().closeFuture().sync();
        } finally {
            sb.shutdown();
        }
    }

}
