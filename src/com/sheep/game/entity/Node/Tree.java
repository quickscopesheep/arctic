package com.sheep.game.entity.Node;

import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;
import com.sheep.game.level.Level;
import com.sheep.game.level.tiles.Tile;
import com.sheep.game.util.AudioPlayer;

import java.awt.*;

public class Tree extends Node{

    public Tree(float startX, float startY, Level level) {
        super(startX, startY, level, 100, Type.TREE, AudioPlayer.WOOD_CHOP);
        setxBound(20);
        setyBound(52);
        setxBoundOffset(6);
        setyBoundOffset(6);
    }

    @Override
    public void Damage(float damage, float knockBackX, float knockBackY, float knockBackTime) {
        super.Damage(damage, knockBackX, knockBackY, knockBackTime);
    }

    @Override
    public void VerifiyPosition() {
        if(level.getTile((int)(x + 16)/16, (int)(y+58)/16) == Tile.iceTile)
            remove();
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void render(Screen screen) {
        screen.renderSprite((int)x, (int)y, 1, Sprite.tree, false, false);
    }

    @Override
    public Rectangle GetDrawBounds() {
        return new Rectangle((int)x, (int)y, 32, 64);
    }
}
