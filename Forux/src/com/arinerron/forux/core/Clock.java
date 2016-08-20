package com.arinerron.forux.core;

import java.util.Timer;
import java.util.TimerTask;

public class Clock {
    private Game game = null;
    private int ticks = 0;
    private int index = 0;
    private Timer timer = null;
    
    public Clock(Game game) {
        this.game = game;
    }
    
    protected void start() {
        this.timer = new Timer();
        
        int delay = (int) (1000 / this.getGame().getSettings().getInt("render_speed"));
        
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Clock.this.update(); // this is the only exception.
            }
        }, delay);
    }
    
    protected void update() { // you should NEVER call this function!
        
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
