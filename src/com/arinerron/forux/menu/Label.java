package com.arinerron.forux.menu;

import java.awt.Graphics;

public class Label extends Component {
    public Label(String s) {
        super();
        this.setText(s);
    }
    
    public void onDraw(Graphics g) {
        drawText(g, this.getX(), this.getY(), 5);
    }
}
