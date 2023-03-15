package com.sheep.game.UI.Widgets;

import com.sheep.game.Game;
import com.sheep.game.UI.Menu;
import com.sheep.game.gfx.Screen;

import java.util.ArrayList;
import java.util.List;

public class HorizontalLayoutGroup extends Widget{
    List<Widget> widgets;

    int spacing;
    int padding;

    public HorizontalLayoutGroup(int x, int y, Menu parent, int spacing, int padding, Game game) {
        super(x, y, 0, 0, parent, game);
        widgets = new ArrayList<>();
        this.spacing = spacing;
        this.padding = padding;
    }

    public Widget AddWidget(Widget widget){
        widgets.add(widget);
        updateWidgetPositions();

        return widget;
    }

    public Widget RemoveWidget(Widget widget){
        widgets.remove(widget);
        updateWidgetPositions();

        return widget;
    }

    public void clearWidgets(){
        for (int i = 0; i < widgets.size(); i++) {
            RemoveWidget(widgets.get(i));
        }
    }

    void updateWidgetPositions(){
        int listPixelWidth = 0;
        for(Widget widget : widgets){
            listPixelWidth += widget.getW();
        }

        int listPixelWidthAccum = 0;

        int tallest = 0;
        for (Widget widget : widgets) {
            widget.x = this.x - listPixelWidth / 2 + listPixelWidthAccum + padding;
            widget.y = this.y;
            listPixelWidthAccum += widget.getW() + spacing;

            if (widget.h > tallest) tallest = widget.h;
        }

        w = listPixelWidthAccum + padding*2;
        h = tallest + padding*2;
    }

    @Override
    public void tick() {
        for (Widget widget : widgets) {
            widget.tick();
        }
    }

    @Override
    public void render(Screen screen) {
        super.render(screen);
        for (Widget widget : widgets) {
            widget.render(screen);
        }
    }

    @Override
    public void OnClick(Game game) {

    }

    public List<Widget> getWidgets() {
        return widgets;
    }
}
