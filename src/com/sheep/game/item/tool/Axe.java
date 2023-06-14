package com.sheep.game.item.tool;

import com.sheep.game.entity.Mob.Mob;
import com.sheep.game.entity.Node.Node;
import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;

public class Axe extends HarvestTool{
    public Axe(Mob owner) {
        super(owner, Node.Type.TREE, 1, 0.5f, 20, 25);
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
        return "Axe";
    }

    @Override
    public Sprite getIcon() {
        return Sprite.tool_axe;
    }

    @Override
    public Sprite getGfx() {
        return Sprite.tool_axe;
    }

    @Override
    public float StaminaCost() {
        return 0;
    }
}