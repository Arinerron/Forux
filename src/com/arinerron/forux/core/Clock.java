package com.arinerron.forux.core;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Clock {
    private Game game = null;
    private int ticks = 0;
    private int index = 0;
    private int renderspeed = 20;
    private int tickspeed = this.renderspeed;
    private boolean running = true;
    private Timer timer = null;

    protected Clock(Game game) {
        this.game = game;
    }

    protected synchronized void start() {
        this.timer = new Timer();

        int delay = (int) (1000 / this.getRenderSpeed());

        this.timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!Clock.this.getGame().isPaused() && Clock.this.running)
                    if (Clock.this.getGame().isRunning())
                        Clock.this.update();
                    else {
                        Clock.this.timer.cancel();
                        Clock.this.timer = new Timer(); // reset game clock
                    }
            }
        }, delay, delay);

        new Thread(new Runnable() {
            @Override
            public void run() {
                reload(); // if you want to know why I did this, just message Arinerron.
            }

            public void reload() {
                Clock.this.tick();

                if(Clock.this.getGame().isRunning() && running)
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            reload();
                        }
                    }, Clock.this.getTickSpeed());
            }
        }).start();
    }

    public synchronized void stop() {
        this.timer.cancel();
        this.running = false;
    }

    private void update() {
        BufferedImage image = new BufferedImage((int) this.getWindow().getImageSize().getWidth(),
                (int) this.getWindow().getImageSize().getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        this.getWindow().getCurrentScreen().onDraw(g);
        g.dispose();

        this.getWindow().setImage(image);
    }

    public void setRenderSpeed(int speed) {
        this.renderspeed = speed;
    }

    public void setTickSpeed(int speed) {
        this.tickspeed = speed;
    }

    public int getRenderSpeed() {
        return this.renderspeed;
    }

    public int getTickSpeed() {
        return this.tickspeed;
    }

    public String getTimestamp() {
        return new SimpleDateFormat("MM-dd-yyyy_HH-mm-ss").format(Calendar.getInstance().getTime());
    }

    public Game getGame() {
        return this.game;
    }

    public Window getWindow() {
        return this.getGame().getWindow();
    }

    private int tick() {
        this.ticks++;
        this.getGame().getWindow().getCurrentScreen().onTick(this.getTicks());
        this.getGame().getEventHandler().onTick(this.getTicks());
        return this.getTicks();
    }

    protected int index() {
        this.index = this.ticks;
        this.getGame().getEventHandler().onIndex(this.getIndex());
        return this.getIndex();
    }

    public int getTicks() {
        return this.ticks;
    }

    public int getIndex() {
        return this.index;
    }

    public int getTicksSinceIndex() {
        return this.getTicks() - this.getIndex();
    }
}
