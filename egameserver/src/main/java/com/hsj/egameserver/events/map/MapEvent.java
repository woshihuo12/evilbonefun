package com.hsj.egameserver.events.map;

import com.hsj.egameserver.events.Event;
import com.hsj.egameserver.server.LocalMap;

public class MapEvent extends Event {

    private LocalMap map;

    public MapEvent(LocalMap map) {
        this.map = map;
    }

    public LocalMap getMap() {
        return map;
    }
}