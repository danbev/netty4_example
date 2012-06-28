package org.example.websocket.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioEventLoop;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpChunkAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

import java.net.InetSocketAddress;


public class WebSocketServer {
    
    public static void main(String[] args) throws Exception {
        final ServerBootstrap sb = new ServerBootstrap();
        try {
            sb.eventLoop(new NioEventLoop(), new NioEventLoop())
             .channel(new NioServerSocketChannel())
             .localAddress(new InetSocketAddress(8080))
             .childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(final SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new HttpRequestDecoder(),
                            new HttpChunkAggregator(65536),
                            new HttpResponseEncoder(),
                            new WebSocketServerHandler());
                }
            });

            final Channel ch = sb.bind().sync().channel();
            System.out.println("Web socket server started at port 8080");

            ch.closeFuture().sync();
        } finally {
            sb.shutdown();
        }
    }


}
