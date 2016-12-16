package com.arinerron.forux.core;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class ResourceManager {
    private Game game = null;

    private File home = null;
    private File config = null;
    private File screenshots = null;
    private File logs = null;
    private File log = null;
    private File resources = null;
    private File images = null;
    private File audio = null;

    private boolean working = false;

    protected ResourceManager(Game game) {
        this.game = game;
        this.home = new File(new File(System.getProperty("user.home")), "." + this.getGame().getFileName());
        this.config = new File(home, "settings.json");
        this.screenshots = new File(home, "screenshots");
        this.logs = new File(home, "logs");
        this.log = new File(logs, "log_" + this.getGame().getClock().getTimestamp() + ".txt");
        this.resources = new File(home, "resources");
        this.images = new File(this.resources, "images");
        this.audio = new File(this.resources, "audio");

        check();
    }

    public Game getGame() {
        return this.game;
    }

    private void check() {
        try {
            if(!home.exists())
                this.getGame().setFirstRun(true);
            home.mkdirs();
            if(!config.exists() && !working)
                writeFile(config, "{}");
            screenshots.mkdirs();
            logs.mkdirs();
            log.createNewFile();
            resources.mkdirs();
            images.mkdirs();
            audio.mkdirs();
        } catch (Exception e) {
            this.getGame().getLogger().error(e);
        }
    }

    public String readFile(File file) throws IOException {
        check();
        if(!file.exists())
            file.createNewFile();

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
        working = true;
        check();
        working = false;

        if(!file.exists())
            file.createNewFile();

        PrintWriter writer = new PrintWriter(file, "UTF-8");
        writer.println(contents + "");
        writer.close();
    }

    public void writeImage(File file, BufferedImage image) { // should throw Exception like above method?
        check();

        try {
            ImageIO.write(image, "jpg", file);
        } catch(Exception e) {
            this.getGame().getLogger().error(e);
        }
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

    public File getScreenshotsFolder() {
        check();
        return this.screenshots;
    }

    public File getLogsFolder() {
        check();
        return this.logs;
    }

    public File getLogFile() {
        check();
        return this.log;
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

    public BufferedImage getImageResource(String filename) {
        check();
        try {
            return ImageIO.read(new File(this.getImageResourceFolder(), filename));
        } catch (IOException e) {
            this.getGame().getLogger().error(e);
            return null;
        }
    }

    public File getAudioResource(String filename) {
        check();
        return new File(this.getAudioResourceFolder(), filename);
    }
}
