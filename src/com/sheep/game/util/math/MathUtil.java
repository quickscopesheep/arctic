package com.sheep.game.util.math;

public class MathUtil {
    public static float NormalizeX(float x, float y){
        float mag = (float) Math.sqrt((x*x)+(y*y));
        return x/mag;
    }

    public static float NormalizeY(float x, float y){
        float mag = (float) Math.sqrt((x*x)+(y*y));
        return y/mag;
    }

    public static float Distance(float x1, float y1, float x2, float y2){
        return (float) Math.sqrt((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1));
    }

    public static float lerp(float a, float b, float t) {
        return a + t * (b - a);
    }

    public static class Vector2{
        float x, y;

        public Vector2(float x, float y){
            this.x = x;
            this.y = y;
        }

        public static Vector2 Add(Vector2 a, Vector2 b){
            return new Vector2(a.x + b.x, a.y + b.y);
        }

        public static Vector2 Subtract(Vector2 a, Vector2 b){
            return new Vector2(a.x - b.x, a.y - b.y);
        }

        public float magnitude(){
            return (float) Math.sqrt((x*x)+(y*y));
        }

        public void normalize(){
            float x = this.x;
            float y = this.y;

            float mag = magnitude();

            this.x = x/mag;
            this.y = y/mag;
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
    }
}
