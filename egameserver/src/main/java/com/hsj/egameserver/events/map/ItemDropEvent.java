package com.hsj.egameserver.events.map;

import com.hsj.egameserver.game.Player;
import com.hsj.egameserver.game.RoamingItem;

public class ItemDropEvent extends MapEvent {

    private RoamingItem roamingItem;
    private Player player;

    public ItemDropEvent(RoamingItem roamingItem, Player player) {
        super(roamingItem.getPosition().getLocalMap());
        this.roamingItem = roamingItem;
        this.player = player;
    }

    public RoamingItem getRoamingItem() {
        return roamingItem;
    }

    public Player getPlayer() {
        return this.player;
    }
}
