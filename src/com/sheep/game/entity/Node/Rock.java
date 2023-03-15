package com.sheep.game.entity.Node;

import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;
import com.sheep.game.level.Level;

import java.awt.*;

public class Rock extends Node{
    public Rock(float startX, float startY, Level level) {
        super(startX, startY, level, 100);
        setxBound(18);
        setyBound(18);
        setxBoundOffset(8);
        setyBoundOffset(14);
    }

    @Override
    public void VerifiyPosition() {

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Screen screen) {
        screen.renderSprite((int)x, (int)y, 1 , Sprite.rock, false, false);
    }

    @Override
    public Rectangle GetDrawBounds() {
        return new Rectangle((int)x, (int)y, 32, 32);
    }
}
