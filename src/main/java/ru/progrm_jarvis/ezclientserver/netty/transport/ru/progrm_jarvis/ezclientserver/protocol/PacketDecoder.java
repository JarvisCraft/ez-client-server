package ru.progrm_jarvis.ezclientserver.netty.transport.ru.progrm_jarvis.ezclientserver.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;

import java.util.List;

@RequiredArgsConstructor
public class PacketDecoder<I, D, P extends Packet> extends ReplayingDecoder<Void> {

    @NonNull private final PacketManager<I, D, P> packetManager;

    @Override
    protected void decode(final ChannelHandlerContext ctx, final ByteBuf in, final List<Object> out) {
        val retriever = packetManager.retrievePacket(packetManager.readId(in));
        if (retriever == null) throw new UnknownPacketException();

        val packet = retriever.retrieve(packetManager.readData(in));
        if (packet == null) throw new UnretrievedPacketException();

        out.add(packet);
    }
}
