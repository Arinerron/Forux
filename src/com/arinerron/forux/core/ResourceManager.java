package com.arinerron.forux.core;

public class ResourceManager {
    private Game game = null;
    
    public ResourceManager(Game game) {
        this.game = game;
    }
    
    public Game getGame() {
        return this.game;
    }
}
