package com.sheep.game.util.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class Keyboard implements KeyListener {
    public static boolean UP_DOWN, DOWN_DOWN, LEFT_DOWN, RIGHT_DOWN, USE1_DOWN, USE2_DOWN, USE3_DOWN, INVENTORY_DOWN, BUILD_DOWN;

    public static final int
            UP = KeyEvent.VK_UP,
            DOWN = KeyEvent.VK_DOWN,
            LEFT = KeyEvent.VK_LEFT,
            RIGHT = KeyEvent.VK_RIGHT,
            USE1 = KeyEvent.VK_Z,
            USE2 = KeyEvent.VK_X,
            USE3 = KeyEvent.VK_C,
            INVENTORY = KeyEvent.VK_E,
            BUILD = KeyEvent.VK_Q;

    private static List<KeyboardButtonListener> listeners = new ArrayList<KeyboardButtonListener>();

    public static void AddListener(KeyboardButtonListener listener){
        listeners.add(listener);
    }

    public static void RemoveListener(KeyboardButtonListener listener){
        listeners.remove(listener);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> UP_DOWN = true;
            case KeyEvent.VK_DOWN -> DOWN_DOWN = true;
            case KeyEvent.VK_LEFT -> LEFT_DOWN = true;
            case KeyEvent.VK_RIGHT -> RIGHT_DOWN = true;
            case KeyEvent.VK_Z -> USE1_DOWN = true;
            case KeyEvent.VK_X -> USE2_DOWN = true;
            case KeyEvent.VK_C -> USE3_DOWN = true;
            case KeyEvent.VK_E -> INVENTORY_DOWN = true;
            case KeyEvent.VK_Q -> BUILD_DOWN = true;
        }

        for (int i = 0; i < listeners.size(); i++) {
            KeyboardButtonListener listener = listeners.get(i);
            listener.KeyDown(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> UP_DOWN = false;
            case KeyEvent.VK_DOWN -> DOWN_DOWN = false;
            case KeyEvent.VK_LEFT -> LEFT_DOWN = false;
            case KeyEvent.VK_RIGHT -> RIGHT_DOWN = false;
            case KeyEvent.VK_Z -> USE1_DOWN = false;
            case KeyEvent.VK_X -> USE2_DOWN = false;
            case KeyEvent.VK_C -> USE3_DOWN = false;
            case KeyEvent.VK_E -> INVENTORY_DOWN = false;
            case KeyEvent.VK_Q -> BUILD_DOWN = false;
        }
    }
}