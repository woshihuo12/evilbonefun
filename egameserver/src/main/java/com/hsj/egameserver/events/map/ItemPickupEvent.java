package com.hsj.egameserver.events.map;

import com.hsj.egameserver.game.Player;
import com.hsj.egameserver.game.RoamingItem;

public class ItemPickupEvent extends MapEvent {

    private Player player;
    private RoamingItem roamingItem;

    public ItemPickupEvent(Player player, RoamingItem roamingItem) {
        super(roamingItem.getPosition().getLocalMap());
        this.player = player;
        this.roamingItem = roamingItem;
    }

    public Player getPlayer() {
        return player;
    }

    public RoamingItem getRoamingItem() {
        return roamingItem;
    }
}