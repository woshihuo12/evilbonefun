package com.hsj.egameserver.events.session;

import com.hsj.egameserver.events.Event;
import com.hsj.egameserver.events.Filter;
import com.hsj.egameserver.events.InvalidEventException;
import com.hsj.egameserver.server.Session;

public class SessionEvent extends Event {

    private Session session;

    public SessionEvent(Session session) {
        this.session = session;
    }

    public Session getSession() {
        return session;
    }

    public static class SessionFilter implements Filter {

        private Session session;
        public SessionFilter(Session session) {
            this.session = session;
        }

        @Override
        public boolean filter(Event event) {
            if (!(event instanceof SessionEvent)) {
                throw new InvalidEventException(event, SessionEvent.class);
            }
            return ((SessionEvent) event).getSession().equals(this.session);
        }
    }
}
