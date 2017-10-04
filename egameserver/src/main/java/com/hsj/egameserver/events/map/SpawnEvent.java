package com.hsj.egameserver.events.map;

import com.hsj.egameserver.game.LivingObject;

public class SpawnEvent extends MapEvent {

    private LivingObject spawnee;

    public SpawnEvent(LivingObject spawnee) {
        super(spawnee.getPosition().getLocalMap());
        this.spawnee = spawnee;
    }

    public LivingObject getSpawnee() {
        return spawnee;
    }
}