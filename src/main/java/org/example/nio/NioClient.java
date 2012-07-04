package org.example.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.SocketChannel;
import java.nio.channels.WritableByteChannel;

public class NioClient {
    
        public static void main(String[] args) {

        SocketChannel client = null;
          try {
            client = SocketChannel.open(new InetSocketAddress("localhost", 1234));
            final ByteBuffer buffer = ByteBuffer.allocate(5);
            final WritableByteChannel out = Channels.newChannel(System.out);
            while (client.read(buffer) != -1) {
              buffer.flip();
              out.write(buffer);
              buffer.clear();
            }
          }
          catch (IOException ex) {
              ex.printStackTrace( );
          }
          finally {
              if (client != null) {
                  try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
              }
          }
      }
}
