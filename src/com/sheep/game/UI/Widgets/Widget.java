package com.sheep.game.UI.Widgets;

import com.sheep.game.Game;
import com.sheep.game.UI.Menu;
import com.sheep.game.gfx.Screen;
import com.sheep.game.util.AudioPlayer;
import com.sheep.game.util.input.Mouse;

public abstract class Widget{
    protected int x, y, w, h;
    protected int paddingX, paddingY;

    Game game;

    Menu parent;
    AudioPlayer audio;

    boolean drawBackground;
    int backgroundColour;

    boolean drawBorder;
    int borderColour;

    boolean active;

    public Widget(int x, int y, int w, int h, Menu parent, Game game){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.parent = parent;
        this.audio = new AudioPlayer();
        this.active = true;

        Mouse.AddListener(this);
        this.game = game;
    }

    public abstract void OnClick(Game game);

    public void tick(){
    }

    public boolean isHovering(){
        int actualX = x - (w/2);
        int actualY = y - (h/2);
        return Mouse.getMouseX() > actualX && Mouse.getMouseX() < actualX+w
                && Mouse.getMouseY() > actualY && Mouse.getMouseY() < actualY+h && active;
    }

    public void render(Screen screen){
        if(drawBackground || drawBorder) {
            for (int y = this.y - (h / 2) -paddingX/2; y < this.y + (h / 2) + paddingY/2; y++) {
                for (int x = this.x - (w / 2) - paddingX/2; x < this.x + (w / 2) + paddingX/2; x++) {
                    if(x >= Game.WIDTH || x < 0 || y >= Game.HEIGHT || y < 0)
                        continue;

                    if(drawBackground)
                        screen.pixels[y * screen.getWidth() + x] = backgroundColour;
                    if(drawBorder)
                        if (x == this.x + (w / 2) + paddingX - 1 || x == this.x - (w / 2) - paddingX || y == this.y - (h / 2) - paddingY
                                || y == this.y + (h / 2) + paddingY - 1) {
                            screen.pixels[y * screen.getWidth() + x] = borderColour;
                        }
                }
            }
        }
    }

    public Widget setBackground(boolean drawBackground, int backgroundColour){
        this.drawBackground = drawBackground;
        this.backgroundColour = backgroundColour;
        return this;
    }

    public Widget setBorder(boolean drawBorder, int borderColour){
        this.drawBorder = drawBorder;
        this.borderColour = borderColour;
        return this;
    }

    public Widget setPadding(int paddingX, int paddingY){
        this.paddingX = paddingX;
        this.paddingY = paddingY;
        return this;
    }

    public Menu getParent() {
        return parent;
    }

    public Game getGame() {
        return game;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setH(int h) {
        this.h = h;
    }

    public AudioPlayer getAudio() {
        return audio;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }
}
