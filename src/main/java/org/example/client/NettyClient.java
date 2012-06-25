package org.example.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioEventLoop;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.Scanner;

public class NettyClient {
    
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
                    ch.pipeline().addLast(new ClientHandler(), new StringEncoder());
                }
            });
            
            final ChannelFuture f = bootstrap.connect().sync();
            f.await();
            
            if (f.isSuccess()) {
	            final Scanner scanner = new Scanner(System.in);
	            final Channel channel = f.channel();
	            System.out.println("Enter line to be sent (exit to quit):");
	            while (scanner.hasNext()) {
	                final String line = scanner.nextLine();
	                if (line.equals("exit")) {
	                    break;
	                }
	                channel.write(line + "\n");
	            }
            }
        } finally {
            bootstrap.shutdown();
        }
    }

}
