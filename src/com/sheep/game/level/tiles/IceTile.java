package com.sheep.game.level.tiles;

import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;
import com.sheep.game.level.Level;

public class IceTile extends Tile{
    public IceTile() {
        super(null);
    }

    @Override
    public void render(int x, int y, Screen screen, Level level) {
        int i = ((y/16)%2)+((x/16)%2);
        switch (i){
            case 0 -> screen.renderSprite(x, y, Sprite.ice1, false, false);
            case 1 -> screen.renderSprite(x, y, Sprite.ice2, false, false);
            case 2 -> screen.renderSprite(x, y, Sprite.ice3, false, false);
        }
    }

    @Override
    public boolean solid() {
        return false;
    }
}
