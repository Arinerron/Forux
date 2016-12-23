package com.arinerron.forux.entities;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.UUID;

import org.json.JSONObject;

public class Entity {
    private String uuid = null;
    private String imagepath = null;
    private BufferedImage image = null;
    private int x = 0;
    private int y = 0;
    private String world = "default";
    private double health = 1;
    private boolean visible = true;

    public Entity() {
        this.uuid = UUID.randomUUID().toString().toUpperCase();
    }

    public JSONObject toJSON() {
        return new JSONObject().put("uuid", this.getUUID()).put("imagepath", this.getImagePath()).put("x", this.getX()).put("y", this.getY()).put("world", this.getWorld()).put("health", this.getHealth()).put("visible", this.isVisible());
    }

    @Override
    public String toString() {
        return this.toJSON().toString();
    }

    public static Entity loadEntity(JSONObject base) {
        Entity entity = new Entity();

        entity.uuid = base.getString("uuid");
        entity.imagepath = base.getString("imagepath");
        entity.x = base.getInt("x");
        entity.y = base.getInt("y");
        entity.world = base.getString("world");
        entity.health = base.getDouble("health");
        entity.visible = base.getBoolean("visible");

        return entity;
    }

    public static Entity loadEntity(String json) {
        return Entity.loadEntity(new JSONObject(json));
    }

    public void setImage(String imagepath, BufferedImage image) {
        this.imagepath = imagepath;
        this.image = image;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getUUID() {
        return this.uuid;
    }

    public String getImagePath() {
        return this.imagepath;
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public String getWorld() {
        return this.world;
    }

    public Point getLocation() {
        return new Point(this.getX(), this.getY());
    }

    public double getHealth() {
        return this.health;
    }

    public boolean isVisible() {
        return this.visible;
    }
}
