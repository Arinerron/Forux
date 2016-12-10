package com.arinerron.forux.core;

import javax.swing.KeyStroke;
import java.util.ArrayList;
import java.util.List;

public class KeyManager {
    private Game game = null;
    private List<Integer> chars = new ArrayList<>();
    
    protected KeyManager(Game game) {
        this.game = game;
    }
    
    public List<Character> getPressedKeys() {
        List<Character> chars2 = new ArrayList<>();
        for(int c : chars)
            chars2.add(c);
        
        return this.chars2;
    }
    
    public boolean isKeyPressed(char c) {
        return this.chars.contains(KeyStroke.getKeyStroke(c));
    }
    
    public boolean isKeyPressed(int c) {
        return this.chars.contains(c);
    }
    
    protected void onKeyPress(int c) {
        this.chars.add(c);
    }
    
    protected void onKeyRelease(int c) {
        this.chars.remove(c);
    }
    
    public Game getGame() {
        return this.game;
    }
}
