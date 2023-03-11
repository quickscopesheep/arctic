package com.sheep.game.UI.Widgets;

import com.sheep.game.Game;
import com.sheep.game.UI.Menu;
import com.sheep.game.gfx.Screen;

public class TextWidget extends Widget {
    String label;
    int colour;

    Game game;

    boolean alignCentre = true;

    public TextWidget(int x, int y, Menu parent, String label, int colour, Game game) {
        super(x, y, 0, 0, parent, game);
        this.label = label;
        this.w = label.length()*8+2;
        this.h = 10;
        this.colour = colour;
    }

    @Override
    public void OnClick(Game game) {

    }

    @Override
    public void render(Screen screen) {
        super.render(screen);
        screen.renderText(x - (alignCentre? (w/2) : 0), y - (h/2), colour, label);
    }

    public void setAlignCentre(boolean alignCentre) {
        this.alignCentre = alignCentre;
    }
}
