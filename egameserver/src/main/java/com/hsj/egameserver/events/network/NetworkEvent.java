package com.hsj.egameserver.events.network;

import com.hsj.egameserver.events.Event;
import com.hsj.egameserver.events.Filter;
import com.hsj.egameserver.events.InvalidEventException;

import java.nio.channels.SocketChannel;

public class NetworkEvent extends Event {

    private SocketChannel socketChannel;

    public NetworkEvent(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    public static class NetworkFilter implements Filter {
        SocketChannel socketChannel;
        public NetworkFilter(SocketChannel socketChannel) {
            this.socketChannel = socketChannel;
        }

        @Override
        public boolean filter(Event event) {
            if (!(event instanceof NetworkEvent)) {
                throw new InvalidEventException(event, NetworkEvent.class);
            }
            return ((NetworkEvent) event).getSocketChannel().equals(this.socketChannel);
        }
    }
}