package com.arinerron.forux.core;

import java.util.HashMap;

public class Settings {
    private Game game = null;
    private HashMap<String, Object> settings = new HashMap<>();
    
    protected Settings(Game game) {
        this.game = game;
        
        this.loadDefaults();
    }
    
    public void loadDefaults() {
        this.set("render_speed", 20); // per second
        this.set("max_fps", 40); // per second
    }
    
    public void set(String key, Object val) {
        this.settings.put(key, val);
    }
    
    public Object get(String key) {
        return settings.get(key);
    }
    
    public String getString(String key) {
        return "" + this.get(key);
    }
    
    public int getInt(String key) {
        return Integer.parseInt(this.getString(key));
    }
    
    public Game getGame() {
        return this.game;
    }
}
