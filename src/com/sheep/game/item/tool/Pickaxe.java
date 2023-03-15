package com.sheep.game.item.tool;

import com.sheep.game.entity.Mob.Mob;
import com.sheep.game.gfx.Sprite;

public class Pickaxe extends Tool{
    public Pickaxe(Mob owner) {
        super(owner);
    }

    @Override
    public void use() {

    }

    @Override
    public String getName() {
        return "pickaxe";
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
