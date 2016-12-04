package com.arinerron.forux.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ResourceManager {
    private Game game = null;
    
    private File home = null;
    private File resources = null;
    private File images = null;
    private File audio = null;
    private File config = null;
    
    protected ResourceManager(Game game) {
        this.game = game;
        this.home = new File(new File(System.getProperty("user.home")), "." + this.getGame().getFileName());
        this.config = new File(home, "config");
        this.resources = new File(home, "resources");
        this.images = new File(home, "images");
        this.audio = new File(home, "audio");
        
        check();
    }
    
    public Game getGame() {
        return this.game;
    }
    
    private void check() {
        try {
            home.mkdirs();
            config.createNewFile();
            resources.mkdirs();
            images.mkdirs();
            audio.mkdirs();
        } catch (Exception e) {
            this.getGame().getLogger().error(e);
        }
    }
    
    public String readFile(File file) throws IOException {
        check();
        
        StringBuilder fileContents = new StringBuilder((int) file.length());
        Scanner scanner = new Scanner(file);
        
        try {
            while (scanner.hasNextLine())
                fileContents.append(scanner.nextLine() + "\n");
            
            return fileContents.toString();
        } finally {
            scanner.close();
        }
    }
    
    public void writeFile(File file, Object contents) throws IOException {
        check();
        
        PrintWriter writer = new PrintWriter(file, "UTF-8");
        writer.println(contents + "");
        writer.close();
    }
    
    public void appendFile(File file, Object contents) throws IOException {
        check();
        
        PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
        writer.println(contents + "");
        writer.close();
    }
    
    public File getHomeFolder() {
        check();
        return this.home;
    }
    
    public File getConfigurationFile() {
        check();
        return this.config;
    }
    
    public File getResourcesFolder() {
        check();
        return this.resources;
    }
    
    public File getImageResourceFolder() {
        check();
        return this.images;
    }
    
    public File getAudioResourceFolder() {
        check();
        return this.audio;
    }
    
    public File getImageResource(String filename) {
        check();
        return new File(this.getImageResourceFolder(), filename);
    }
    
    public File getAudioResource(String filename) {
        check();
        return new File(this.getAudioResourceFolder(), filename);
    }
}
