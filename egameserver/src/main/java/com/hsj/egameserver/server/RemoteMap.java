package com.hsj.egameserver.server;

import com.hsj.egameserver.events.Event;
import org.slf4j.LoggerFactory;

public class RemoteMap extends Map {

    public RemoteMap(int id) {
        super(id);
    }

    @Override
    public void load() {
        super.load();
        LoggerFactory.getLogger(RemoteMap.class).info("Remote server registered on " + getAddress().getHostName() + ":" + getAddress().getPort() + " for " + this.getName());
    }

    @Override
    public void handleEvent(Event event) {
    }
}