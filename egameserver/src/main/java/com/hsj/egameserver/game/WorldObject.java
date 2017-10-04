package com.hsj.egameserver.game;

import com.hsj.egameserver.events.EventDispatcher;
import com.hsj.egameserver.events.session.SendPacketSessionEvent;
import com.hsj.egameserver.server.PacketFactory;
import com.hsj.egameserver.server.Sendable;
import com.hsj.egameserver.server.Session;
import com.hsj.egameserver.server.SessionList;

public class Interested implements Sendable {

    private WorldObject entity;

    public WorldObject getEntity() {
        return entity;
    }

    public Interested(WorldObject entity) {
        this.entity = entity;
    }

    public SessionList<Session> getSessions() {
        return null;
//        return entity.getPosition().getLocalMap().GetSessions(entity);
    }

    @Override
    public void sendPacket(PacketFactory.Type packetType, Object... args) {
        String data = PacketFactory.createPacket(packetType, args);
        entity.fireEvent(SendPacketSessionEvent.class, null, data);
    }
}

public abstract class WorldObject extends EventDispatcher implements Entity {

    private int id = -1;
    private Position position = new Position();

    public Interested getInterested() {
        return interested;
    }

    private Interested interested = new Interested(this);

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract void enter(Session session);
    public abstract void exit(Session session);

    @Override
    public int getEntityId() {
        return id;
    }

    @Override
    public void setEntityId(int id) {
        this.id = id;
    }
}
