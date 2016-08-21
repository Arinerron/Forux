package com.arinerron.forux.core;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Logger {
    public static int PRINT_TO_CONSOLE = 0;
    public static int PRINT_TO_FILE = 1;
    public static int PRINT_TO_CONSOLE_AND_FILE = 2;
    
    private Game game = null;
    private int type = Logger.PRINT_TO_CONSOLE_AND_FILE;    
    
    public Logger(Game game) {
        this.game = game;
    }
    
    private void log(Object o) {
        if(this.getLoggerType() % 2 == 0)
            System.out.println(o);
        if(this.getLoggerType() > 0)
            ;// make write to file later
    }
    
    public void log(String type, Object o) {
        log("[" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()) + type.toUpperCase() + "] " + o);
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
        log("ERROR", e.getMessage());
        e.printStackTrace();
    }
    
    public void setLoggerType(int type) {
        this.type = type;
    }
    
    public Game getGame() {
        return this.game;
    }
    
    public int getLoggerType() {
        return this.type;
    }
}
