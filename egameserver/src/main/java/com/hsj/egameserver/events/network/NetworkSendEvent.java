package com.hsj.egameserver.events.network;

import java.nio.channels.SocketChannel;

public class NetworkSendEvent extends NetworkEvent {

    public NetworkSendEvent(SocketChannel socketChannel) {
        super(socketChannel);
    }
}