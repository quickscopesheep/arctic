package com.sheep.game.entity;

import com.sheep.game.gfx.Screen;
import com.sheep.game.level.Level;

import java.awt.*;

public abstract class Entity {
    protected float x, y;
    protected int xBound, yBound;
    protected int xBoundOffset, yBoundOffset;

    protected int flags;

    protected Level level;
    protected boolean removed;

    public Entity(float startX, float startY, Level level){
        this.x = startX;
        this.y = startY;
        this.level = level;
    }

    public abstract void tick();
    public abstract void render(Screen screen);

    public abstract void Damage(float damage, float knockBackX, float knockBackY, float knockBackTime);

    public boolean collision(Entity entity, float mx, float my){
        return GetBounds(Math.round(mx), Math.round(my)).intersects(entity.GetBounds());
    }

    protected boolean collision(float ax, float ay){
        float x0 = x + ax + xBoundOffset + -xBound/2f;
        float x1 = x + ax + + yBoundOffset + xBound/2f;
        float y0 = y + ay + + yBoundOffset + -yBound/2f;
        float y1 = y + ay + xBoundOffset + yBound/2f;

        return level.getTile((int) (x0 / 16), (int) (y0 / 16)).solid()||
                level.getTile((int) (x0 / 16), (int) (y1 / 16)).solid() ||
                level.getTile((int) (x1 / 16), (int) (y0 / 16)).solid() ||
                level.getTile((int) (x1 / 16), (int) (y1 / 16)).solid();
    }

    public void remove(){
        removed = true;
        level.RemoveEntity(this);
    }

    public void AddFlag(int flag){
        flags |= flag;
    }

    public boolean HasFlag(int flag){
        return (flags & flag) == flag;
    }

    public Rectangle GetBounds(){
        return new Rectangle((int)x + xBoundOffset, (int)y + yBoundOffset, xBound, yBound);
    }

    public abstract Rectangle GetDrawBounds();

    public Rectangle GetBounds(int offsetX, int offsetY){
        return new Rectangle((int)x + xBoundOffset + offsetX, (int)y + yBoundOffset + offsetY, xBound, yBound);
    }

    public Level getLevel() {
        return level;
    }

    public boolean isRemoved() {
        return removed;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getxBound() {
        return xBound;
    }

    public void setxBound(int xBound) {
        this.xBound = xBound;
    }

    public int getyBound() {
        return yBound;
    }

    public int getFlags() {
        return flags;
    }

    public void setyBound(int yBound) {
        this.yBound = yBound;
    }

    public void setxBoundOffset(int xBoundOffset) {
        this.xBoundOffset = xBoundOffset;
    }

    public void setyBoundOffset(int yBoundOffset) {
        this.yBoundOffset = yBoundOffset;
    }
}
