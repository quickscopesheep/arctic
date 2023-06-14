package com.sheep.game.level;

import com.sheep.game.Game;
import com.sheep.game.entity.Node.Rock;
import com.sheep.game.entity.Node.Tree;
import com.sheep.game.level.tiles.Tile;
import com.sheep.game.util.math.MathUtil;
import com.sheep.game.util.math.NoiseGenerator;
import com.sheep.game.util.math.PoissonDisc;

import java.util.List;
import java.util.Random;

public class ArcticLevel extends Level{
    public ArcticLevel(int width, int height, int seed, Game game){
        super(width, height, seed, game);
    }

    @Override
    protected void generateLevel() {
        Random random = new Random(seed);
        NoiseGenerator noiseGenerator = new NoiseGenerator(seed, 5, .5f, 2, 1, 10);

        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                if(noiseGenerator.generateHeight(x, y) > 3)
                    tiles[y*width+x] = 1;
                else
                    tiles[y*width+x] = 0;
            }
        }

        List<MathUtil.Vector2> treeSpawns = PoissonDisc.GeneratePoints(4, new MathUtil.Vector2(width, height));

        for(MathUtil.Vector2 spawn : treeSpawns){
            int node = random.nextInt(10);

            if(MathUtil.Distance(spawn.getX(), spawn.getY(), width/2f, height/2f) < 5)
                continue;

            if(node > 7)
                AddEntity(new Rock(spawn.getX()*16, spawn.getY()*16, this));
            else
                AddEntity(new Tree(spawn.getX()*16, spawn.getY()*16, this));
        }
    }

    @Override
    public Tile getTile(int x, int y) {
        if(x < 0 || x > width - 1 || y < 0 || y > height - 1)
            return Tile.voidTile;

        return switch (tiles[y * width + x]) {
            case 0 -> Tile.snowTile;
            case 1 -> Tile.iceTile;
            case 2 -> Tile.floorTile;
            default -> Tile.voidTile;
        };
    }
}
