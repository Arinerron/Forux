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
    
    public Map<String, String> getSounds() {
        return this.soundfiles;
    }
    
    public Sound getSound(String name) {
        if(!this.getSounds().containsKey(name) || !this.getGame().getResourceManager().getAudioResource(this.getSounds().get(name)).exists())
            return null;
        return new Sound(this, this.getGame().getResourceManager().getAudioResource(this.getSounds().get(name)));
    }
}

class Sound {
    private SoundManager manager = null;
    private File file = null;
    private Clip clip = null;
    private long position = 0;
    
    public Sound(SoundManager manager, File file) {
        this.manager = manager;
        this.file = file;
        
        try {
            this.clip = AudioSystem.getClip();
            this.clip.open(AudioSystem.getAudioInputStream(file.toURI().toURL().openStream()));
        } catch(Exception e) {
            this.getGame().getLogger().error(e);
        }
    }
    
    public void play() {
        clip.setMicrosecondPosition(position);
        clip.start();
    }
    
    public void stop() {
        this.position = 0;
        
        clip.stop();
        clip.flush();
    }
    
    public void restart() {
        stop();
        play();
    }
    
    public void loop() {
        this.stop();
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    public void pause() {
        this.position = clip.getMicrosecondPosition();
        
        clip.stop();
        clip.flush();
    }
    
    public void dispose() {
        try {
            clip.close();
        } catch(Exception e) {
            this.getGame().getLogger().error(e);
        } finally {
            clip = null;
        }
    }
    
    public boolean isPlaying() {
        return this.clip != null && this.clip.isRunning();
    }
    
    public Game getGame() {
        return this.getSoundManager().getGame();
    }
    
    public SoundManager getSoundManager() {
        return this.manager;
    }
    
    public File getFile() {
        return this.file;
    }
}
