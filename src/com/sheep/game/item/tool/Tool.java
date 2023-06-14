package com.sheep.game.item.tool;

import com.sheep.game.entity.Mob.Mob;
import com.sheep.game.item.Item;

public abstract class Tool extends Item {
    public Tool(Mob owner) {
        super(owner);
    }

    public abstract float StaminaCost();
}
