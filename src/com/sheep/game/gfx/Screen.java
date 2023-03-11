package com.sheep.game.gfx;

import java.awt.*;
import java.util.Arrays;

public class Screen {
    private final int width, height;
    public int[] pixels;

    public int xOffset, yOffset;

    Sprite[] fontmap;

    String fontREGEX = "abcdefghijklmnopqrstuvwxyz0123456789:,.! ";

    public Screen(int width, int height){
        this.width = width;
        this.height = height;
        pixels = new int[width * height];

        generateFontSprites(SpriteSheet.fontmap, 8);
    }

    void generateFontSprites(SpriteSheet sheet, int charSize){
        fontmap = new Sprite[fontREGEX.length()];

        for(int i = 0; i < fontmap.length; i++){
            int spriteX = (i*charSize) % sheet.width;
            int spriteY = ((i*charSize) / sheet.width)*charSize;
            fontmap[i] = new Sprite(spriteX, spriteY, charSize, charSize, sheet);
        }
    }

    public void renderSprite(int xp, int yp, Sprite sprite, boolean flipped, boolean fixed){
        if(!fixed){
            xp -= xOffset;
            yp -= yOffset;
        }
        for(int y = 0; y < sprite.getHeight(); y++){
            int ya = y + yp;

            for(int x = 0; x < sprite.getWidth(); x++){
                int xa = x + xp;
                int xs;
                if(flipped)
                    xs = (sprite.getWidth() -1) - x;
                else
                    xs = x;

                if(xa < -sprite.getWidth() || xa > width || ya < 0 || ya > width) break;
                if(xa < 0) continue;
                if(xa > width - 1) continue;
                if(ya > height - 1) continue;

                int col = sprite.pixels[y * sprite.getWidth() + xs];
                if(col != 0xffff00ff)
                    pixels[ya * width + xa] = col;
            }
        }
    }

    public void renderColouredSprite(int xp, int yp, Sprite sprite, int colour, boolean flipped, boolean fixed) {
        if (!fixed){
            xp -= xOffset;
            yp -= yOffset;
        }

        for(int y = 0; y < sprite.getHeight(); y++){
            int ya = y + yp;

            for(int x = 0; x < sprite.getWidth(); x++){
                int xa = x + xp;
                int xs;
                if(flipped)
                    xs = (sprite.getWidth() -1) - x;
                else
                    xs = x;

                if(xa < -sprite.getWidth() || xa > width || ya < 0 || ya > width) break;
                if(xa < 0) continue;
                if(xa > width - 1) continue;
                if(ya > height - 1) continue;

                int col = sprite.pixels[y * sprite.getWidth() + xs];
                if(col == 0xffffffff)
                    pixels[ya * width + xa] = colour;
            }
        }
    }

    public void renderText(int x, int y, int colour, String text){
        for(int i = 0; i < text.length(); i++){
            Sprite sprite = fontmap[fontREGEX.indexOf(text.toLowerCase().charAt(i))];
            renderColouredSprite(x+i*8, y, sprite, colour, false, true);
        }
    }

    public Rectangle GetScreenRect(){
        return new Rectangle(xOffset - 32, yOffset -32, width + 64, height + 64);
    }

    public void clear(){
        Arrays.fill(pixels, 0x000000);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setOffset(int x, int y){
        this.xOffset = x;
        this.yOffset = y;
    }
}
