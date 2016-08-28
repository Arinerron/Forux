package com.arinerron.forux.events;

import com.arinerron.forux.core.EventHandler;
import com.arinerron.forux.core.Game;
import com.arinerron.forux.core.MenuScreen;

public abstract class EventListener {
    private Game game = null;
    
    public EventListener(Game game) {
        this.game = game;
        this.registerEvent();
    }
    
    public boolean registerEvent() {
        return this.getGame().getEventHandler().registerListener(this);
    }
    
    public boolean unregisterEvent() {
        return this.getGame().getEventHandler().unregisterListener(this);
    }
    
    public boolean isRegistered() {
        return this.getGame().getEventHandler().isRegistered(this);
    }
    
    public Game getGame() {
        return this.game;
    }
    
    public EventHandler getEventHandler() {
        return this.getGame().getEventHandler();
    }
    
    public abstract void onGameStart();
    public abstract void onGameStop();
    public abstract void onGameError(String message);
    public abstract void onScreenSet(MenuScreen screen);
}
