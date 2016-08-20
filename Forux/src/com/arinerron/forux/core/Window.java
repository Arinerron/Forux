package com.arinerron.forux.core;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class Window {
    private Game game = null;
    protected JFrame frame = null;
    private List<Screen> screens = new ArrayList<>();
    private int currentScreen = -1;
    
    public Window(Game game) {
        this.game = game;
        
        this.frame = new JFrame(this.getGame().getName());
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public Game getGame() {
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
    
    public boolean addScreen(Screen screen) {
        if(!this.getScreens().contains(screen)) {
            screen.setID(this.getScreens().size());
            this.screens.add(screen);
            return true;
        }
        
        return false;
    }
    
    public boolean removeScreen(Screen screen) {
        if(this.getScreens().contains(screen)) {
            this.screens.remove(screen);
            return true;
        }
        
        return false;
    }
    
    public boolean removeScreen(int id) {
        Screen remove = null;
        for(Screen screen : this.getScreens())
            if(screen.getID() == id)
                remove = screen;
        
        if(remove != null) {
            this.screens.remove(remove);
            return true;
        }
        
        return false;
    }
    
    /* 
     * Later:
     * public void getScreen(int id);
     * 
     */
    
    public Screen getCurrentScreen() {
        return this.getScreens().get(this.currentScreen);
    }
    
    public boolean setCurrentScreen(Screen screen) {
        return setCurrentScreen(screen.getID());
    }
    
    public boolean setCurrentScreen(int id) {
        for(int i = 0; i < this.getScreens().size(); i++)
            if(this.getScreens().get(i).getID() == id) {
                this.setCurrentScreen(i);
                return true;
            }
        
        return false;
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
    
    public List<Screen> getScreens() {
        return this.screens;
    }
}