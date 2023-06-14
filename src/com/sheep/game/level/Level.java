package com.sheep.game.level;

import com.sheep.game.Game;
import com.sheep.game.entity.Entity;
import com.sheep.game.gfx.Screen;
import com.sheep.game.level.tiles.Tile;

import java.util.ArrayList;

public abstract class Level {
    protected int width, height;
    protected int tiles[];
    public int seed;

    Game game;

    ArrayList<Entity> entities = new ArrayList<>();
    ArrayList<Entity> entitiesToRemove = new ArrayList<>();
    ArrayList<Entity> entitiesToAdd = new ArrayList<>();

    int xScroll, yScroll;

    public Level(int width, int height, int seed, Game game){
        this.width = width;
        this.height = height;
        this.seed = seed;
        this.game = game;
        tiles = new int[width*height];
        generateLevel();
    }

    protected abstract void generateLevel();

    public void tick(){
        for (Entity e : entities) {
            e.tick();
        }

        entities.addAll(entitiesToAdd);
        entities.removeAll(entitiesToRemove);
        entitiesToAdd.clear();
        entitiesToRemove.clear();
    }

    public void render(Screen screen){
        screen.setOffset(xScroll, yScroll);
        int x0 = xScroll >> 4;
        int x1 = ((xScroll + screen.getWidth()) >> 4) + 16;
        int y0 = yScroll >> 4;
        int y1 = ((yScroll + screen.getHeight()) >> 4) + 16;

        for(int y = y0; y < y1; y++){
            for(int x = x0; x < x1; x++){
                getTile(x, y).render(x << 4, y << 4, screen, this);
            }
        }

        for (Entity e : entities) {
            if(screen.GetScreenRect().intersects(e.GetDrawBounds()))
                e.render(screen);
        }
    }

    public void AddEntity(Entity entity){
        entitiesToAdd.add(entity);
    }

    public void RemoveEntity(Entity entity){
        entitiesToRemove.add(entity);
    }

    public abstract Tile getTile(int x, int y);

    public void setTile(int x, int y, int t){
        if(x < 0 || x >= width || y < 0 || y >= height)
            return;

        tiles[y*width+x] = t;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Game getGame() {
        return game;
    }

    public int[] getTiles() {
        return tiles;
    }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public int getxScroll() {
        return xScroll;
    }

    public int getyScroll() {
        return yScroll;
    }

    public void setScroll(int x, int y){
        xScroll = x;
        yScroll = y;
    }
}
