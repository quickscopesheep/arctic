package com.sheep.game.item.tool;

import com.sheep.game.entity.Entity;
import com.sheep.game.entity.Mob.Mob;
import com.sheep.game.entity.Node.Node;
import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;

import java.awt.*;

public class Pick extends HarvestTool{
    public Pick(Mob owner) {
        super(owner, Node.Type.ROCK, 1, 0.5f, 8, 25);
    }

    @Override
    public void use() {
        super.use();
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Screen screen) {

    }

    @Override
    public String getName() {
        return "Pick";
    }

    @Override
    public Sprite getIcon() {
        return Sprite.tool_pickaxe;
    }

    @Override
    public Sprite getGfx() {
        return Sprite.tool_pickaxe;
    }

    @Override
    public float StaminaCost() {
        return 0;
    }
}
