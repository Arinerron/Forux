package com.arinerron.forux.menu;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.KeyEvent;

import com.arinerron.forux.core.Game;
import com.arinerron.forux.core.Screen;
import com.arinerron.forux.core.Window;
import com.arinerron.forux.screens.MenuScreen;

public class Component { // TODO: Add color changing support!
    static final int[] font = {
        0x00, 0x00, 0x00, 0x00, 0x00,// (space)
        0x00, 0x00, 0x5F, 0x00, 0x00,// !
        0x00, 0x07, 0x00, 0x07, 0x00,// "
        0x14, 0x7F, 0x14, 0x7F, 0x14,// #
        0x24, 0x2A, 0x7F, 0x2A, 0x12,// $
        0x23, 0x13, 0x08, 0x64, 0x62,// %
        0x36, 0x49, 0x55, 0x22, 0x50,// &
        0x00, 0x05, 0x03, 0x00, 0x00,// '
        0x00, 0x1C, 0x22, 0x41, 0x00,// (
        0x00, 0x41, 0x22, 0x1C, 0x00,// )
        0x08, 0x2A, 0x1C, 0x2A, 0x08,// *
        0x08, 0x08, 0x3E, 0x08, 0x08,// +
        0x00, 0x50, 0x30, 0x00, 0x00,// ,
        0x08, 0x08, 0x08, 0x08, 0x08,// -
        0x00, 0x60, 0x60, 0x00, 0x00,// .
        0x20, 0x10, 0x08, 0x04, 0x02,// /
        0x3E, 0x51, 0x49, 0x45, 0x3E,// 0
        0x00, 0x42, 0x7F, 0x40, 0x00,// 1
        0x42, 0x61, 0x51, 0x49, 0x46,// 2
        0x21, 0x41, 0x45, 0x4B, 0x31,// 3
        0x18, 0x14, 0x12, 0x7F, 0x10,// 4
        0x27, 0x45, 0x45, 0x45, 0x39,// 5
        0x3C, 0x4A, 0x49, 0x49, 0x30,// 6
        0x01, 0x71, 0x09, 0x05, 0x03,// 7
        0x36, 0x49, 0x49, 0x49, 0x36,// 8
        0x06, 0x49, 0x49, 0x29, 0x1E,// 9
        0x00, 0x36, 0x36, 0x00, 0x00,// :
        0x00, 0x56, 0x36, 0x00, 0x00,// ;
        0x00, 0x08, 0x14, 0x22, 0x41,// <
        0x14, 0x14, 0x14, 0x14, 0x14,// =
        0x41, 0x22, 0x14, 0x08, 0x00,// >
        0x02, 0x01, 0x51, 0x09, 0x06,// ?
        0x32, 0x49, 0x79, 0x41, 0x3E,// @
        0x7E, 0x11, 0x11, 0x11, 0x7E,// A
        0x7F, 0x49, 0x49, 0x49, 0x36,// B
        0x3E, 0x41, 0x41, 0x41, 0x22,// C
        0x7F, 0x41, 0x41, 0x22, 0x1C,// D
        0x7F, 0x49, 0x49, 0x49, 0x41,// E
        0x7F, 0x09, 0x09, 0x01, 0x01,// F
        0x3E, 0x41, 0x41, 0x51, 0x32,// G
        0x7F, 0x08, 0x08, 0x08, 0x7F,// H
        0x00, 0x41, 0x7F, 0x41, 0x00,// I
        0x20, 0x40, 0x41, 0x3F, 0x01,// J
        0x7F, 0x08, 0x14, 0x22, 0x41,// K
        0x7F, 0x40, 0x40, 0x40, 0x40,// L
        0x7F, 0x02, 0x04, 0x02, 0x7F,// M
        0x7F, 0x04, 0x08, 0x10, 0x7F,// N
        0x3E, 0x41, 0x41, 0x41, 0x3E,// O
        0x7F, 0x09, 0x09, 0x09, 0x06,// P
        0x3E, 0x41, 0x51, 0x21, 0x5E,// Q
        0x7F, 0x09, 0x19, 0x29, 0x46,// R
        0x46, 0x49, 0x49, 0x49, 0x31,// S
        0x01, 0x01, 0x7F, 0x01, 0x01,// T
        0x3F, 0x40, 0x40, 0x40, 0x3F,// U
        0x1F, 0x20, 0x40, 0x20, 0x1F,// V
        0x7F, 0x20, 0x18, 0x20, 0x7F,// W
        0x63, 0x14, 0x08, 0x14, 0x63,// X
        0x03, 0x04, 0x78, 0x04, 0x03,// Y
        0x61, 0x51, 0x49, 0x45, 0x43,// Z
        0x00, 0x00, 0x7F, 0x41, 0x41,// [
        0x02, 0x04, 0x08, 0x10, 0x20,// "\"
        0x41, 0x41, 0x7F, 0x00, 0x00,// ]
        0x04, 0x02, 0x01, 0x02, 0x04,// ^
        0x40, 0x40, 0x40, 0x40, 0x40,// _
        0x00, 0x01, 0x02, 0x04, 0x00,// `
        0x20, 0x54, 0x54, 0x54, 0x78,// a
        0x7F, 0x48, 0x44, 0x44, 0x38,// b
        0x38, 0x44, 0x44, 0x44, 0x20,// c
        0x38, 0x44, 0x44, 0x48, 0x7F,// d
        0x38, 0x54, 0x54, 0x54, 0x18,// e
        0x08, 0x7E, 0x09, 0x01, 0x02,// f
        0x08, 0x14, 0x54, 0x54, 0x3C,// g
        0x7F, 0x08, 0x04, 0x04, 0x78,// h
        0x00, 0x44, 0x7D, 0x40, 0x00,// i
        0x20, 0x40, 0x44, 0x3D, 0x00,// j
        0x00, 0x7F, 0x10, 0x28, 0x44,// k
        0x00, 0x41, 0x7F, 0x40, 0x00,// l
        0x7C, 0x04, 0x18, 0x04, 0x78,// m
        0x7C, 0x08, 0x04, 0x04, 0x78,// n
        0x38, 0x44, 0x44, 0x44, 0x38,// o
        0x7C, 0x14, 0x14, 0x14, 0x08,// p
        0x08, 0x14, 0x14, 0x18, 0x7C,// q
        0x7C, 0x08, 0x04, 0x04, 0x08,// r
        0x48, 0x54, 0x54, 0x54, 0x20,// s
        0x04, 0x3F, 0x44, 0x40, 0x20,// t
        0x3C, 0x40, 0x40, 0x20, 0x7C,// u
        0x1C, 0x20, 0x40, 0x20, 0x1C,// v
        0x3C, 0x40, 0x30, 0x40, 0x3C,// w
        0x44, 0x28, 0x10, 0x28, 0x44,// x
        0x0C, 0x50, 0x50, 0x50, 0x3C,// y
        0x44, 0x64, 0x54, 0x4C, 0x44,// z
        0x00, 0x08, 0x36, 0x41, 0x00,// {
        0x00, 0x00, 0x7F, 0x00, 0x00,// |
        0x00, 0x41, 0x36, 0x08, 0x00,// }
        0x08, 0x08, 0x2A, 0x1C, 0x08,// ~
    };
    private MenuScreen screen = null;

    private int id = -1;
    private int x = 0;
    private int y = 0;
    private int width = 0;
    private int height = 0;
    private boolean visible = true;
    private boolean focus = false;
    private boolean enabled = true;
    private String text = "";

    public static int CENTER = 0;
    public static int LEFT = 1;
    public static int RIGHT = 2;

    /*
     * Constructor
     */
    public Component() {}

    /*
     * Sets the current {@link MenuScreen} and the ID. Should never be called
     * directly.
     *
     * @param screen The MenuScreen to use
     * @param id The component ID
     * @see MenuScreen
     */
    public void setMenuScreen(MenuScreen screen, int id) { // DON'T CALL DIRECTLY!
        this.screen = screen;
        this.id = id;
    }

    /*
     * Returns the component ID
     */
    public int getID() {
        return this.id;
    }

    /*
     * Returns the {@link MenuScreen}
     *
     * @see MenuScreen
     */
    public Screen getMenuScreen() {
        return this.screen;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void requestFocus() {
        this.focus = true; // Don't use this function! IT WILL MESS EVERYTHING
                           // UP!
    }

    public void looseFocus() {
        this.focus = false;
    }

    public boolean hasFocus() {
        return this.focus;
    }

    public Game getGame() {
        return this.getMenuScreen().getGame();
    }

    public Window getWindow() {
        return this.getMenuScreen().getWindow();
    }

    public double height_count = 17.5; // i don't know how to implement this as of now.
    public void autoPosition(int type, int id) {

        /*
         * id is the element number in the array of components type is either
         * Component.CENTER, Component.LEFT, or Component.RIGHT. You should only
         * have to use Component.CENTER since you aren't using buttons, but for
         * buttons it is really useful.
         *
         * this.getWindow().getImageSize().getWidth() in this function is the
         * REAL width of the BufferedImage that gets stretched. padding is the
         * distance between each component vertically (And with buttons and the
         * Component.LEFT and Component.RIGHT horizontally padding, but ignore
         * that for now)
         *
         */
        try {
            int paddingY = (int) (this.getWindow().getImageSize().getHeight() / 30);
            int buttonWidth = (int) (this.getWindow().getImageSize().getWidth() / 1.2);
            int fx = (int) ((this.getWindow().getImageSize().getWidth() / 2) - (buttonWidth / 2));
            if (type != Component.CENTER)
                buttonWidth = (buttonWidth / 2) - (paddingY / 2);
            if (type == Component.RIGHT)
                fx = (buttonWidth) + (paddingY * 4);
            int buttonHeight = (int) (this.getWindow().getImageSize().getHeight() / height_count);

            int ys = paddingY;
            for(int d = 0; d < id + 1; d++) {
                if (type != Component.RIGHT && (d != id)) {
                    ys += paddingY;
                    ys += buttonHeight;
                } else {
                    // i dunno why this is empty and I can't remember.
                }
            }

            int fy = ys;

            this.setX(fx);
            this.setY(fy);
            this.setWidth(buttonWidth);
            this.setHeight(buttonHeight);
        } catch (Exception e) {

            System.err.println("Please add the Component to the Screen before calling the autoPosition function. Blame this error on @mysterywave, he wanted it to be like this :P");
            e.printStackTrace();
        }
    }

    public void drawText(Graphics g, int x1, int y1) {
        drawText(g, x1, y1, 1);
    }

    public void drawText(Graphics g, int x1, int y1, int size) {
        g.setColor(Color.WHITE);
        int len = this.getText().length();
        for(int i = 0; i < len; i++)
            for(int j = 0; j < 5; j++)
                for(int k = 0; k < 7; k++)
                    if(((font[(this.getText().charAt(i) - 32) * 5 + j] >> k) & 1) == 1)
                        g.fillRect((j + i * 6) * size + x1, k * size + y1, size, size);
    }

    public void onKeyPress(KeyEvent e) {}

    public void onKeyRelease(KeyEvent e) {}

    public void onDraw(Graphics g) {}

    public void onMouseDown(int x2, int y2) {}

    public void onMouseUp(int x2, int y2) {}

    public void onMouseMotion(int x2, int y2) {}

    public void onStart() {}

    public void onStop() {}
}
