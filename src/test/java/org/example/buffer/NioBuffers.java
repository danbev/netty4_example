package org.example.buffer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import org.junit.Test;

public class NioBuffers {

    @Test
    public void byteBuffer() {
        final ByteBuffer b = ByteBuffer.allocate(3);
        assertThat(b.position(), is(0));
        assertThat(b.putChar('b').position(), is(2)); //char is 16 bits = 2 bytes
        
        b.flip();
        assertThat(b.getChar(), is('b'));
        b.limit(b.position()).position(0);
        assertThat(b.getChar(), is('b'));
        
        assertThat(b.hasArray(), is(true));
    }
    
    @Test
    public void charBuffer() {
        final CharBuffer c = CharBuffer.wrap("bajja");
        assertThat(c.get(), is('b'));
        System.out.println(c.order());
    }
    
}
