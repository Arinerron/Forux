package com.arinerron.forux.core;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window {
    private Game game = null;
    protected JFrame frame = null;
    private List<Screen> screens = new ArrayList<>();
    private Dimension imageSize = new Dimension(20, 20);
    private BufferedImage image = null;
    private JPanel panel = null;
    
    private int currentScreen = -1;
    
    private int width = 0;
    private int height = 0;
    private int gapX = 0;
    private int gapY = 0;
    private double data = 0;
    private double nw = 0;
    private double nh = 0;
    
    protected Window(Game game) {
        this.game = game;
        
        this.frame = new JFrame(this.getGame().getName() + " " + this.getGame().getVersion());
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setBackground(Color.BLACK);
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(false);
        this.frame.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {
                Window.this.recalculate();
            }
            
            public void componentMoved(ComponentEvent e) {}
            
            public void componentShown(ComponentEvent e) {}
            
            public void componentHidden(ComponentEvent e) {}
        });
        
        this.panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                if (Window.this.getImage() != null)
                    g.drawImage(Window.this.getImage(), Window.this.gapX, Window.this.gapY, (int) Window.this.nw,
                            (int) Window.this.nh, null);
            }
        };
        this.panel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                Window.this.getGame().getKeyManager().onKeyPress(e.getKeyCode());
                Window.this.getCurrentScreen().onKeyPress(e);
                Window.this.getGame().getEventHandler().onKeyPress(e);
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                Window.this.getGame().getKeyManager().onKeyRelease(e.getKeyCode());
                Window.this.getCurrentScreen().onKeyRelease(e);
                Window.this.getGame().getEventHandler().onKeyRelease(e);
            }
            
            public void keyPressed(KeyEvent e) {}
        });
        this.panel.setFocusable(true);
        
        this.frame.add(panel);
        
        Screen screen = new Screen(this) {
            public void onDraw(Graphics g) {}
            
            public void onStart() {}
            
            public void onStop() {}
        };
        
        this.addScreen(screen);
        this.currentScreen = 0; // black screen
    }
    
    private void recalculate() {
        this.width = (int) this.getSize().getWidth();
        this.height = (int) this.getSize().getHeight();
        if (this.width > this.height) {
            this.data = height / this.getImageSize().getHeight();
            this.nw = (this.getImageSize().getWidth() * this.data);
            this.gapX = (int) ((this.width / 2) - (this.nw / 2));
            this.nh = height;
            this.gapY = 0;
        } else {
            this.data = width / this.getImageSize().getWidth();
            this.nh = (this.getImageSize().getHeight() * this.data);
            this.gapY = (int) ((this.height / 2) - (this.nh / 2));
            this.nw = width;
            this.gapX = 0;
        }
    }
    
    public Game getGame() {
        return this.game;
    }
    
    public void setIcon(BufferedImage image) {
        try {
            frame.setIconImage(image);
        } catch(Exception e) {
            this.getGame().getLogger().error(e);
        }
    }
    
    public void setVisible(boolean visible) {
        if (!this.getGame().isRunning())
            this.getGame().start();
        
        this.frame.setVisible(visible);
    }
    
    public void setSize(int width, int height) {
        this.panel.setPreferredSize(new Dimension(width, height));
        this.frame.pack();
        this.recalculate();
    }
    
    public void setFullscreen(boolean fullscreen) {
        if (fullscreen)
            this.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        else
            this.frame.setExtendedState(JFrame.NORMAL);
        
        this.recalculate();
    }
    
    public void setLocation(int x, int y) {
        this.frame.setLocation(x, y);
    }
    
    public void setResizable(boolean resizable) {
        this.frame.setResizable(resizable);
    }
    
    public void setImageSize(int width, int height) {
        this.imageSize = new Dimension(width, height);
        this.recalculate();
    }
    
    protected void setImage(BufferedImage image) {
        boolean update = this.getImage() != image;
        this.image = image;
        
        if (update && this.getGame().isRunning()) // should it repaint, or did the image not change?
            panel.repaint();
    }
    
    public boolean addScreen(Screen screen) {
        if (!this.getScreens().contains(screen)) {
            screen.setID(this.getScreens().size());
            this.screens.add(screen);
            return true;
        }
        
        return false;
    }
    
    public boolean removeScreen(Screen screen) {
        if (this.getScreens().contains(screen)) {
            this.screens.remove(screen);
            return true;
        }
        
        return false;
    }
    
    public boolean removeScreen(int id) {
        Screen remove = null;
        for(Screen screen : this.getScreens())
            if (screen.getID() == id)
                remove = screen;
        
        if (remove != null) {
            this.screens.remove(remove);
            return true;
        }
        
        return false;
    }
    
    /*
     * Later: public void getScreen(int id);
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
            if (this.getScreens().get(i).getID() == id) {
                this.getCurrentScreen().onStop();
                this.currentScreen = i + 1;
                this.getGame().getClock().index();
                this.getCurrentScreen().onStart();
                this.getGame().getEventHandler().onScreenSet(this.getScreens().get(i));
                return true;
            }
        
        return false;
    }
    
    public BufferedImage getIcon() {
        return (BufferedImage) this.frame.getIconImage();
    }
    
    public boolean isVisible() {
        return this.frame.isVisible();
    }
    
    public Dimension getSize() {
        return this.panel.getSize();
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
    
    public Dimension getImageSize() {
        return this.imageSize;
    }
    
    public BufferedImage getImage() {
        return this.image;
    }
}
