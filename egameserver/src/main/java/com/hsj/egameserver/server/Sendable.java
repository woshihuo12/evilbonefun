package com.hsj.egameserver.server;

import com.hsj.egameserver.server.PacketFactory.Type;

public interface Sendable {
    void sendPacket(Type packetType, Object... args);
}