package com.arinerron.forux.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.arinerron.forux.core.Screen;
import com.arinerron.forux.core.Window;

public class IntroScreen extends Screen {
    
    public IntroScreen(Window window) {
        super(window);
    }

    @Override
    public void onDraw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 500, 500);
        g.setColor(Color.BLUE);
        g.setFont(new Font(Font.SANS_SERIF, 30, 30));
        g.drawString("Example slide", 200, 200);
    }
    
    @Override
    public void onStart() {
        
    }
    
    @Override
    public void onStop() {
        
    }
    
}