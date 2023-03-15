package com.sheep.game.item.tool;

import com.sheep.game.Game;
import com.sheep.game.entity.Mob.Mob;
import com.sheep.game.gfx.Sprite;

public class BuildingHammer extends Tool{
    public BuildingHammer(Mob owner) {
        super(owner);
    }

    @Override
    public void use() {

    }

    @Override
    public void onEquip(Mob owner) {
        super.onEquip(owner);
    }

    @Override
    public void onUnequipped() {
        super.onUnequipped();
    }

    @Override
    public String getName() {
        return "Building Hammer";
    }

    @Override
    public Sprite getIcon() {
        return Sprite.tool_hammer;
    }

    @Override
    public Sprite getGfx() {
        return Sprite.tool_hammer;
    }

    @Override
    public float StaminaCost() {
        return 0;
    }
}
