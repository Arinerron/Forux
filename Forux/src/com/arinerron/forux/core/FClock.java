package com.arinerron.forux.core;

public class FClock {
    private FGame game = null;
    private int ticks = 0;
    private int index = 0;
    
    public FClock(FGame game) {
        this.game = game;
    }
    
    public FGame getGame() {
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
