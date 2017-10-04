package com.hsj.egameserver.events.client;

import com.hsj.egameserver.server.Client;

public class ClientDisconnectEvent extends ClientEvent {

    public ClientDisconnectEvent(Client client) {
        super(client);
    }
}