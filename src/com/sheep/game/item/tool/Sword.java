package com.sheep.game.item.tool;

import com.sheep.game.entity.Mob.Mob;
import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;

public class Sword extends Tool{
    public Sword(Mob owner) {
        super(owner);
    }

    @Override
    public void use() {

    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Screen screen) {

    }

    @Override
    public String getName() {
        return "Sword";
    }

    @Override
    public Sprite getIcon() {
        return Sprite.tool_sword;
    }

    @Override
    public Sprite getGfx() {
        return Sprite.tool_sword;
    }

    @Override
    public float getCooldown() {
        return 0;
    }

    @Override
    public float StaminaCost() {
        return 0;
    }
}
