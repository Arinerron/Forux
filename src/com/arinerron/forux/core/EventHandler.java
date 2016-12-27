package com.arinerron.forux.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import com.arinerron.forux.events.EventListener;

public class EventHandler {
    private Game game = null;
    private List<EventListener> eventListeners = new ArrayList<>();

    protected EventHandler(Game game) {
        this.game = game;
    }

    /**
     * Gets the Game instance
     *
     * @returns the Game instance
     */
    public Game getGame() {
        return this.game;
    }

    /**
     * Gets a list of all of the EventListeners
     *
     * @return a List of EventListeners
     */
    public List<EventListener> getEventListeners() {
        return this.eventListeners;
    }

    /**
     * Registers an EventListener
     * <br>
     * If the EventListener has previously been registered, it will not reregister.
     * 
     * @param eventListener the EventListener to register
     * @return whether or not the EventListener was registered
     */
    public boolean registerListener(EventListener eventListener) {
        if (!this.getEventListeners().contains(eventListener))
            this.eventListeners.add(eventListener);
        else
            return false;
        return true;
    }

    /**
     * Unregisters an EventListener
     * <br>
     * If the EventListener wasn't registered, it will not unregister.
     *
     * @param eventListener the EventListener to unregister
     * @return whether or not the EventListener was unregistered
     */
    public boolean unregisterListener(EventListener eventListener) {
        if (this.getEventListeners().contains(eventListener))
            this.eventListeners.remove(eventListener);
        else
            return false;
        return true;
    }

    /**
     * Unregistes all EventListeners
     */
    public void unregisterAll() {
        this.eventListeners.clear();
    }

    /**
     * Has the EventListener previously been registered?
     *
     * @param eventListener the EventListener to check
     * @return whether or not the EventListener is registered
     */
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

    public void onGameError(String error) {
        for(EventListener eventListener : this.getEventListeners())
            eventListener.onGameError(error);
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

    public void onKeyPress(KeyEvent e) {
        for(EventListener eventListener : this.getEventListeners())
            eventListener.onKeyPress(e);
    }

    public void onKeyRelease(KeyEvent e) {
        for(EventListener eventListener : this.getEventListeners())
            eventListener.onKeyRelease(e);
    }
}
