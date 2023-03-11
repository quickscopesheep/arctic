package com.sheep.game.UI.Widgets;

import com.sheep.game.Game;
import com.sheep.game.UI.Menu;
import com.sheep.game.gfx.Screen;
import com.sheep.game.util.input.Keyboard;
import com.sheep.game.util.input.KeyboardButtonListener;

import java.awt.event.KeyEvent;

public class TextInputWidget extends Widget implements KeyboardButtonListener {
    private String placeholder;

    private String text;
    private boolean focused;

    public TextInputWidget(int x, int y, Menu parent, Game game, String placeholder) {
        super(x, y, 0, 0, parent, game);
        this.placeholder = placeholder;
        Keyboard.AddListener(this);
        setW(placeholder.length()*8);
        setH(8);
        text = "";
    }

    @Override
    public void OnClick(Game game) {
        focused = true;
    }

    @Override
    public void KeyDown(KeyEvent event) {
        if(!focused)
            return;

        if(event.getKeyCode() == KeyEvent.VK_ENTER){
            focused = false;
        }

        if(event.getKeyCode() == KeyEvent.VK_BACK_SPACE && text.length() > 0){
            text = text.substring(0, text.length()-1);
        }
        else if(!event.isActionKey()){
            text += event.getKeyChar();
        }

        if(text.length() < 1)
            setW(placeholder.length()*8);
        else
            setW(text.length()*8);
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    public void render(Screen screen) {
        if(text.length() < 1)
            screen.renderText(x - w/2, y - h/2,  focused ? 0x555500 : 0x555555, placeholder);
        else
            screen.renderText(x - w/2, y - h/2, focused ? 0xffff00 : 0xffffff, text);
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isFocused() {
        return focused;
    }

    public void setFocused(boolean focused) {
        this.focused = focused;
    }
}
