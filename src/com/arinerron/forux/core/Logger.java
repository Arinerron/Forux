package com.arinerron.forux.core;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Properties;

public class Logger {
    public static int PRINT_TO_CONSOLE = 0;
    public static int PRINT_TO_FILE = 1;
    public static int PRINT_TO_CONSOLE_AND_FILE = 2;

    private Game game = null;
    private int type = Logger.PRINT_TO_CONSOLE_AND_FILE;

    protected Logger(Game game) {
        this.game = game;
    }

    private void log(Object o) {
        if (this.getLoggerType() % 2 == 0)
            System.out.println(o);
        if (this.getLoggerType() > 0)
            try {
                this.getGame().getResourceManager().appendFile(this.getGame().getResourceManager().getLogFile(), o);
            } catch (Exception e) {
                this.setLoggerType(Logger.PRINT_TO_CONSOLE);
                this.error("Disabled logging to file due to file writing error");
                this.error(e);
                this.log(o);
            }
    }

    public void log(String type, Object o) {
        log("[" + new SimpleDateFormat("MM/dd/yyyy-HH:mm:ss").format(Calendar.getInstance().getTime()) + " "
                + type.toUpperCase() + "] " + o);
    }

    public void info(Object o) {
        log("INFO", o);
    }

    public void warn(Object o) {
        log("WARN", o);
    }

    public void error(Object o) {
        log("ERROR", o);
    }

    public void error(Exception e) {
        log("ERROR", e.toString());
        e.printStackTrace();
        this.getGame().getEventHandler().onGameError(e.toString());
    }

    public void setLoggerType(int type) {
        this.type = type;
    }

    public void printProperties() {
        String[] properties = {"os.name", "os.version", "os.arch", "java.version", "java.vendor"};

        for(String s : properties)
            this.info("System.getProperty\"" + s + "\") == " + System.getProperty(s));
    }

    public Game getGame() {
        return this.game;
    }

    public int getLoggerType() {
        return this.type;
    }
}
