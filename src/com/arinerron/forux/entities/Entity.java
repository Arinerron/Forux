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

    /**
     * Converts Entity to JSON
     *
     * @return the String representation of the Entity
     */
    public JSONObject toJSON() {
        return new JSONObject().put("uuid", this.getUUID()).put("imagepath", this.getImagePath()).put("x", this.getX()).put("y", this.getY()).put("world", this.getWorld()).put("health", this.getHealth()).put("visible", this.isVisible());
    }

    /**
     * Gets the String representation of Entity
     *
     * @return the String representation of the Entity
     */
    @Override
    public String toString() {
        return this.toJSON().toString();
    }

    /**
     * Converts JSON to Entity
     *
     * @param base the JSON Object to convert
     * @return the converted Entity
     */
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

    /**
     * Converts JSON to Entity
     *
     * @param json the JSON string to convert
     * @return the converted Entity
     */
    public static Entity loadEntity(String json) {
        return Entity.loadEntity(new JSONObject(json));
    }

    /**
     * Sets the Entity's image
     *
     * @param imagepath the path to the image
     * @param image the image to be rendered
     */
    public void setImage(String imagepath, BufferedImage image) {
        this.imagepath = imagepath;
        this.image = image;
    }

    /**
     * Sets the Entity's X position
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     *  Sets the Entity's Y position
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Sets the Entity's current world
     */
    public void setWorld(String world) {
        this.world = world;
    }

    /**
     * Sets the Entity's health
     */
    public void setHealth(double health) {
        this.health = health;
    }

    /**
     * Sets the visibility of the Entity
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    /**
     * Gets the UUID of the Entity
     *
     * @return the UUID of the Entity
     */
    public String getUUID() {
        return this.uuid;
    }

    /**
     * Gets the image path as a String
     *
     * @return the image path as a String
     */
    public String getImagePath() {
        return this.imagepath;
    }

    /**
     * Gets the image to be rendered
     *
     * @return the image to be rendered
     */
    public BufferedImage getImage() {
        return this.image;
    }

    /**
     * Gets the Entity's X position
     *
     * @return the Entity's X position
     */
    public int getX() {
        return this.x;
    }

    /**
     * Gets the Entity's Y position
     *
     * @return the Entity's Y position
     */
    public int getY() {
        return this.y;
    }

    /**
     * Gets the Entity's current world
     *
     * @return the Entity's current world
     */
    public String getWorld() {
        return this.world;
    }

    /**
     * Gets the Entity's location, stored in a Point Object
     *
     * @return the Entity's location
     */
    public Point getLocation() {
        return new Point(this.getX(), this.getY());
    }

    /**
     * Gets the Entity's health
     *
     * @return the Entity's health
     */
    public double getHealth() {
        return this.health;
    }

    /**
     * Gets the Entity's visibility
     *
     * @return the visibility of the Entity
     */
    public boolean isVisible() {
        return this.visible;
    }
}
