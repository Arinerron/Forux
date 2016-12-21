package com.arinerron.forux.menu;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import com.arinerron.forux.core.Game;
import com.arinerron.forux.core.Screen;
import com.arinerron.forux.core.Window;
import com.arinerron.forux.screens.MenuScreen;

public class Component { // TODO: Add color changing support!
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
            int buttonHeight = (int) (this.getWindow().getImageSize().getHeight() / 17.5);

            int ys = paddingY;
            for(int d = 0; d < id + 1; d++) {
                if (type != Component.RIGHT && (d != id)) {
                    ys += paddingY;
                    ys += buttonHeight;
                } else {
                    // i dunno why thisi s empty and I can't remember.
                }
            }

            int fy = ys;

            this.setX(fx);
            this.setY(fy);
            this.setWidth(buttonWidth);
            this.setHeight(buttonHeight);
        } catch (Exception e) {

            System.err.println(
                    "Please add the Component to the Screen before calling the autoPosition function. Blame this error on @mysterywave, he wanted it to be like this :P");
            e.printStackTrace();
        }
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
