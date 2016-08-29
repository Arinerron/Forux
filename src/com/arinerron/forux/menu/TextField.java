package com.arinerron.forux.menu;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class TextField extends Component {
    private String text = "";
    private String placeholder = "";
    private int maxlength = 10; // Set to stop text overlap
    private boolean unchanged = true;
    private boolean editable = true;
    private ActionListener event = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {}
    };
    
    /*
     * Constructor
     */
    public TextField() {}
    
    @Override
    public void onDraw(Graphics g2) {
        int stroke = this.hasFocus() ? 2 : 1;
        BufferedImage image = new BufferedImage((int) (this.getWidth() + (stroke * 17.5)),
                this.getHeight() + (stroke * 2), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) g2;
        if (this.isEnabled())
            if (this.hasFocus())
                g.setColor(Color.WHITE);
            else
                g.setColor(Color.LIGHT_GRAY);
        else
            g.setColor(Color.DARK_GRAY);
        g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(stroke));
        g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        
        if (unchanged)
            g.setColor(Color.GRAY);
        else
            g.setColor(Color.BLACK);
        
        try {
            g.setFont(g.getFont().deriveFont(this.getHeight()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        String ss = placeholder;
        if (this.getText().length() != 0)
            ss = this.getText();
        
        g.drawString(ss, this.getX() + 5, -2 + (int) (this.getY()
                + ((double) (this.getHeight() / 2) + (double) (g.getFontMetrics().getHeight() / 2)))); // THIS
                                                                                                       // FUNCTION
                                                                                                       // DRAWS
                                                                                                       // THE
                                                                                                       // TEXT.
                                                                                                       // YOU
                                                                                                       // MAY
                                                                                                       // HAVE
                                                                                                       // TO
                                                                                                       // CUSTOMIZE
                                                                                                       // IT
        g2.drawImage(image, 0, 0, null);
    }
    
    public void setPlaceholder(String text) {
        this.placeholder = text;
    }
    
    public void setText(String text) {
        super.setText(text);
        
        this.unchanged = false;
        this.getEventListener().actionPerformed(new ActionEvent(this, this.getText().length(), this.getText()));
    }
    
    @Override
    public void onMouseDown(int x, int y) {
        /*
         * System.out.println(x + " " + y); Rectangle coords = new
         * Rectangle(this.getX(), this.getY(), this.getWidth(),
         * this.getHeight()); if (coords.contains(x, y)) { this.requestFocus();
         * } else { this.looseFocus(); }
         */
    }
    
    @Override
    public void onKeyPress(KeyEvent e) { // TODO: Add numbers-only support,
                                         // etc....
        if (this.hasFocus() && this.isEnabled() && this.isEditable()) {
            if (this.getMaxLength() > this.getText().length()) {
                String validString = "^??????1!2\"??????3$4%5&6/7{(8[)9]=0}???????\\`??????q@we?????????rtzuiop??????*+~asdfghjkl????????????'#<>yxcvbnm??????;,:._- ";
                if (this.isPrintableChar(e.getKeyChar())
                        && validString.indexOf(String.valueOf(e.getKeyChar()).toLowerCase()) != -1) {
                    if (this.unchanged)
                        this.setText("");
                    
                    text += (e.getKeyChar() + "");
                    
                    if (this.getText().length() == 0)
                        this.unchanged = true;
                    else
                        this.unchanged = false;
                    
                    this.getEventListener()
                            .actionPerformed(new ActionEvent(this, this.getText().length(), this.getText()));
                } else {
                    if ((int) e.getKeyChar() == 8)
                        try {
                            System.out.println("BACKSPACE!");
                            text = text.substring(0, text.length() - 1);
                        } catch (Exception ee) {
                            ee.printStackTrace();
                        }
                }
            }
        }
    }
    
    public boolean isPrintableChar(char c) {
        Character.UnicodeBlock block = Character.UnicodeBlock.of(c);
        return (!Character.isISOControl(c)) && c != KeyEvent.CHAR_UNDEFINED && block != null
                && block != Character.UnicodeBlock.SPECIALS;
    }
    
    public ActionListener getEventListener() {
        return event;
    }
    
    public void setEventListener(ActionListener event) {
        this.event = event;
    }
    
    public boolean isEditable() {
        return editable;
    }
    
    public void setEditable(boolean editable) {
        this.editable = editable;
    }
    
    public int getMaxLength() {
        return maxlength;
    }
    
    public void setMaxLength(int maxlength) {
        this.maxlength = maxlength;
    }
    
}
