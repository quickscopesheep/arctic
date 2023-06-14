package com.sheep.game.entity.Mob;

import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;
import com.sheep.game.item.Item;
import com.sheep.game.item.tool.Axe;
import com.sheep.game.item.tool.Pick;
import com.sheep.game.item.tool.Sword;
import com.sheep.game.level.Level;
import com.sheep.game.util.input.Keyboard;
import com.sheep.game.util.input.KeyboardButtonListener;

import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;


public class Player extends Mob implements KeyboardButtonListener {
    public static final float MOVE_SPEED = 32/60f;

    int animTick;
    int frame;

    public float inputX, inputY;
    float lastInputX, lastInputY = 1;

    boolean moving;
    boolean lastUsedItem, canUseItem;

    float itemCooldownTime;

    List<Item> inventory;
    Item[] hotbar;

    int hotbarIndex;

    public Player(float x, float y, Level level) {
        super(x, y, 0, 0, 25, level);
        Keyboard.AddListener(this);
        setxBound(12);
        setyBound(2);
        setxBoundOffset(2);
        setyBoundOffset(14);

        inventory = new ArrayList<>();
        hotbar = new Item[5];

        hotbar[0] = new Sword(this);
        hotbar[1] = new Pick(this);
        hotbar[2] = new Axe(this);

        equipItem(hotbar[hotbarIndex]);
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

        if(!lastUsedItem && Keyboard.USE1_DOWN){
            canUseItem = true;
        }

        if(canUseItem && Keyboard.USE1_DOWN && itemCooldownTime <= 0){
            canUseItem = false;
            lastUsedItem = true;

            item.use();
            itemCooldownTime = item.getCooldown();
        }

        if(itemCooldownTime > 0){
            itemCooldownTime -= 1/60f;
        }

        moving = inputX != 0 || inputY != 0;
        move(inputX*MOVE_SPEED, inputY*MOVE_SPEED);

        animTick++;
        if(animTick > 40)
            animTick = 0;

        frame = animTick/10;
        lastUsedItem = Keyboard.USE1_DOWN;
    }

    @Override
    public void render(Screen screen) {
        super.render(screen);
        if(moving){
            if(lastInputY < 0)
                screen.renderSprite((int)x, (int)y,3, frame % 2 != 0 ? Sprite.player_backward_walk : Sprite.player_backward, frame % 3 == 0, false);
            else if (lastInputY > 0)
                screen.renderSprite((int)x, (int)y,3, frame % 2 != 0 ? Sprite.player_forward_walk : Sprite.player_forward, frame % 3 == 0, false);
            else if(inputX != 0)
                screen.renderSprite((int)x, (int)y,3, frame % 2 != 0 ? Sprite.player_side_walk : Sprite.player_side, lastInputX>0, false);

        }else{
            if(lastInputY > 0)
                screen.renderSprite((int)x, (int)y,3, Sprite.player_forward, false, false);
            else if (lastInputY < 0)
                screen.renderSprite((int)x, (int)y,3, Sprite.player_backward, false, false);
            else if(lastInputX !=0)
                screen.renderSprite((int)x, (int)y,3, Sprite.player_side, lastInputX>0, false);
        }

        if(item != null)
            screen.renderSprite((int)x + dirX*3, (int)y, dirY < 0 ? 2 : 3, item.getGfx(), dirX == 1, false);
    }

    @Override
    public void KeyDown(KeyEvent button) {
        //num keys
        if(button.getKeyCode() < 0x40 && button.getKeyCode() > 0x30){
            int num = button.getKeyCode() - 0x30;
            if(num <= hotbar.length){
                hotbarIndex = num-1;
                equipItem(hotbar[hotbarIndex]);
            }
        }
    }

    void equipToHotbar(Item item, int hotbarSlot) {
        hotbar[hotbarSlot] = item;

        if (hotbarIndex == hotbarSlot)
            equipItem(item);
    }

    public int getHotbarIndex() {
        return hotbarIndex;
    }

    public Item[] getHotbar() {
        return hotbar;
    }
}
