package org.example.buffer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.nio.ByteOrder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import org.junit.Before;
import org.junit.Test;

public class ByteBufTest {
    
    private ByteBuf b;

    @Before
    public void createByteBuf() {
        b = Unpooled.buffer(10);
        b.writeChar('b');
        b.writeChar('a');
        b.writeChar('j');
        b.writeChar('j');
        b.writeChar('a');
    }
    
    @Test
    public void isDirect() {
        assertThat(b.isDirect(), is(false));
    }
    
    @Test
    public void order() {
        assertThat(b.order(), is(ByteOrder.BIG_ENDIAN));
    }

    @Test
    public void write() {
        assertThat(b.writerIndex(), is(10));
        assertThat(b.readerIndex(), is(0));
    }
    
    @Test
    public void read() {
        assertThat(b.readChar(), is('b'));
        assertThat(b.readerIndex(), is(2));
    }
    
    @Test
    public void discardReadBytes() {
        assertThat(b.readChar(), is('b'));
        b.discardReadBytes();
        assertThat(b.readerIndex(), is(0));
        assertThat(b.readChar(), is('a'));
        assertThat(b.readerIndex(), is(2));
    }
    
}
