package com.hsj.egameserver.events.session;

import com.hsj.egameserver.server.Session;

public class NewSessionEvent extends SessionEvent {
    public NewSessionEvent(Session session) {
        super(session);
    }
}