package com.arinerron.forux.core;

public final class Game { // Game.java
    private String name = "unknown";
    private String file_name = "unknown";
    private String version = "1.0";

    private Window window = null;
    private Clock clock = null;
    private EventHandler eventHandler = null;
    private Settings settings = null;
    private Logger logger = null;
    private ResourceManager resourcemanager = null;
    private SoundManager soundmanager = null;
    private KeyManager keymanager = null;

    private boolean running = false;
    private boolean paused = false;

    public Game(String name, String version) {
        this.name = name;
        this.file_name = name.replace(" ", "_").toLowerCase();
        this.version = version;

        this.eventHandler = new EventHandler(this);
        this.resourcemanager = new ResourceManager(this);
        this.settings = new Settings(this);
        this.logger = new Logger(this);
        this.window = new Window(this);
        this.clock = new Clock(this);
        this.soundmanager = new SoundManager(this);
        this.keymanager = new KeyManager(this);

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                Game.this.getEventHandler().onGameStop();
            }
        }));
    }

    public synchronized void start() {
        if (!this.isRunning()) {
            this.setRunning(true);
            this.setPaused(false);

            this.getWindow().setVisible(true);
            this.getClock().start();
            this.getEventHandler().onGameStart();
        }
    }

    public synchronized void stop() {
        if (this.isRunning()) {
            this.setRunning(false);
            this.setPaused(false);

            this.getWindow().setVisible(false);
            this.getClock().stop();
            this.getEventHandler().onGameStop();

        }
    }

    private void setRunning(boolean running) {
        this.running = running;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public String getName() {
        return this.name;
    }

    public String getFileName() {
        return this.file_name;
    }

    public String getVersion() {
        return this.version;
    }

    public Window getWindow() {
        return this.window;
    }

    public Clock getClock() {
        return this.clock;
    }

    public EventHandler getEventHandler() {
        return this.eventHandler;
    }

    public Settings getSettings() {
        return this.settings;
    }

    public Logger getLogger() {
        return this.logger;
    }

    public ResourceManager getResourceManager() {
        return this.resourcemanager;
    }

    public SoundManager getSoundManager() {
        return this.soundmanager;
    }

    public KeyManager getKeyManager() {
        return this.keymanager;
    }

    public boolean isRunning() {
        return this.running;
    }

    public boolean isPaused() {
        return this.paused;
    }
}
