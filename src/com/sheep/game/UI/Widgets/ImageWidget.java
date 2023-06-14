package com.sheep.game.UI.Widgets;

import com.sheep.game.Game;
import com.sheep.game.UI.Menu;
import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;

public class ImageWidget extends Widget{
    Sprite sprite;
    boolean flipped;

    public ImageWidget(int x, int y, Sprite sprite, Menu parent, Game game) {
        super(x, y, sprite.getWidth(), sprite.getHeight(), parent, game);
        this.sprite = sprite;
    }

    @Override
    public void render(Screen screen) {
        super.render(screen);
        screen.renderSprite(x-sprite.getWidth()/2, y - sprite.getHeight()/2,99, sprite, flipped, true);
    }

    @Override
    public void OnClick(Game game) {

    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public ImageWidget setFlipped(boolean flipped) {
        this.flipped = flipped;
        return this;
    }

    public boolean isFlipped() {
        return flipped;
    }
}
