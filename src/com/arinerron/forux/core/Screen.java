package com.arinerron.forux.core;

import java.awt.Graphics;

public abstract class Screen {
    private Window window = null;
    private int id = -1;
    private boolean active = false;
    
    public Screen(Window window) {
        this.window = window;
    }
    
    protected void setID(int id) {
        if(id == -1)
            this.id = id;
    }
    
    protected void setActive(boolean active) {
        this.active = active;
    }
    
    public Window getWindow() {
        return this.window;
    }
    
    public Game getGame() {
        return this.getWindow().getGame();
    }
    
    public Clock getClock() {
        return this.getGame().getClock();
    }
    
    public int getID() {
        return this.id;
    }
    
    public boolean isActive() {
        return this.active;
    }
    
    public abstract void onDraw(Graphics g);
    public abstract void onStart();
    public abstract void onStop();
}
