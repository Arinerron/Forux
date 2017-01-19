package com.arinerron.forux.menu;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Button extends Component {
    private boolean enterDown = false;
    private ActionListener event = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO: Add multiple event listener support
        }
    };

    public Button(String s) {
        this.setText(s);
        this.setWidth(s.length() * 6 - 1 + 20);
        this.setHeight(7 + 20);
    }

    @Override
    public void onDraw(Graphics g) {
        if (this.isEnabled())
            if (!this.hasFocus())
                g.setColor(Color.LIGHT_GRAY);
            else if (!this.enterDown)
                g.setColor(Color.decode("#e3e3e3"));
            else
                g.setColor(Color.DARK_GRAY);
        else
            g.setColor(Color.GRAY);
        g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());

        /*
         * Hey! If you're here because you're trying to figure
         * out why strings aren't centering on buttons, it is
         * because I couldn't figure out why it was bugging,
         * and I got so tired of debugging. If you want to try
         * to figure out why yourself, good luck, and thanks.
         * The problem is coming from the lines below.
         */

        g.setColor(Color.WHITE);

        drawText(g, this.getX() + (this.getWidth() - (this.getText().length() * 6 - 1)) / 2, this.getY() + (this.getHeight() - 7) / 2);
    }

    @Override
    public void onKeyPress(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (this.hasFocus())
                this.press();
        }
    }

    @Override
    public void onKeyRelease(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            this.enterDown = false;
        }
    }

    public void press() {
        this.enterDown = true;
        this.event.actionPerformed(new ActionEvent(this, 0, "Enter pressed!"));
    }

    public ActionListener getEventListener() {
        return event;
    }

    public void setEventListener(ActionListener event) {
        this.event = event;
    }
}
