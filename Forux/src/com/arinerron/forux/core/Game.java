package com.arinerron.forux.core;

public class Game {
    private String name = "unknown";
    private String version = "1.0";
    
    private Window window = null;
    private Clock clock = null;
    private EventHandler eventHandler = null;
    private Settings settings = null;
    private boolean running = false;
    
    public Game(String name, String version) {
        this.name = name;
        this.version = version;
        
        this.window = new Window(this);
        this.clock = new Clock(this);
        this.eventHandler = new EventHandler(this);
        this.settings = new Settings(this);
    }
    
    public void start() {
        if(!this.isRunning()) {
            this.setRunning(true);
            
            this.getWindow().setVisible(true);
            this.getClock().start();
        }
    }
    
    public void setRunning(boolean running) {
        this.running = running;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getVersion() {
        return this.version;
    }
    
    public Window getWindow() {
        return this.window;
    }
    
    public Clock getClock() {
        return this.clock;
    }

    public EventHandler getEventHandler() {
        return this.eventHandler;
    }
    
    public Settings getSettings() {
        return this.settings;
    }
    
    public boolean isRunning() {
        return this.running;
    }
}
