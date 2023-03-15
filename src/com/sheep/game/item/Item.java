package com.sheep.game.item;

import com.sheep.game.Game;
import com.sheep.game.entity.Mob.Mob;
import com.sheep.game.gfx.Sprite;

public abstract class Item {
    boolean equipped;
    Mob owner;

    public Item(Mob owner){
        this.owner = owner;
    }

    public abstract void use();

    public void onEquip(Mob owner){
        equipped = true;
        this.owner = owner;
    }
    public void onUnequipped(){
        equipped = false;
        this.owner = null;
    }

    public abstract String getName();

    public abstract Sprite getIcon();

    public abstract Sprite getGfx();
}
