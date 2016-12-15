package com.arinerron.forux.core;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class SoundPlayer {
    private SoundManager manager = null;
    private File file = null;
    private Clip clip = null;
    private FloatControl controls = null;
    private long position = 0;

    public SoundPlayer(SoundManager manager, File file) {
        this.manager = manager;
        this.file = file;

        try {
            this.clip = AudioSystem.getClip();
            this.clip.open(AudioSystem.getAudioInputStream(file.toURI().toURL().openStream()));
            this.controls = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (Exception e) {
            this.getGame().getLogger().error(e);
        }
    }

    public void play() {
        this.clip.setMicrosecondPosition(position);
        this.clip.start();
    }

    public void stop() {
        this.position = 0;
        this.clip.stop();
        this.clip.flush();
    }

    public void restart() {
        stop();
        play();
    }

    public void loop() {
        this.stop();
        this.clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void pause() {
        this.position = clip.getMicrosecondPosition();

        this.clip.stop();
        this.clip.flush();
    }

    public void dispose() {
        try {
            this.clip.close();
        } catch (Exception e) {
            this.getGame().getLogger().error(e);
        } finally {
            this.clip = null;
        }
    }

    public void setVolume(float volume) {
        this.controls.setValue((float) Math.min(this.controls.getMaximum(), Math.max(this.controls.getMinimum(), volume)));
    }

    public float getVolume() {
        return this.controls.getValue();
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
