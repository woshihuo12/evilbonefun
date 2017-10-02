package com.hsj.egameserver.events.server;

import com.hsj.egameserver.server.Server;

public class ServerStopEvent extends ServerEvent {
    public ServerStopEvent(Server server) {
        super(server);
    }
}