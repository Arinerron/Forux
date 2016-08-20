package com.arinerron.forux.core;

import java.util.Timer;

public class Clock {
    private Game game = null;
    private int ticks = 0;
    private int index = 0;
    private Timer timer = new Timer();
    
    public Clock(Game game) {
        this.game = game;
    }
    
    protected void start() {
        
        timer.schedule(new TimerTask() {
            
        }, delay);
    }
    
    public Game getGame() {
        return this.game;
    }
    
    protected int tick() {
        this.ticks++;
        return this.getTicks();
    }
    
    protected int index() {
        this.index = this.ticks;
        return this.getIndex();
    }
    
    public int getTicks() {
        return this.ticks;
    }
    
    public int getIndex() {
        return this.index;
    }
    
    public int getTicksSinceIndex() {
        return this.getTicks() - this.getIndex();
    }
}
