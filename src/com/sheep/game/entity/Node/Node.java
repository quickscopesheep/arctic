package com.sheep.game.entity.Node;

import com.sheep.game.Game;
import com.sheep.game.anim.ISequence;
import com.sheep.game.anim.Sequencer;
import com.sheep.game.entity.Entity;
import com.sheep.game.entity.EntityFlags;
import com.sheep.game.gfx.Screen;
import com.sheep.game.level.Level;
import com.sheep.game.util.math.MathUtil;

public abstract class Node extends Entity {
    public float health;
    public Type type;

    public String dmg_sfx;

    Sequencer hitSequence;

    public Node(float startX, float startY, Level level, float startHealth, Type type, String dmg_sfx) {
        super(startX, startY, level);
        health = startHealth;
        this.type = type;
        this.dmg_sfx = dmg_sfx;

        AddFlag(EntityFlags.NODE);

        VerifiyPosition();

        hitSequence = new Sequencer(0.2f, false, new ISequence() {
            float dirX, dirY;

            float originalX, originalY;

            @Override
            public void OnInit(Sequencer sequencer) {
                originalX = x;
                originalY = y;
            }

            @Override
            public void OnPlay(Sequencer sequencer) {
                double dir = Game.RNG.nextFloat(2)*Math.PI;
                dirX = (float) Math.sin(dir);
                dirY = (float) Math.cos(dir);
            }

            @Override
            public void OnTick(Sequencer sequencer) {
                float amplitude = 3f;

                float t = (float)Math.sin(sequencer.getNormalizedTime()*2*(float)Math.PI);

                x = MathUtil.lerp(originalX, originalX + dirX*amplitude, t);
                y = MathUtil.lerp(originalY, originalY + dirY*amplitude, t);
            }

            @Override
            public void OnStop(Sequencer sequencer) {

            }
        });
    }

    @Override
    public void Damage(float damage, float knockBackX, float knockBackY, float knockBackTime) {
        health -= damage;
        hitSequence.Play();
        if(health <= 0)
            remove();
    }

    public abstract void VerifiyPosition();

    @Override
    public void tick() {
        hitSequence.Tick();
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
