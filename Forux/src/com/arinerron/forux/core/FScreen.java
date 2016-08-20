package com.arinerron.forux.core;

import java.awt.Graphics;

public abstract class FScreen {
    private FWindow window = null;
    private int id = -1;
    private boolean active = false;
    
    public FScreen(FWindow window) {
        this.window = window;
    }
    
    protected void setID(int id) {
        if(id == -1)
            this.id = id;
    }
    
    protected void setActive(boolean active) {
        this.active = active;
    }
    
    public FWindow getWindow() {
        return this.window;
    }
    
    public FGame getGame() {
        return this.getWindow().getGame();
    }
    
    public FClock getClock() {
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
