package com.arinerron.forux.events;

import com.arinerron.forux.core.FEventHandler;
import com.arinerron.forux.core.FGame;
import com.arinerron.forux.core.FScreen;

public class FEventListener {
    private FGame game = null;
    
    public FEventListener(FGame game) {
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
    
    public FGame getGame() {
        return this.game;
    }
    
    public FEventHandler getEventHandler() {
        return this.getGame().getEventHandler();
    }
    
    public void onGameStart() {}
    public void onGameStop() {}
    public void onScreenSet(FScreen screen) {}
}
