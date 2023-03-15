package com.sheep.game.UI.Hud;

import com.sheep.game.Game;
import com.sheep.game.UI.Menu;
import com.sheep.game.UI.Widgets.Widget;
import com.sheep.game.gfx.Screen;
import com.sheep.game.gfx.Sprite;
import com.sheep.game.item.Item;

public class HotbarWidget extends Widget {
    Item item;

    public HotbarWidget(int x, int y, Menu parent, Game game, Item item) {
        super(x, y, 16, 16, parent, game);
        this.item = item;
    }

    @Override
    public void OnClick(Game game) {

    }

    @Override
    public void render(Screen screen) {
        super.render(screen);

        screen.renderSprite(x-8, y-8, 99, Sprite.item_frame, false, true);

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
