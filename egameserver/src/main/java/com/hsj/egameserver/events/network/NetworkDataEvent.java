package com.hsj.egameserver.events.network;

import java.nio.channels.SocketChannel;

public class NetworkDataEvent extends NetworkEvent {
    private byte[] data;

    public NetworkDataEvent(SocketChannel socketChannel, byte[] data) {
        super(socketChannel);
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }
}