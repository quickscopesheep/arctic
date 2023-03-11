package com.sheep.game.entity.Mob;

import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;
import com.sheep.game.level.Level;
import com.sheep.game.util.input.Keyboard;


public class Player extends Mob {
    public static final float MOVE_SPEED = 32/60f;

    int animTick;
    int frame;

    float inputX, inputY;
    float lastInputX, lastInputY = 1;

    boolean moving;

    public Player(float x, float y, Level level) {
        super(x, y, 0, 0, 25, level);
        setxBound(12);
        setyBound(2);
        setxBoundOffset(2);
        setyBoundOffset(14);
    }

    @Override
    public void tick() {
        inputX = 0;
        inputY = 0;
        if(Keyboard.UP_DOWN) inputY = -1;
        if(Keyboard.DOWN_DOWN) inputY = 1;
        if(Keyboard.LEFT_DOWN) inputX = -1;
        if(Keyboard.RIGHT_DOWN) inputX = 1;

        if(inputX != 0 || inputY != 0){
            lastInputX = inputX;
            lastInputY = inputY;
        }

        moving = inputX != 0 || inputY != 0;
        move(inputX*MOVE_SPEED, inputY*MOVE_SPEED);

        animTick++;
        if(animTick > 40)
            animTick = 0;

        frame = animTick/10;
    }

    @Override
    public void render(Screen screen) {
        if(moving){
            if(lastInputY < 0)
                screen.renderSprite((int)x, (int)y, frame % 2 != 0 ? Sprite.player_backward_walk : Sprite.player_backward, frame % 3 == 0, false);
            else if (lastInputY > 0)
                screen.renderSprite((int)x, (int)y, frame % 2 != 0 ? Sprite.player_forward_walk : Sprite.player_forward, frame % 3 == 0, false);
            else if(inputX != 0)
                screen.renderSprite((int)x, (int)y, frame % 2 != 0 ? Sprite.player_side_walk : Sprite.player_side, lastInputX>0, false);

        }else{
            if(lastInputY > 0)
                screen.renderSprite((int)x, (int)y, Sprite.player_forward, false, false);
            else if (lastInputY < 0)
                screen.renderSprite((int)x, (int)y, Sprite.player_backward, false, false);
            else if(lastInputX !=0)
                screen.renderSprite((int)x, (int)y, Sprite.player_side, lastInputX>0, false);
        }

        //if(building){
        //    screen.renderColouredSprite((int)Math.floor((Mouse.getMouseX()+screen.xOffset)/16f)*16,
        //            (int)Math.floor((Mouse.getMouseY()+ screen.yOffset)/16f)*16, Sprite.UI_crosshair_build, 0xdddd00, false, false);
        //}
    }
}
