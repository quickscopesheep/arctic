package com.sheep.game.UI;

import com.sheep.game.Game;
import com.sheep.game.UI.Widgets.Widget;
import com.sheep.game.gfx.Screen;

import java.util.ArrayList;
import java.util.List;

public abstract class Menu {
    List<Widget> widgets;

    public Game game;

    public Menu(Game game){
        widgets = new ArrayList<>();
        this.game = game;
    }

    protected abstract void constructMenu();

    protected Widget AddWidget(Widget widget){
        widgets.add(widget);

        return widget;
    }

    public void tick(){
        for (Widget w : widgets) {
            if(w.isActive())
                w.tick();
        }
    }

    public void render(Screen screen){
        for (Widget w : widgets) {
            if(w.isActive())
                w.render(screen);
        }
    }

    public void clearWidgets(){
        widgets.clear();
    }
}
