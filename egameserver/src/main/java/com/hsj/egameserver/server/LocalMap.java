package com.hsj.egameserver.server;

import com.hsj.ecommon.Parser;
import com.hsj.egameserver.events.Event;
import com.hsj.egameserver.events.map.*;
import com.hsj.egameserver.game.Entity;
import com.hsj.egameserver.game.PlayerSpawn;
import com.hsj.egameserver.game.Spawn;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;

public class LocalMap extends Map implements Runnable {

    private List<Spawn> npcSpawnList = new Vector<>();
    private List<Spawn> playerSpawnList = new Vector<>();

    private Area area = new Area();

    private SessionList<Session> sessions = new SessionList<>();

    private HashMap<Integer, Entity> entities = new HashMap<Integer, Entity>();

    private Parser playerSpawnReference;

    private ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    private ScheduledFuture<?> mobsAI;

    private Thread thread;

    private Parser mobSpawnReference;
    private Parser npcSpawnReference;
    private PlayerSpawn defaultSpawn;

    private World world;

    public World getWorld() {
        return world;
    }

    public LocalMap(World world, int id) {
        super(id);
        this.world = world;

        thread = new Thread(this);
        thread.setDaemon(true);
        thread.start();

        addEventListener(SpawnEvent.class, this);
        addEventListener(PlayerLoginEvent.class, this);
        addEventListener(PlayerLogoutEvent.class, this);
        addEventListener(ItemDropEvent.class, this);
        addEventListener(ItemPickupEvent.class, this);
    }

    public Entity getEntity(int id) {
        synchronized (entities) {
            return (Entity) entities.get(id);
        }
    }

    public int getEntitiesListSize() {
        return entities.size();
    }

    private int entityIter = 0;

    public synchronized int createEntityId(Entity obj) {
        synchronized (this.entities) {
            if (!entities.containsValue(obj)) {
                int counter = 0;

                while (entities.containsKey(entityIter)) {
                    entityIter++;
                    if (entityIter >= Integer.MAX_VALUE) {
                        entityIter = 0;
                    }
                    counter++;
                    if (counter >= Integer.MAX_VALUE)
                        throw new RuntimeException("No more available entity ids");
                }

                int id = entityIter;
                obj.setEntityId(id);
                entities.put(id, obj);
                entityIter++;
                return id;
            } else {
                return obj.getEntityId();
            }
        }
    }

    @Override
    public void handleEvent(Event event) {

    }

    @Override
    public void run() {

    }
}
