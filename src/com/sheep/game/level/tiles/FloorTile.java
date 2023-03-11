package com.sheep.game.level.tiles;

import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;
import com.sheep.game.level.Level;

public class FloorTile extends Tile{
    public FloorTile() {
        super(null);
    }

    @Override
    public void render(int x, int y, Screen screen, Level level) {
        screen.renderSprite(x, y, Sprite.wood_floor, false, false);
    }

    @Override
    public boolean solid() {
        return false;
    }
}
