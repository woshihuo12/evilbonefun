package com.hsj.egameserver.network;

import java.io.IOException;

public abstract class PacketServer<T extends PacketConnection<T>> extends NetworkThread<T> {
    public PacketServer() throws IOException {
        super();
    }
}