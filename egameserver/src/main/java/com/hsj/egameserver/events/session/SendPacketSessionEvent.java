package com.hsj.egameserver.events.session;

import com.hsj.egameserver.server.Session;

public class SendPacketSessionEvent extends SessionEvent {

    private String data;
    public SendPacketSessionEvent(Session session, String data) {
        super(session);
        this.data = data;
    }
    public String getData() {
        return data;
    }
}