package com.arinerron.forux.events;

import com.arinerron.forux.core.EventHandler;
import com.arinerron.forux.core.Game;
import com.arinerron.forux.core.Screen;

public class EventListener {
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
    
    public void onGameStart() {}
    public void onGameStop() {}
    public void onScreenSet(Screen screen) {}
}