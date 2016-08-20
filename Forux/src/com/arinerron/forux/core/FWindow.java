package com.arinerron.forux.core;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class FWindow {
    private FGame game = null;
    protected JFrame frame = null;
    private List<FScreen> screens = new ArrayList<>();
    
    public FWindow(FGame game) {
        this.game = game;
        
        this.frame = new JFrame(this.getGame().getName());
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public FGame getGame() {
        return this.game;
    }
    
    public void setVisible(boolean visible) {
        this.frame.setVisible(visible);
    }
    
    public void setSize(int width, int height) {
        this.frame.setSize(width, height);
    }
    
    public void setFullscreen(boolean fullscreen) {
        if (fullscreen)
            this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        else
            this.frame.setExtendedState(JFrame.NORMAL);
    }
    
    public void addScreen(FScreen screen) {
        screen.setID(this.getScreens().size());
        this.screens.add(screen);
    }
    
    public boolean isVisible() { 
        return this.frame.isVisible();
    }
    
    public Dimension getSize() {
        return this.frame.getSize();
    }
    
    public boolean isFullscreen() {
        return this.frame.getExtendedState() == JFrame.MAXIMIZED_BOTH;
    }
    
    public List<FScreen> getScreens() {
        return this.screens;
    }
}
