package ru.progrm_jarvis.ezclientserver.netty.transport.ru.progrm_jarvis.ezclientserver.protocol;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.ReplayingDecoder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public abstract class PacketManager<I, D, P extends Packet> extends ReplayingDecoder<Void> {

    @NonNull private final Map<I, Packet.Retriever<D, P>> retrievers;

    protected void register(@NonNull final I id, @NonNull final Packet.Retriever<D, P> retriever) {
        if (retrievers.containsKey(id)) throw new IllegalArgumentException("There is already a packet " +
                "registered by id ".concat(String.valueOf(id)));

        retrievers.put(id, retriever);
    }

    protected void unregister(@NonNull final I id) {
        if (retrievers.containsKey(id)) retrievers.remove(id);
        else throw new IllegalArgumentException("There is no packet registered by id ".concat(String.valueOf(id)));
    }

    protected Packet.Retriever<D, P> retrievePacket(final I id) {
        return retrievers.get(id);
    }

    protected abstract I readId(ByteBuf in);

    protected abstract D readData(ByteBuf in);
}
