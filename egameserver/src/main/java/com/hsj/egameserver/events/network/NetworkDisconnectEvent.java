package com.hsj.egameserver.events.network;

import java.nio.channels.SocketChannel;

public class NetworkDisconnectEvent extends NetworkEvent {

    public NetworkDisconnectEvent(SocketChannel socketChannel) {
        super(socketChannel);
    }
}