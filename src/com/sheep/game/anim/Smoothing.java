package com.sheep.game.anim;

public class Smoothing {
    public static float smoothstep(float a, float b, float x){
        float t = (x - a) / (b - a);

        if(t > 1)
            t = 1;
        else if(t < 0)
            t = 0;

        return t * t * (3 - 2 * t);
    }
}
