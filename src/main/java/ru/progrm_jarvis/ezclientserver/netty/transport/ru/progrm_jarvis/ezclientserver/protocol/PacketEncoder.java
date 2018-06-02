package ru.progrm_jarvis.ezclientserver.netty.transport.ru.progrm_jarvis.ezclientserver.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.ReplayingDecoder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;

import java.util.List;

@RequiredArgsConstructor
public class PacketEncoder<P extends Packet> extends MessageToByteEncoder<P> {

    @Override
    protected void encode(final ChannelHandlerContext ctx, final P packet, final ByteBuf out) {
        packet.writeTo(out);
    }
}
