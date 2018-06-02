package ru.progrm_jarvis.ezclientserver.netty.transport.ru.progrm_jarvis.ezclientserver.protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class PacketHandler<P extends Packet> extends SimpleChannelInboundHandler<P> {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void channelRead0(final ChannelHandlerContext ctx, final P packet) {
        packet.handle();
    }
}
