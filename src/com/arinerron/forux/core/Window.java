package com.arinerron.forux.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
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
    private JPanel panel;
    
    private int currentScreen = -1;
    
    protected Window(Game game) {
        this.game = game;
        
        this.frame = new JFrame(this.getGame().getName());
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setBackground(Color.BLACK);
        this.frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        
        this.panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                if(Window.this.getImage() != null)
                    g.drawImage(Window.this.getImage(), 0, 0, null); // later: center and size the image properly.
            }
        };
        
        this.frame.add(panel);
        
        Screen screen = new Screen(this) {
            public void onDraw(Graphics g) {}
            public void onStart() {}
            public void onStop() {}
        };
        
        this.addScreen(screen);
        this.currentScreen = 0; // black screen
    }
    
    public Game getGame() {
        return this.game;
    }
    
    public void setVisible(boolean visible) { // will start game if !running
        if(!this.getGame().isRunning())
            this.getGame().start();

        this.frame.setVisible(visible);
    }
    
    public void setSize(int width, int height) {
        panel.setPreferredSize(new Dimension(width, height));
        this.frame.pack();
    }
    
    public void setFullscreen(boolean fullscreen) {
        if (fullscreen)
            this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        else
            this.frame.setExtendedState(JFrame.NORMAL);
    }
    
    public void setLocation(int x, int y) {
        this.frame.setLocation(x, y);
    }
    
    public void setResizable(boolean resizable) {
        this.frame.setResizable(resizable);
    }
    
    public void setFrameSize(int width, int height) {
        this.frameSize = new Dimension(width, height);
    }
    
    protected void setImage(BufferedImage image) {
        boolean update = this.getImage() != image;
        this.image = image;
        
        if(update) // should it repaint, or did the image not change?
            
            panel.repaint();
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
        return setCurrentScreen(screen.getID());
    }
    
    public boolean setCurrentScreen(int id) {
        for(int i = 0; i < this.getScreens().size(); i++)
            if(this.getScreens().get(i).getID() == id) {
                this.getCurrentScreen().onStop();
                this.currentScreen = i + 1;
                this.getGame().getClock().index();
                this.getCurrentScreen().onStart();
                this.getGame().getEventHandler().onScreenSet(this.getScreens().get(i));
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
    
    public Point getLocation() {
        return this.frame.getLocation();
    }
    
    public boolean isResizable() {
        return this.frame.isResizable();
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