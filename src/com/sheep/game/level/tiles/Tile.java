package com.sheep.game.level.tiles;

import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;
import com.sheep.game.level.Level;

public abstract class Tile {
    Sprite sprite;

    public static Tile voidTile = new VoidTile();
    public static Tile snowTile = new SnowTile();
    public static Tile iceTile = new IceTile();

    public Tile(Sprite sprite){
        this.sprite = sprite;
    }

    public abstract void render(int x, int y, Screen screen, Level level);

    public abstract boolean solid();
}
