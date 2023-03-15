package com.sheep.game.level.tiles;

import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;
import com.sheep.game.level.Level;

public class VoidTile extends Tile{
    public VoidTile(){
        super(new Sprite(16, 16, 0xffffff));
    }

    @Override
    public void render(int x, int y, Screen screen, Level level) {
        screen.renderSprite(x, y,0, sprite, false, false);
    }

    @Override
    public boolean solid() {
        return false;
    }
}
