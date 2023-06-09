package com.sheep.game.UI.Hud;

import com.sheep.game.Game;
import com.sheep.game.UI.Menu;
import com.sheep.game.UI.Widgets.Widget;
import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;
import com.sheep.game.item.Item;

public class HotbarWidget extends Widget {
    Item item;
    int index;

    public HotbarWidget(int x, int y, Menu parent, Game game, Item item, int index) {
        super(x, y, 16, 16, parent, game);
        this.item = item;
        this.index = index;
    }

    @Override
    public void OnClick(Game game) {

    }

    @Override
    public void render(Screen screen) {
        super.render(screen);

        screen.renderSprite(x-8, y-8, 99, getGame().player.getHotbarIndex() == index ? Sprite.item_frame_select : Sprite.item_frame,
                false, true);

        if(item != null)
            screen.renderSprite(x-8, y-8, 99, item.getIcon(), false, true);
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Item getItem() {
        return item;
    }
}
