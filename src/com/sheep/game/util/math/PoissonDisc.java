package com.sheep.game.util.math;

import com.sheep.game.Game;

import java.util.ArrayList;
import java.util.List;

public class PoissonDisc {
    public static List<MathUtil.Vector2> GeneratePoints(float radius, MathUtil.Vector2 sampleRegionSize){
        float cellSize = radius / (float)Math.sqrt(2);

        int[][] grid = new int[(int)Math.ceil(sampleRegionSize.x / cellSize)][(int)Math.ceil(sampleRegionSize.y / cellSize)];

        List<MathUtil.Vector2> points = new ArrayList<>();
        List<MathUtil.Vector2> spawnPoints = new ArrayList<>();

        //TODO: finnish this shit

        spawnPoints.add(new MathUtil.Vector2(sampleRegionSize.x/2, sampleRegionSize.y/2));
        while(spawnPoints.size() > 0){
            int spawnIndex = Game.RNG.nextInt(0, spawnPoints.size());
            MathUtil.Vector2 spawnCentre = spawnPoints.get(spawnIndex);

            boolean candidateAccepted = false;

            for(int i = 0; i < 30; i++){
                double angle = Game.RNG.nextFloat() * Math.PI * 2;
                MathUtil.Vector2 dir = new MathUtil.Vector2((float)Math.sin(angle), (float)Math.cos(angle));
                float ar = Game.RNG.nextFloat(radius, 2*radius);
                MathUtil.Vector2 candidate = new MathUtil.Vector2(spawnCentre.x + dir.x * ar, spawnCentre.y + dir.y * ar);

                if(isValid(candidate, sampleRegionSize, cellSize, radius, points, grid)){
                    points.add(candidate);
                    spawnPoints.add(candidate);
                    grid[(int)(candidate.x/cellSize)][(int)(candidate.y/cellSize)] = points.size();
                    candidateAccepted = true;
                    break;
                }
            }

            if(!candidateAccepted){
                spawnPoints.remove(spawnIndex);
            }
        }

        return points;
    }

    private static boolean isValid(MathUtil.Vector2 candidate, MathUtil.Vector2 sampleRegionSize, float cellSize, float radius, List<MathUtil.Vector2> points, int[][] grid){
        if(candidate.x >= 0 && candidate.x < sampleRegionSize.x && candidate.y >= 0 && candidate.y < sampleRegionSize.y){
            int cellX = (int)(candidate.x / cellSize);
            int cellY = (int)(candidate.y / cellSize);

            int searchStartX = Math.max(0, cellX - 2);
            int searchEndX = Math.min(cellX+2, ((int)Math.ceil(sampleRegionSize.x / cellSize))-1);
            int searchStartY = Math.max(0, cellY - 2);
            int searchEndY = Math.min(cellY+2, ((int)Math.ceil(sampleRegionSize.y / cellSize))-1);

            for(int x = searchStartX; x < searchEndX; x++){
                for(int y = searchStartY; y < searchEndY; y++){
                    int pointIndex = grid[x][y] - 1;
                    if(pointIndex != -1){
                        float dst = MathUtil.Vector2.Subtract(candidate, points.get(pointIndex)).magnitude();
                        if(dst < radius){
                            return false;
                        }
                    }
                }
            }

            return true;
        }
        return false;
    }
}
