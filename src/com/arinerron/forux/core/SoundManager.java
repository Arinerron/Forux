package com.arinerron.forux.core;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundManager {
    private Game game = null;
    private HashMap<String, String> soundfiles = new HashMap<String, String>();
    
    protected SoundManager(Game game) {
        this.game = game;
    }
    
    public Game getGame() {
        return this.game;
    }
    
    public String register(String sound, String path) {
        return this.getSounds().put(sound, path);
    }
    
    public String unregister(String sound) {
        return this.getSounds().remove(sound);
    }
    
    public void unregisterAll() {
        this.getSounds().clear();
    }
    
    public HashMap<String, String> getSounds() {
        return this.soundfiles;
    }
    
    public SoundPlayer getSound(String name) {
        if(!this.getSounds().containsKey(name) || !this.getGame().getResourceManager().getAudioResource(this.getSounds().get(name)).exists())
            return null;
        return new SoundPlayer(this, this.getGame().getResourceManager().getAudioResource(this.getSounds().get(name)));
    }
}