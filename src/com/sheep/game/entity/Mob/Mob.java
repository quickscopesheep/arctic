package com.sheep.game.entity.Mob;

import com.sheep.game.entity.Entity;
import com.sheep.game.gfx.Screen;
import com.sheep.game.item.Item;
import com.sheep.game.level.Level;

import java.awt.*;

public class Mob extends Entity {
    protected int dirX, dirY;

    protected float health;
    protected float maxHealth;
    float knockBackX, knockBackY, knockBackTime;

    Item item;

    public Mob(float x, float y, int xBound, int yBound, float maxHealth, Level level) {
        super(x, y, level);
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        dirX = 1;
        setxBound(xBound);
        setyBound(yBound);
    }

    public Mob(float x, float y, int xBound, int yBound, float maxHealth, float startHealth, Level level) {
        super(x, y, level);
        this.maxHealth = maxHealth;
        this.health = startHealth;
        dirX = 1;
        setxBound(xBound);
        setyBound(yBound);
    }

    public void useTool(){
        item.use();
    }

    public void equipItem(Item item){
        if(this.item != null)
            this.item.onUnequipped();
        this.item = item;
        this.item.onEquip(this);
    }

    @Override
    public void tick(){
        if(knockBackX > 1 || knockBackX < -1 || knockBackY > 1 || knockBackY < -1){
            float frameKnockBackX = knockBackX / knockBackTime;
            float frameKnockBackY = knockBackY / knockBackTime;

            move(frameKnockBackX, frameKnockBackY);

            knockBackX -= frameKnockBackX;
            knockBackY -= frameKnockBackY;
        }
    }

    @Override
    public void render(Screen screen) {

    }

    public void move(float ax, float ay){
        boolean collidingX = false;
        boolean collidingY = false;
        for (Entity e : level.getEntities()) {
            if(e != this){
                //world's most autistic way of doing it but cleanest
                collidingX = collision(e, ax, 0) || collidingX;
                collidingY = collision(e, 0, ay) || collidingY;
            }
        }

        if(!collision(ax, 0) && !collidingX){
            x += ax;
        }

        if(!collision(0, ay) && !collidingY){
           y += ay;
        }

        if(ax != 0){
            if(ax > 0) dirX = 1;
            if(ax < 0) dirX = -1;
        }
        if(ay != 0){
            if(ay > 0) dirY = 1;
            if(ay < 0) dirY = -1;
        }
    }

    @Override
    public void Damage(float damage, float knockBackX, float knockBackY, float knockBackTime){
        health -= damage;
        this.knockBackX = knockBackX;
        this.knockBackY = knockBackY;
        this.knockBackTime = knockBackTime;
        if(health <= 0) remove();
    }

    @Override
    public Rectangle GetDrawBounds() {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    public float getHealth(){
        return health;
    }

    public void setHealth(float health){
        this.health = health;
    }

    public float getMaxHealth() {
        return maxHealth;
    }

    public int getDirX() {
        return dirX;
    }

    public void setDirX(int dirX) {
        this.dirX = dirX;
    }

    public int getDirY() {
        return dirY;
    }

    public void setDirY(int dirY) {
        this.dirY = dirY;
    }

    public Item getItem() {
        return item;
    }
}
