package com.sheep.game.level.tiles;

import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;
import com.sheep.game.level.Level;

import java.util.Random;

public class SnowTile extends Tile{
    Random random;

    public SnowTile() {
        super(null);
        random = new Random();
    }

    @Override
    public void render(int x, int y, Screen screen, Level level) {
        random.setSeed((long) y*x);
        switch ((x/16+y/16 + random.nextInt(0, 3))%4){
            case 0->{screen.renderSprite(x, y, Sprite.snow1, false, false);}
            case 1->{screen.renderSprite(x, y, Sprite.snow2, false, false);}
            case 2->{screen.renderSprite(x, y, Sprite.snow3, false, false);}
            case 3->{screen.renderSprite(x, y, Sprite.snow4, false, false);}
        }
    }

    @Override
    public boolean solid() {
        return false;
    }
}
