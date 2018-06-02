package ru.progrm_jarvis.ezclientserver.netty.transport;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.kqueue.KQueue;
import io.netty.channel.kqueue.KQueueEventLoopGroup;
import io.netty.channel.kqueue.KQueueServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadFactory;

/**
 * Available transport types to be used by netty on current machine.
 */
public enum TransportType {
    NIO {
        @Override
        public Class<NioEventLoopGroup> eventLoopGroupClass() {
            return NioEventLoopGroup.class;
        }

        @Override
        public Class<NioServerSocketChannel> serverSocketChannelClass() {
            return NioServerSocketChannel.class;
        }

        @Override
        public NioEventLoopGroup eventLoopGroup() {
            return new NioEventLoopGroup();
        }

        @Override
        public NioEventLoopGroup eventLoopGroup(final int nThreads) {
            return new NioEventLoopGroup(nThreads);
        }

        @Override
        public NioEventLoopGroup eventLoopGroup(final int nThreads, final ThreadFactory threadFactory) {
            return new NioEventLoopGroup(nThreads, threadFactory);
        }

        @Override
        public NioEventLoopGroup eventLoopGroup(final int nThreads, final Executor executor) {
            return new NioEventLoopGroup(nThreads, executor);
        }

        @Override
        public NioServerSocketChannel serverSocketChannel() {
            return new NioServerSocketChannel();
        }
    },
    EPOLL {
        @Override
        public Class<EpollEventLoopGroup> eventLoopGroupClass() {
            return EpollEventLoopGroup.class;
        }

        @Override
        public Class<EpollServerSocketChannel> serverSocketChannelClass() {
            return EpollServerSocketChannel.class;
        }

        @Override
        public EpollEventLoopGroup eventLoopGroup() {
            return new EpollEventLoopGroup();
        }

        @Override
        public EpollEventLoopGroup eventLoopGroup(final int nThreads) {
            return new EpollEventLoopGroup(nThreads);
        }

        @Override
        public EpollEventLoopGroup eventLoopGroup(final int nThreads, final ThreadFactory threadFactory) {
            return new EpollEventLoopGroup(nThreads, threadFactory);
        }

        @Override
        public EpollEventLoopGroup eventLoopGroup(final int nThreads, final Executor executor) {
            return new EpollEventLoopGroup(nThreads, executor);
        }

        @Override
        public EpollServerSocketChannel serverSocketChannel() {
            return new EpollServerSocketChannel();
        }
    },
    KQUEUE {
        @Override
        public Class<KQueueEventLoopGroup> eventLoopGroupClass() {
            return KQueueEventLoopGroup.class;
        }

        @Override
        public Class<KQueueServerSocketChannel> serverSocketChannelClass() {
            return KQueueServerSocketChannel.class;
        }

        @Override
        public KQueueEventLoopGroup eventLoopGroup() {
            return new KQueueEventLoopGroup();
        }

        @Override
        public KQueueEventLoopGroup eventLoopGroup(final int nThreads) {
            return new KQueueEventLoopGroup(nThreads);
        }

        @Override
        public KQueueEventLoopGroup eventLoopGroup(final int nThreads, final ThreadFactory threadFactory) {
            return new KQueueEventLoopGroup(nThreads, threadFactory);
        }

        @Override
        public KQueueEventLoopGroup eventLoopGroup(final int nThreads, final Executor executor) {
            return new KQueueEventLoopGroup(nThreads, executor);
        }

        @Override
        public KQueueServerSocketChannel serverSocketChannel() {
            return new KQueueServerSocketChannel();
        }
    };

    public abstract Class<? extends EventLoopGroup> eventLoopGroupClass();

    public abstract Class<? extends ServerSocketChannel> serverSocketChannelClass();

    public abstract EventLoopGroup eventLoopGroup();

    public abstract EventLoopGroup eventLoopGroup(int nThreads);

    public abstract EventLoopGroup eventLoopGroup(int nThreads, ThreadFactory threadFactory);

    public abstract EventLoopGroup eventLoopGroup(int nThreads, Executor executor);

    public abstract ServerSocketChannel serverSocketChannel();

    public static TransportType getNative() {
        return Epoll.isAvailable() ? EPOLL : KQueue.isAvailable() ? KQUEUE : NIO;
    }
}
