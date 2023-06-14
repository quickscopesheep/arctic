package com.sheep.game.UI.Widgets;

import com.sheep.game.Game;
import com.sheep.game.UI.Menu;
import com.sheep.game.gfx.Sprite;

public class ImageButtonWidget extends ImageWidget {
    IButton callback;
    boolean hoverLastTick;

    public ImageButtonWidget(int x, int y, Sprite sprite, Menu parent, Game game, IButton callback) {
        super(x, y, sprite, parent, game);
        this.callback = callback;
    }

    @Override
    public void OnClick(Game game) {
        super.OnClick(game);
        callback.OnClick(this, game);
    }

    public void OnHover(Game game){
        callback.OnHover(this, game);
    }

    @Override
    public void tick() {
        super.tick();

        if(isHovering() && !hoverLastTick){
            OnHover(game);
            hoverLastTick = true;
        }else if(!isHovering()){
            hoverLastTick = false;
        }
    }
}
