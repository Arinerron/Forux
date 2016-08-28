package com.arinerron.forux.core;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import com.arinerron.forux.core.menu.Button;
import com.arinerron.forux.core.menu.Component;

public class MenuScreen extends Screen {
    private Window window = null;
    private int id = -1;
    private boolean active = false;
    public final List<Component> components = new ArrayList<>();
    private int focus;
    
    public MenuScreen(Window window) {
        this.window = window;
        this.getWindow().addScreen(this);
    }
    
    public void setID(int id) {
        if (id == -1)
            this.id = id;
    }
    
    protected void setActive(boolean active) {
        this.active = active;
    }
    
    public Window getWindow() {
        return this.window;
    }
    
    public Game getGame() {
        return this.getWindow().getGame();
    }
    
    public Clock getClock() {
        return this.getGame().getClock();
    }
    
    public int getID() {
        return this.id;
    }
    
    public boolean isActive() {
        return this.active;
    }
    
    public Component getFocus() {
        return this.getComponents().get(this.focus);
    }
    
    public boolean setFocus(int id) {
        if(this.getComponent(id) != null) {
            if(this.getFocus() != null)
                this.getFocus().looseFocus();
            
            this.focus = id;
            this.getFocus().requestFocus();
            
            return true;
        }
        
        return false;
    }
    
    public Component getComponent(int id) {
        for(Component component : this.getComponents())
            if(component.getID() == id)
                return component;
        return null;
    }
    
    public boolean setFocus(Component component) {
        return this.setFocus(component);
    }
    
    public int add(Component component) {
        int id = this.getComponents().size();
        this.components.add(component);
        component.setScreen(this, id);
        
        return id;
    }
    
    public boolean remove(Component component) {
        return this.components.remove(component);
    }
    
    public List<Component> getComponents() {
        return this.components;
    }
    
    public void onDraw(Graphics g) {
        for (Component component : this.getComponents())
            if (component.isVisible())
                component.onDraw(g);
    }
    
    public void onMouseDown(int x, int y) {
        for (Component component : this.getComponents())
            component.onMouseDown(x, y);
    }
    
    public void onMouseUp(int x, int y) {
        for (Component component : this.getComponents())
            component.onMouseUp(x, y);
    }
    
    public void onMouseMotion(int x, int y) {
        for (Component component : this.getComponents())
            component.onMouseMotion(x, y);
    }
    
    public int getComponentCount() {
        return this.getComponents().size();
    }
    
    public void onKeyPress(KeyEvent e) {
        boolean run = true;
        
        if (this.getComponentCount() != 0) {
            if (e.getKeyCode() == KeyEvent.VK_TAB || e.getKeyCode() == KeyEvent.VK_DOWN
                    || (e.getKeyCode() == KeyEvent.VK_ENTER && !(this.getFocus() instanceof Button)) || (this.getFocus() instanceof Button && (e.getKeyCode() == KeyEvent.VK_RIGHT))) {
                if (focus >= this.getComponentCount() - 1)
                    this.setFocus(0);
                else
                    this.setFocus(focus + 1);
                if(this.getFocus() instanceof Button)
                    ((Button)this.getFocus()).press(); // TODO: how did it get here?
                run = false;
            } else if (e.getKeyCode() == KeyEvent.VK_UP || (this.getFocus() instanceof Button && (e.getKeyCode() == KeyEvent.VK_LEFT))) {
                if (focus <= 0)
                    if(this.getComponentCount() > 1)
                        this.setFocus(this.getComponents().size() - 1);
                else
                    if(this.getComponentCount() > 1)
                        this.setFocus(this.focus - 1);
                run = false;
            }
        }

        if (run)
            for (Component component : this.getComponents())
                component.onKeyPress(e);
    }

    public void onKeyRelease(KeyEvent e) {
        for (Component component : this.getComponents())
            component.onKeyRelease(e);
    }

    public void onStop() {
        for (Component component : this.getComponents())
            component.onStop();
    }

    public void onStart() {
        for (Component component : this.getComponents())
            component.onStart();
    }
}