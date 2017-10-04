package com.hsj.egameserver.events.client;

import com.hsj.egameserver.server.Client;

public class ClientSendEvent extends ClientEvent {

    public ClientSendEvent(Client client) {
        super(client);
    }
}