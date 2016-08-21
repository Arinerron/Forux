package com.arinerron.forux.core;

import java.awt.image.BufferedImage;
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
        
        this.timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(!Clock.this.getGame().isPaused())
                    if(Clock.this.getGame().isRunning())
                        Clock.this.update();
                    else {
                        Clock.this.timer.cancel();
                        Clock.this.timer = new Timer(); // reset game clock
                    }
            }
        }, delay, delay);
    }
    
    public void stop() {
        this.timer.cancel();
    }
    
    private void update() {
        BufferedImage image = new BufferedImage((int) this.getWindow().getFrameSize().getWidth(), (int) this.getWindow().getFrameSize().getHeight(), BufferedImage.TYPE_INT_ARGB);
        this.getWindow().getCurrentScreen().onDraw(image.getGraphics());
        this.getWindow().setImage(image);
        
        this.tick();
    }
    
    public Game getGame() {
        return this.game;
    }
    
    public Window getWindow() {
        return this.getGame().getWindow();
    }
    
    private int tick() {
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
