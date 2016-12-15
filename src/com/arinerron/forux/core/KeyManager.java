package com.arinerron.forux.core;

import javax.swing.KeyStroke;
import java.util.ArrayList;
import java.util.List;

public class KeyManager {
    private Game game = null;
    private List<Integer> chars = new ArrayList<>();
    private List<Integer> buttons = new ArrayList<>();

    protected KeyManager(Game game) {
        this.game = game;
    }

    public List<Character> getPressedKeys() {
        List<Character> chars2 = new ArrayList<>();
        for(int c : chars)
            chars2.add(new Character((char)c));

        return chars2;
    }

    public List<Integer> getPressedMouseButtons() {
        return this.buttons;
    }

    public boolean isKeyPressed(char c) {
        return this.chars.contains(KeyStroke.getKeyStroke(c));
    }

    public boolean isKeyPressed(int c) {
        return this.chars.contains(c);
    }

    public boolean isMouseButtonPressed(int button) {
        return this.buttons.contains(button);
    }

    protected void onKeyPress(int c) {
        this.chars.add(c);
    }

    protected void onKeyRelease(int c) {
        this.chars.remove(Integer.valueOf(c));
    }

    protected void onMousePress(int b) {
        this.buttons.add(b);
    }

    protected void onMouseRelease(int b) {
        this.buttons.remove(Integer.valueOf(b));
    }

    public Game getGame() {
        return this.game;
    }
}
