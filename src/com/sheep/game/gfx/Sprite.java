package com.sheep.game.gfx;

public class Sprite {
    private final int width, height;
    private int x, y;

    public int[] pixels;
    private SpriteSheet sheet;

    //util

    public static Sprite black = new Sprite(16, 16, 0x000000);
    public static Sprite purple = new Sprite(16, 16, 0xff00ff);

    //tiles

    public static Sprite snow1 = new Sprite(0, 64, 16, 16, SpriteSheet.world);
    public static Sprite snow2 = new Sprite(16, 64, 16, 16, SpriteSheet.world);
    public static Sprite snow3 = new Sprite(0, 80, 16, 16, SpriteSheet.world);
    public static Sprite snow4 = new Sprite(16, 80, 16, 16, SpriteSheet.world);

    public static Sprite ice1 = new Sprite(32, 64, 16, 16, SpriteSheet.world);
    public static Sprite ice2 = new Sprite(48, 64, 16, 16, SpriteSheet.world);
    public static Sprite ice3 = new Sprite(32, 80, 16, 16, SpriteSheet.world);

    public static Sprite wood_floor = new Sprite(464, 64, 16, 16, SpriteSheet.world);
    public static Sprite wood_wall = new Sprite(448, 80, 16, 16, SpriteSheet.world);
    public static Sprite wood_wall_side = new Sprite(464, 64, 16, 16, SpriteSheet.world);

    //nodes

    public static Sprite tree = new Sprite(0, 0, 32, 64, SpriteSheet.world);
    public static Sprite rock = new Sprite(64, 32, 32, 32, SpriteSheet.world);

    //player

    public static Sprite player_forward = new Sprite(0, 0, 16, 16, SpriteSheet.player);
    public static Sprite player_backward = new Sprite(16, 0, 16, 16, SpriteSheet.player);
    public static Sprite player_side = new Sprite(32, 0, 16, 16, SpriteSheet.player);

    public static Sprite player_forward_walk = new Sprite(3*16, 0, 16, 16, SpriteSheet.player);
    public static Sprite player_backward_walk = new Sprite(4*16, 0, 16, 16, SpriteSheet.player);
    public static Sprite player_side_walk = new Sprite(80, 0, 16, 16, SpriteSheet.player);

    //items

    public static Sprite tool_hammer = new Sprite(0, 0, 16, 16, SpriteSheet.items);
    public static Sprite tool_pickaxe = new Sprite(16, 0, 16, 16, SpriteSheet.items);

    //UI

    public static Sprite item_frame = new Sprite(16, 0, 16, 16, SpriteSheet.UI);

    public Sprite(int x, int y, int w, int h, SpriteSheet sheet){
        this.width = w;
        this.height = h;
        this.x = x;
        this.y = y;
        this.sheet = sheet;

        pixels = new int[w * h];
        load();
    }

    public Sprite(int w, int h, int Colour){
        width = w;
        height = h;
        pixels = new int[w * h];

        fillColour(Colour);
    }

    private void fillColour(int colour){
        for(int i = 0; i < width*height; i++){
                pixels[i] = colour;
        }
    }

    private void load(){
        for(int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                pixels[y*width+x] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.width];
            }
        }
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
}
