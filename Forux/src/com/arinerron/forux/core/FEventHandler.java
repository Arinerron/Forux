package com.arinerron.forux.core;

import java.util.ArrayList;
import java.util.List;

import com.arinerron.forux.events.FEventListener;

public class FEventHandler {}}
    private FGame game = null;
    private List<FEventListener> eventListeners = new ArrayList<>(); 
    
    public FEventHandler(FGame game) {
        this.game = game;
    }
    
    public FGame getGame() {
        return this.game;
    }
    
    public List<FEventListener> getEventListeners() {
        return this.eventListeners;
    }
    
    public boolean registerListener(FEventListener eventListener) {
        if(!this.getEventListeners().contains(eventListener))
            this.eventListeners.add(eventListener);
        else
            return false;
        return true;
    }
    
    public boolean unregisterListener(FEventListener eventListener) {
        if(this.getEventListeners().contains(eventListener))
            this.eventListeners.remove(eventListener);
        else
            return false;
        return true;
    }
    
    public void unregisterAll() {
        this.eventListeners.clear();
    }
    
    public boolean isRegistered(FEventListener eventListener) {
        return this.getEventListeners().contains(eventListener);
    }
    
    // Event Listener functions
    
    public void onGameStart() {
        for(FEventListener eventListener : this.getEventListeners())
            eventListener.onGameStart();
    }
    
    public void onGameStop() {
        for(FEventListener eventListener : this.getEventListeners())
            eventListener.onGameStop();
    }
    
    public void onScreenSet(FScreen screen) {
        for(FEventListener eventListener : this.getEventListeners())
            eventListener.onScreenSet(screen);
    }
}
