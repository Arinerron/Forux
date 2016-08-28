package com.arinerron.forux.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.arinerron.forux.core.Window;
import com.arinerron.forux.core.menu.MenuScreen;

public class IntroScreen extends MenuScreen {
    
    public IntroScreen(Window window) {
        super(window);
    }

    @Override
    public void onDraw(Graphics g) {
        super.onDraw(g);
        
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 500, 500);
        g.setColor(Color.BLUE);
        g.setFont(new Font(Font.SANS_SERIF, 30, 30));
        g.drawString("Slide " + this.getClock().getTicksSinceIndex(), 200, 200);
    }
    
    @Override
    public void onStart() {
        
    }
    
    @Override
    public void onStop() {
        
    }
    
}
