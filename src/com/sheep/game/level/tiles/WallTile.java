package com.sheep.game.level.tiles;

import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;
import com.sheep.game.level.Level;

public class WallTile extends Tile{
    public WallTile() {
        super(null);
    }

    @Override
    public void render(int x, int y, Screen screen, Level level) {
        screen.renderSprite(x, y,0, Sprite.wood_wall, false, false);
    }

    @Override
    public boolean solid() {
        return true;
    }
}
