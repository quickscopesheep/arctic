package com.sheep.game.gfx;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {
    private String path;
    public final int width, height;
    public int[] pixels;

    public static SpriteSheet world = new SpriteSheet("/sprites/iceTileset.png", 640, 288);
    public static SpriteSheet player = new SpriteSheet("/sprites/player.png", 96, 16);

    public static SpriteSheet fontmap = new SpriteSheet("/sprites/fontmap.png", 128, 128);
    public static SpriteSheet UI = new SpriteSheet("/sprites/UI.png", 128, 128);

    public SpriteSheet(String path, int width, int height){
        this.path = path;
        this.width = width;
        this.height = height;

        pixels = new int[width*height];
        load();
    }

    private void load(){
        try {
            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            int w = image.getWidth();
            int h = image.getHeight();

            image.getRGB(0, 0, w, h, pixels, 0, w);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
