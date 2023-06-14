package com.sheep.game.UI.Hud;

import com.sheep.game.Game;
import com.sheep.game.UI.Menu;
import com.sheep.game.UI.Widgets.*;

public class Hud extends Menu {
    HorizontalLayoutGroup hotbarLayout;
    int hotbarSize;

    public Hud(Game game, int hotbarSize) {
        super(game);
        this.hotbarSize = hotbarSize;
        constructMenu();
    }

    @Override
    protected void constructMenu() {
        hotbarLayout = new HorizontalLayoutGroup((hotbarSize/2+1)*16 + 4, 10, this, 4, 0, game);

        for (int i = 0; i < hotbarSize; i++){
            hotbarLayout.AddWidget(new HotbarWidget(0, 0, this, game, null, i));
        }

        AddWidget(hotbarLayout);
    }

    @Override
    public void tick() {
        super.tick();

        for (int i = 0, widgetsSize = hotbarLayout.getWidgets().size(); i < widgetsSize; i++) {
            Widget widget = hotbarLayout.getWidgets().get(i);
            ((HotbarWidget) widget).setItem(game.player.getHotbar()[i]);
        }
    }
}
