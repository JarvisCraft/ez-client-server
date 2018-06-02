package ru.progrm_jarvis.ezclientserver.netty.transport.ru.progrm_jarvis.ezclientserver.protocol;

import io.netty.buffer.ByteBuf;
import lombok.NonNull;

public interface Packet {

    /**
     * Handles the packet when it is incoming.
     */
    default void handle() {
        throwPacketNotIn(this);
    }

    /**
     * Writes content of this packet to {@link ByteBuf given}.
     * @param out byte-buf to write packet-data to
     */
    default void writeTo(final ByteBuf out) {
        throwPacketNotOut(this);
    }

    static void throwPacketNotIn(final Packet packet) {
        throw new UnsupportedOperationException("Packet ".concat(packet.getClass().getName())
                .concat(" is not an incoming one"));
    }

    static void throwPacketNotOut(final Packet packet) {
        throw new UnsupportedOperationException("Packet ".concat(packet.getClass().getName())
                .concat(" is not an outcoming one"));
    }

    @FunctionalInterface
    interface Retriever<D, P extends Packet> {

        /**
         * Retrieves a packet from data given.
         *
         * @param data data to fill a packet with
         * @return {@link Packet} if it was retrieved or {@code null} if not.
         */
        @NonNull P retrieve(D data);
    }
}
