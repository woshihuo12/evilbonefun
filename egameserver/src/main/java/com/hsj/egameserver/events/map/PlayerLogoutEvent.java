package com.hsj.egameserver.events.map;

import com.hsj.egameserver.game.Player;

public class PlayerLogoutEvent extends MapEvent {

    private Player player;

    public PlayerLogoutEvent(Player player) {
        super(player.getPosition().getLocalMap());
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }
}