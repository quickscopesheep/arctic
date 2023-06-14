package com.sheep.game.UI;

import com.sheep.game.Game;
import com.sheep.game.UI.Widgets.*;
import com.sheep.game.gfx.Sprite;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MainMenu extends Menu{
    public MainMenu(Game game) {
        super(game);
        constructMenu();
    }

    @Override
    protected void constructMenu() {
        VerticalLayoutGroup group = new VerticalLayoutGroup(Game.WIDTH/2, Game.HEIGHT/2, this, 2, 0, game);
        AddWidget(group);

        AddWidget(new TextWidget(Game.WIDTH/2, Game.HEIGHT/2 - 48, this, "Arctic", 0xffffff, game));

        group.AddWidget(new ButtonWidget(0, 0, "Start", this, new IButton(){
            @Override
            public void OnClick(Widget widget, Game game) {
                super.OnClick(widget, game);
                game.init();
                game.currentMenu = game.menus[1];
            }
        }, game));


        group.AddWidget(new ButtonWidget(0, 0, "Quit", this, new IButton(){
            @Override
            public void OnClick(Widget widget, Game game) {
                super.OnClick(widget, game);
                System.exit(0);
            }
        }, game));

        AddWidget(new ButtonWidget(Game.WIDTH / 2, Game.HEIGHT - 8, "Made By Noah Whewall", this, new IButton() {
            @Override
            public void OnClick(Widget widget, Game game) {
                super.OnClick(widget, game);
                try {
                    Desktop desktop = Desktop.getDesktop();
                    desktop.browse(new URI("https://github.com/quickscopesheep"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, game));
    }
}
