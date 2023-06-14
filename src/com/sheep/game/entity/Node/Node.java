package com.sheep.game.entity.Node;

import com.sheep.game.entity.Entity;
import com.sheep.game.entity.EntityFlags;
import com.sheep.game.gfx.Screen;
import com.sheep.game.level.Level;

public abstract class Node extends Entity {
    public float health;
    public Type type;

    public String dmg_sfx;

    public Node(float startX, float startY, Level level, float startHealth, Type type, String dmg_sfx) {
        super(startX, startY, level);
        health = startHealth;
        this.type = type;
        this.dmg_sfx = dmg_sfx;

        AddFlag(EntityFlags.NODE);

        VerifiyPosition();
    }

    @Override
    public void Damage(float damage, float knockBackX, float knockBackY, float knockBackTime) {
        health -= damage;
        if(health <= 0)
            remove();
    }

    public abstract void VerifiyPosition();

    @Override
    public void tick() {

    }

    @Override
    public void render(Screen screen) {

    }

    public float getHealth() {
        return health;
    }

    public enum Type {
        ROCK,
        TREE
    }
}
