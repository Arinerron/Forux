package com.arinerron.forux.core;

import java.util.Iterator;
import java.util.HashMap;

import org.json.JSONObject;

public class Settings {
    private Game game = null;
    private JSONObject settings = null;
    
    protected Settings(Game game) {
        this.game = game;
        
        this.loadDefaults();
    }
    
    public void loadDefaults() {
        try {
            this.settings = new JSONObject(this.getGame().getResourceManager().readFile(this.getGame().getResourceManager().getConfigurationFile()));
        } catch(Exception e) {
            this.getGame().getLogger().error(e);
        }
    }
    
    public void saveDefaults() {
        try {
            this.getGame().getResourceManager().writeFile(this.getGame().getResourceManager().getConfigurationFile(), this.settings.toString(4));
        } catch(Exception e) {
            this.getGame().getLogger().error(e);
        }
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
    
    public float getFloat(String key) {
        return Float.parseFloat(this.getString(key));
    }
    
    public double getDouble(String key) {
        return Double.parseDouble(this.getString(key));
    }
    
    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(this.getString(key));
    }
    
    public Game getGame() {
        return this.game;
    }
}
