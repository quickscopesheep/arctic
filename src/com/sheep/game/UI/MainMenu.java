package com.sheep.game.UI;

import com.sheep.game.Game;
import com.sheep.game.UI.Widgets.ButtonWidget;
import com.sheep.game.UI.Widgets.TextWidget;
import com.sheep.game.UI.Widgets.VerticalLayoutGroup;
import com.sheep.game.UI.Widgets.buttonFunctions.QuitGameCallback;

public class MainMenu extends Menu{
    public MainMenu(Game game) {
        super(game);
        constructMenu();
    }

    @Override
    protected void constructMenu() {
        VerticalLayoutGroup group = new VerticalLayoutGroup(Game.WIDTH/2, Game.HEIGHT/2, this, 2, 0, game);
        AddWidget(group);

        AddWidget(new TextWidget(Game.WIDTH/2, Game.HEIGHT/2 - 48, this, "Glacier", 0xffffff, game));

        group.AddWidget(new ButtonWidget(0, 0, "Start", this, null, game));
        group.AddWidget(new ButtonWidget(0, 0, "Quit", this, new QuitGameCallback(), game));
    }
}
