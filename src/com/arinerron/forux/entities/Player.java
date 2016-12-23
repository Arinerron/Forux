package com.arinerron.forux.entities;

import com.arinerron.forux.events.EventListener;
import com.arinerron.forux.core.Game;

public class Player extends Entity {
    private Game game = null;

    public Player(Game game) {
        this.game = game;
    }

    @Override
    public JSONObject toJSON() {
        return super.toJSON().put("player", true);
    }

    public Game getGame() {
        return this.game;
    }
}
