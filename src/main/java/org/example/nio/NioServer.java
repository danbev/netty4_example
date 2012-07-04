package org.example.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Set;

public class NioServer {
    
    public static void main(final String... args) {
        Selector selector;
        ServerSocketChannel serverChannel;
        try {
            serverChannel = SelectorProvider.provider().openServerSocketChannel();
            final ServerSocket serverSocket = serverChannel.socket();
            serverSocket.bind(new InetSocketAddress("localhost", 1234));
            selector = Selector.open();
            serverChannel.configureBlocking(false);
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (final IOException e) {
            e.printStackTrace();
            return;
        }
            
        while(true) {
            try {
                selector.select();
            } catch (final IOException e) {
                e.printStackTrace();
                break;
            }
            
            final Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readyKeys.iterator();
            while (iterator.hasNext()) {
                final SelectionKey key = iterator.next();
                iterator.remove();
                try {
                    if (key.isAcceptable()) {
                        final ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                        final SocketChannel connection = serverSocketChannel.accept();
                        connection.configureBlocking(false);
                        connection.register(selector, SelectionKey.OP_WRITE);
                    } else if (key.isWritable()) {
                        final SocketChannel client = (SocketChannel) key.channel();
                        client.write(ByteBuffer.wrap("bajja".getBytes()));
                        key.cancel();
                        client.close();
                    }
                } catch (final IOException e) {
                    key.cancel();
                    try {
                        key.channel().close();
                    } catch (final IOException ignored) {
                    }
                }
            }
        }
    }

}
