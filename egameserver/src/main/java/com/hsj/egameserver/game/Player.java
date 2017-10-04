package com.hsj.egameserver.game;

import com.hsj.egameserver.events.EventListener;
import com.hsj.egameserver.server.Client;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public abstract class Player extends LivingObject implements EventListener {

    private Client client;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}