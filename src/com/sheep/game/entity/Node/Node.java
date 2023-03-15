package com.sheep.game.entity.Node;

import com.sheep.game.entity.Entity;
import com.sheep.game.gfx.Screen;
import com.sheep.game.level.Level;

import java.awt.*;

public abstract class Node extends Entity {
    public float health;

    public Node(float startX, float startY, Level level, float startHealth) {
        super(startX, startY, level);
        health = startHealth;

        VerifiyPosition();
    }

    @Override
    public void Damage(float damage, float knockBackX, float knockBackY, float knockBackTime) {
        health -= damage;
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
}
