package com.arinerron.forux.core;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Screen {
    private Window window = null;
    private int id = -1;
    private boolean active = false;
    
    public Screen(Window window) {
        this.window = window;
        this.getWindow().addScreen(this);
    }
    
    public void setID(int id) {
        if (id == -1)
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
    
    public void onDraw(Graphics g) {
    }
    
    public void onMouseDown(int x, int y) {
    }
    
    public void onMouseUp(int x, int y) {
    }
    
    public void onMouseMotion(int x, int y) {
    }
    
    public void onKeyPress(KeyEvent e) {
    }
    
    public void onKeyRelease(KeyEvent e) {
    }
    
    public void onStop() {
    }
    
    public void onStart() {
    }
}