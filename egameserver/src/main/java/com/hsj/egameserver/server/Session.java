package com.hsj.egameserver.server;

import java.util.List;
import java.util.Vector;

import com.hsj.egameserver.events.Event;
import com.hsj.egameserver.events.EventDispatcher;
import com.hsj.egameserver.events.EventListener;
import com.hsj.egameserver.game.Player;
import com.hsj.egameserver.game.Position;
import com.hsj.egameserver.game.WorldObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Session extends EventDispatcher implements EventListener {

    private List<WorldObject> entities = new Vector<WorldObject>();

    private boolean sessionActive = false;

    private Player owner;

    public Session(Player player) {

        super();
//        player.setSession(this);
        owner = player;
        setActive(true);
    }

    public void close() {
        this.empty();
        setActive(false);
    }

    public boolean contains(Position position) {

        Player owner = getOwner();
       // return owner.getPosition().within(position, owner.getSessionRadius());
        //return owner.getPosition().distance(position)< owner.getSessionRadius();
        return false;
    }

    public boolean contains(WorldObject entity) {
        synchronized(entities){
            return entities.contains(entity);
        }
    }

    public boolean getActive() {
        return sessionActive;
    }

    public Player getOwner() {
        return owner;
    }

    public void setActive(boolean sessionActive) {
        this.sessionActive = sessionActive;
    }

    public void exit(WorldObject entity){

        exit(entity, true);

    }

    public void exit(WorldObject entity, boolean defaultAction){

        //LoggerFactory.getLogger(Session.class).debug("exit "+getOwner()+" "+entity);
        synchronized(entities){
            if (!entities.contains(entity)) {
                return;
            }
            while (entities.contains(entity)) {
                entities.remove(entity);
            }
           // entity.addEventListener(SessionEvent.class, this);

        }
        if(defaultAction) {
            //  entity.exit(this);
        }
    }
    public void enter(WorldObject entity){
        enter(entity, true);

    }
    public void enter(WorldObject entity, boolean defaultAction){

        //LoggerFactory.getLogger(Session.class).debug("enter "+getOwner().getName()+" "+entity);
        synchronized(entities){
            if(this.contains(entity))
                return;

          // entity.addEventListener(SessionEvent.class, this);
            entities.add(entity);


        }
        if(defaultAction) {
            //  entity.enter(this);
        }
    }

    public void empty() {

        List<WorldObject> tmpEntities = new Vector<WorldObject>(entities);
        for(WorldObject object:tmpEntities)
        {
            exit(object);
        }
        entities.clear();

    }

    @Override
    public void handleEvent(Event event) {

//        if(event instanceof MapEvent){
//            LocalMap map = ((MapEvent)event).getMap();
//        }
//
//        if(event instanceof SessionEvent){
//            Session session = ((SessionEvent)event).getSession();
//            if(event instanceof SendPacketSessionEvent){
//                SendPacketSessionEvent sendPacketSessionEvent = (SendPacketSessionEvent)event;
//                String data = sendPacketSessionEvent.getData();
//                getOwner().getClient().sendData(data);
//            }
//        }
    }

}