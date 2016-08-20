package com.arinerron.forux.core;

import java.awt.Graphics;

public abstract class FScreen {
    public FWindow window = null;
    
    public FScreen(FWindow window) {
        this.window = window;
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
    
    public abstract void onDraw(Graphics g);
    
    public abstract void on
}
