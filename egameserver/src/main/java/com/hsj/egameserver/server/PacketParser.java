package com.hsj.egameserver.server;

import com.hsj.egameserver.events.Event;
import com.hsj.egameserver.events.EventDispatcher;
import com.hsj.egameserver.events.EventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PacketParser extends EventDispatcher implements EventListener {

    private MessageParser messageParser;
    private static Logger logger = LoggerFactory.getLogger(PacketParser.class);

    public PacketParser() {
        super();
        messageParser = new MessageParser();
    }



    @Override
    public void handleEvent(Event event) {

    }
}