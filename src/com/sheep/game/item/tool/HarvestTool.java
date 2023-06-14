package com.sheep.game.item.tool;

import com.sheep.game.entity.Entity;
import com.sheep.game.entity.EntityFlags;
import com.sheep.game.entity.Mob.Mob;
import com.sheep.game.entity.Node.Node;
import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;
import com.sheep.game.util.AudioPlayer;
import com.sheep.game.util.input.Keyboard;

import java.awt.*;

public class HarvestTool extends Tool{
    public Node.Type harvestType;
    public float staminaCost;
    public float cooldown;
    public int reach;
    public int damage;

    int inputX, inputY;

    AudioPlayer nodeDmgSfx;

    public HarvestTool(Mob owner, Node.Type harvestType, float staminaCost, float cooldown, int reach, int damage) {
        super(owner);
        this.harvestType = harvestType;
        this.staminaCost = staminaCost;
        this.cooldown = cooldown;
        this.reach = reach;
        this.damage = damage;

        nodeDmgSfx = new AudioPlayer();
    }

    @Override
    public void use() {
        for(Entity e : owner.getLevel().getEntities()){
            if(!e.HasFlag(EntityFlags.NODE))
                continue;

            if(((Node)e).type != harvestType)
                continue;

            int hitX = (int)owner.getX()+8 + inputX*reach;
            int hitY = (int)owner.getY()+8 + inputY*reach;

            if(e.GetBounds().intersects(new Rectangle(hitX-8, hitY-8, 16, 16))){
                e.Damage(damage, 0, 0, 0);
                nodeDmgSfx.loadSound(((Node)e).dmg_sfx);
                nodeDmgSfx.play();
            }
        }
    }

    @Override
    public void tick() {
        int _inputX = 0;
        int _inputY = 0;
        if(Keyboard.UP_DOWN) _inputY = -1;
        if(Keyboard.DOWN_DOWN) _inputY = 1;
        if(Keyboard.LEFT_DOWN) _inputX = -1;
        if(Keyboard.RIGHT_DOWN) _inputX = 1;

        if(_inputX != 0 || _inputY != 0){
            inputX = _inputX;
            inputY = _inputY;
        }
    }

    @Override
    public void render(Screen screen) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Sprite getIcon() {
        return null;
    }

    @Override
    public Sprite getGfx() {
        return null;
    }

    @Override
    public float StaminaCost() {
        return staminaCost;
    }

    @Override
    public float getCooldown() {
        return cooldown;
    }
}
