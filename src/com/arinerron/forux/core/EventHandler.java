package com.arinerron.forux.core;

import java.util.ArrayList;
import java.util.List;

import com.arinerron.forux.events.EventListener;

public class EventHandler {
    private Game game = null;
    private List<EventListener> eventListeners = new ArrayList<>();
    
    protected EventHandler(Game game) {
        this.game = game;
    }
    
    public Game getGame() {
        return this.game;
    }
    
    public List<EventListener> getEventListeners() {
        return this.eventListeners;
    }
    
    public boolean registerListener(EventListener eventListener) {
        if (!this.getEventListeners().contains(eventListener))
            this.eventListeners.add(eventListener);
        else
            return false;
        return true;
    }
    
    public boolean unregisterListener(EventListener eventListener) {
        if (this.getEventListeners().contains(eventListener))
            this.eventListeners.remove(eventListener);
        else
            return false;
        return true;
    }
    
    public void unregisterAll() {
        this.eventListeners.clear();
    }
    
    public boolean isRegistered(EventListener eventListener) {
        return this.getEventListeners().contains(eventListener);
    }
    
    // Event Listener functions
    
    public void onGameStart() {
        for(EventListener eventListener : this.getEventListeners())
            eventListener.onGameStart();
    }
    
    public void onGameStop() {
        for(EventListener eventListener : this.getEventListeners())
            eventListener.onGameStop();
    }
    
    public void onGameError() {
        for(EventListener eventListener : this.getEventListeners())
            eventListener.onGameStop();
    }
    
    public void onScreenSet(Screen screen) {
        for(EventListener eventListener : this.getEventListeners())
            eventListener.onScreenSet(screen);
    }
    
    public void onTick(int tick) {
        for(EventListener eventListener : this.getEventListeners())
            eventListener.onTick(tick);
    }
    
    public void onIndex(int index) {
        for(EventListener eventListener : this.getEventListeners())
            eventListener.onIndex(index);
    }
}
