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
    
    public Button() {
        this.setText("Change my text!");
    }
    
    @Override
    public void onDraw(Graphics g2) {
        int stroke = this.hasFocus() ? 2 : 1;
        BufferedImage image = new BufferedImage((int) (this.getWidth() + (stroke * 17.5)),
                this.getHeight() + (stroke * 2), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) g2;
        if (this.isEnabled())
            if (!this.hasFocus())
                g.setColor(Color.LIGHT_GRAY);
            else if (!this.enterDown)
                g.setColor(Color.decode("#e3e3e3"));
            else
                g.setColor(Color.DARK_GRAY);
        else
            g.setColor(Color.GRAY);
        RoundRectangle2D roundedRectangle = new RoundRectangle2D.Float(0, 0, this.getWidth(), this.getHeight(), 30, 30);
        g.fill(roundedRectangle);
        // g.fillRect(this.getX(), this.getY(), this.getWidth(),
        // this.getHeight());
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(stroke));
        g.draw(roundedRectangle);
        // g.drawRect(this.getX(), this.getY(), this.getWidth(),
        // this.getHeight());
        
        g.setColor(Color.BLACK);
        
        try {
            g.setFont(g.getFont().deriveFont(this.getHeight()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        g.drawString(this.getText(),
                this.getX() + 3 + (this.getWidth() / 2 - g.getFontMetrics().stringWidth(this.getText()) / 2),
                -2 + (int) (this.getY()
                        + ((double) (this.getHeight() / 2) + (double) (g.getFontMetrics().getHeight() / 2))));
        g2.drawImage(image, this.getX(), this.getY(), null);
        try {
            ImageIO.write(image, "jpg", new File(new File(System.getProperty("user.home")), "filex.jpg"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
