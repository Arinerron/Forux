package com.arinerron.forux.core;

public class FGame {
    private String name = "unknown";
    private String version = "1.0";
    
    private FWindow window = null;
    private FClock clock = null;
    
    public FGame(String name, String version) {
        this.name = name;
        this.version = version;
        
        this.window = new FWindow(this);
        this.clock = new FClock(this);
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getVersion() {
        return this.version;
    }
    
    public FWindow getWindow() {
        return this.window;
    }
    
    public FClock getClock() {
        return this.clock;
    }
}
