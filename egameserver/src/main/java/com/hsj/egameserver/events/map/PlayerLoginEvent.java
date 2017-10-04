package com.hsj.egameserver.events.map;

import com.hsj.egameserver.game.Player;
import com.hsj.egameserver.game.Position;

public class PlayerLoginEvent extends MapEvent {

    private Player player;
    private Position position;

    public PlayerLoginEvent(Player player, Position position) {
        super(player.getPosition().getLocalMap());
        this.player = player;
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public Player getPlayer() {
        return player;
    }
}