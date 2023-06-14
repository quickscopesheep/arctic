package com.sheep.game.item;

import com.sheep.game.Game;
import com.sheep.game.entity.Mob.Mob;
import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;

public abstract class Item {
    protected boolean equipped;
    protected Mob owner;

    public Item(Mob owner){
        this.owner = owner;
    }

    public abstract void use();

    public abstract void tick();

    public abstract void render(Screen screen);

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

    public Mob getOwner() {
        return owner;
    }

    public abstract float getCooldown();
}
