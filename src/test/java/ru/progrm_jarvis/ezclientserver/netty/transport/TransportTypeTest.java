package ru.progrm_jarvis.ezclientserver.netty.transport;

import io.netty.channel.kqueue.KQueue;
import lombok.val;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;

import static org.mockito.Mockito.mock;
import static ru.progrm_jarvis.ezclientserver.netty.transport.TransportType.*;

public class TransportTypeTest {

    private static final int N_THREADS = 2;

    @Test
    public void eventLoopGroupClass() {
        for (val transportType : values()) assertNotNull(transportType.eventLoopGroupClass());
    }

    @Test
    public void serverSocketChannelClass() {
        for (val transportType : values()) assertNotNull(transportType.serverSocketChannelClass());
    }

    @Test
    public void getNativeAndEventLoopGroup() {
        val nativeTransport = getNative();

        assertNotNull(nativeTransport.eventLoopGroup());
        assertNotNull(nativeTransport.eventLoopGroup(N_THREADS));
        assertNotNull(nativeTransport.eventLoopGroup(N_THREADS, mock(ThreadFactory.class)));
        assertNotNull(nativeTransport.eventLoopGroup(N_THREADS, mock(Executor.class)));
    }
}