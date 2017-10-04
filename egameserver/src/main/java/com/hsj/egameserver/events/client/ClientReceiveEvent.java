package com.hsj.egameserver.events.client;

import com.hsj.egameserver.server.Client;

public class ClientReceiveEvent extends ClientEvent {

    public ClientReceiveEvent(Client client) {
        super(client);
    }
}