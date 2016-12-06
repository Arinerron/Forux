package com.arinerron.forux.screens;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.arinerron.forux.core.Screen;
import com.arinerron.forux.core.Window;
import com.arinerron.forux.entities.Entity;

public class GameScreen extends Screen {
    private List<Entity> entities = new ArrayList<>();
    private HashMap<Integer, BufferedImage> images = new HashMap<>();
    private Integer[][] oldtiles = new Integer[500][100];
    private Integer[][] tiles = new Integer[500][100]; // Integer[x][y]. An array of x positions
    private BufferedImage image = null;
    private boolean update = true;
    
    public GameScreen(Window window) {
        super(window);
    }
    
    private void updateImage() {
        BufferedImage image = new BufferedImage(tiles[0].length, tiles.length, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) image.getGraphics();
        
        if(this.oldtiles != this.getTiles()) {
            for(int y = 0; y < this.getTiles().length; y++)
                for(int x = 0; x < this.getTiles()[0].length; x++) {
                    Integer tile = this.getTiles()[y][x];
                    if(tile != null)
                        g.drawImage(images.get(tile), x, y, null);
                }
            
            this.oldtiles = this.getTiles();
        }
        
        for(Entity entity : this.getEntities())
            if(entity.isVisible() && entity.getImage() != null && entity.getX() < this.getTiles()[0].length && entity.getY() < this.getTiles().length)
                g.drawImage(entity.getImage(), 0, 0, null);
        
        this.image = image;
    }
    
    public void setTiles(Integer[][] tiles) {
        this.tiles = tiles;
        this.update();
    }
    
    public void setTile(int x, int y, int tile) {
        this.tiles[x][y] = tile;
        this.update();
    }
    
    public void removeTile(int x, int y) {
        this.tiles[x][y] = null;
        this.update();
    }
    
    public Point getFirst(int tile) {
        for(int y = 0; y < tiles.length; y++)
            for(int x = 0; x < tiles[0].length; x++)
                if(this.getTile(x, y) == tile)
                    return new Point(x, y);
        return null;
    }
    
    public Integer getTile(int x, int y) {
        return this.getTiles()[x][y];
    }
    
    public Integer[][] getTiles() {
        return this.tiles;
    }
    
    public void update() {
        this.updateImage();
    }
    
    public void addEntity(Entity entity) {
        this.entities.add(entity);
    }
    
    public boolean removeEntity(Entity entity) {
        return this.entities.remove(entity);
    }
    
    public boolean hasEntity(Entity entity) {
        return this.getEntities().contains(entity);
    }
    
    public List<Entity> getEntities() {
        return this.entities;
    }
    
    @Override
    public void onDraw(Graphics g) {
        if(this.update)
            g.drawImage(this.image, 0, 0, null);
    }
}
