package com.hsj.egameserver.server;

import com.hsj.egameserver.events.Event;
import com.hsj.egameserver.events.EventDispatcher;
import com.hsj.egameserver.events.EventListener;
import org.springframework.stereotype.Service;

@Service
public class Network extends EventDispatcher implements EventListener, Runnable {
    @Override
    public void handleEvent(Event event) {

    }

    @Override
    public void run() {

    }
}
