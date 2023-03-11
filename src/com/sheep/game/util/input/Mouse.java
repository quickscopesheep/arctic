package com.sheep.game.util.input;

import com.sheep.game.Game;
import com.sheep.game.UI.Widgets.Widget;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class Mouse implements MouseListener, MouseMotionListener {
    private static int mouseX, mouseY;
    private static int button = -1;

    private static List<MouseButtonListener> listeners = new ArrayList<MouseButtonListener>();
    private static List<Widget> UIElements = new ArrayList<>();

    Game game;

    public static void AddListener(MouseButtonListener listener){
        listeners.add(listener);
    }
    public static void RemoveListener(MouseButtonListener listener){
        listeners.remove(listener);
    }

    public static void AddListener(Widget listener){
        UIElements.add(listener);
    }
    public static void RemoveListener(Widget listener){
        UIElements.remove(listener);
    }

    public static int getMouseX() {
        return mouseX;
    }

    public static int getMouseY() {
        return mouseY;
    }

    public static int getButton() {
        return button;
    }

    public Mouse(Game game) {
        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        boolean shouldCancel = false;
        for (int i = 0; i < UIElements.size(); i++) {
            Widget widget = UIElements.get(i);
                if(widget.getParent().game.currentMenu == widget.getParent() && widget.isHovering() && e.getButton() == MouseEvent.BUTTON1){
                    widget.OnClick(game);
                    shouldCancel = true;
                }
        }

        if(!shouldCancel)
            for(MouseButtonListener listener : listeners){
                listener.MouseButtonClicked(e);
            }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        button = e.getButton();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        button = -1;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX() / Game.SCALE;
        mouseY = e.getY() / Game.SCALE;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX() / Game.SCALE;
        mouseY = e.getY() / Game.SCALE;
    }
}
