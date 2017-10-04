package com.hsj.egameserver.events.client;

import com.hsj.egameserver.server.Client;

public class ClientConnectEvent extends ClientEvent {

    public ClientConnectEvent(Client client) {
        super(client);
    }
}