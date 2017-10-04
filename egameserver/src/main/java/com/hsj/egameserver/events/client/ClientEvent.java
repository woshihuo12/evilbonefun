package com.hsj.egameserver.events.client;

import com.hsj.egameserver.events.Event;
import com.hsj.egameserver.events.Filter;
import com.hsj.egameserver.events.InvalidEventException;
import com.hsj.egameserver.server.Client;

public class ClientEvent extends Event {

    private Client client;

    public ClientEvent(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public static class ClientFilter implements Filter {

        private Client client;

        public ClientFilter(Client client) {
            this.client = client;
        }

        @Override
        public boolean filter(Event event) {
            if (!(event instanceof ClientEvent)) {
                throw new InvalidEventException(event, ClientEvent.class);
            }
            return ((ClientEvent) event).getClient() == this.client;
        }
    }
}