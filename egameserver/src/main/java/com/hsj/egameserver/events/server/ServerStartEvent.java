package com.hsj.egameserver.events.server;

import com.hsj.egameserver.server.Server;

public class ServerStartEvent extends ServerEvent {

    public ServerStartEvent(Server server) {
        super(server);
    }
}
