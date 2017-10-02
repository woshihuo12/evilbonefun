package com.hsj.egameserver.events.server;

import com.hsj.egameserver.events.Event;
import com.hsj.egameserver.server.Server;

public class ServerEvent extends Event {
    Server server;

    public Server getServer() {
        return server;
    }

    public ServerEvent(Server server) {
        this.server = server;
    }
}
