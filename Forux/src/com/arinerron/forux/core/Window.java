package com.arinerron.forux.core;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window {
    private Game game = null;
    protected JFrame frame = null;
    private List<Screen> screens = new ArrayList<>();
    private Dimension frameSize = new Dimension(20, 20);
    private BufferedImage image = null;
    private int fps = ((1000 / this.getGame().getSettings().getInt("max_fps")));
    
    private int currentScreen = -1;
    
    public Window(Game game) {
        this.game = game;
        
        this.frame = new JFrame(this.getGame().getName());
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.add(new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                if(Window.this.getImage() != null)
                    g.drawImage(Window.this.getImage(), 0, 0, null); // later: center and size the image properly.
                
                try {
                    Thread.sleep(fps);
                } catch(Exception e) {
                    Window.this.getGame().getLogger().error(e); // later add this
                }
                
                if(Window.this.getGame().isRunning())
                    this.repaint();
            }
        });
        
        Screen screen = new Screen(this) {
            public void onDraw(Graphics g) {}
            public void onStart() {}
            public void onStop() {}
        };
        
        this.addScreen(screen);
        this.setCurrentScreen(screen); // black screen
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
    
    public void setFrameSize(int width, int height) {
        this.frameSize = new Dimension(width, height);
    }
    
    protected void setImage(BufferedImage image) {
        this.image = image;
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
    
    public int getScreenCount() {
        return this.getScreens().size();
    }
    
    public Screen getCurrentScreen() {
        return this.getScreens().get(this.currentScreen);
    }
    
    public boolean setCurrentScreen(Screen screen) {
        this.getCurrentScreen().onStop();
        boolean code = setCurrentScreen(screen.getID());
        this.getCurrentScreen().onStart();
        this.getGame().getEventHandler().onScreenSet(screen);
        
        return code;
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
    
    public Dimension getFrameSize() {
        return this.frameSize;
    }
    
    public BufferedImage getImage() {
        return this.image;
    }
}
