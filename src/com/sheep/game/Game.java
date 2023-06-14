package com.sheep.game;

import com.sheep.game.UI.Hud.Hud;
import com.sheep.game.UI.MainMenu;
import com.sheep.game.UI.Menu;
import com.sheep.game.entity.Mob.Player;
import com.sheep.game.gfx.Screen;
import com.sheep.game.level.ArcticLevel;
import com.sheep.game.level.Level;
import com.sheep.game.util.input.Keyboard;
import com.sheep.game.util.input.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

public class Game extends Canvas implements Runnable{
    public static final int WIDTH = 320, HEIGHT = WIDTH / 4*3, SCALE = 2;

    private Thread thread;
    private JFrame frame;
    private Screen screen;

    public Menu[] menus = new Menu[]{
        new MainMenu(this),
        new Hud(this, 5)
    };

    public Menu currentMenu;

    public Level level;
    public Player player;

    public boolean running;
    public boolean gameStarted;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();

    public static Random RNG = new Random();

    public Game(){
        setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        frame = new JFrame();
        screen = new Screen(WIDTH, HEIGHT);

        currentMenu = menus[0];

        Keyboard keyboard = new Keyboard();
        Mouse mouse = new Mouse(this);

        addKeyListener(keyboard);
        addMouseListener(mouse);
        addMouseMotionListener(mouse);


    }

    public void init(){
        level = new ArcticLevel(1024, 1024, RNG.nextInt(), this);

        player = new Player(512*16, 512*16, level);
        level.AddEntity(player);

        gameStarted = true;
    }

    public synchronized void start(){
        thread = new Thread(this, "Display");
        running = true;
        thread.start();
    }

    public synchronized void stop(){
        try{
            running = false;
            thread.join();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void tick(){
        if(level != null){
            level.tick();
            level.setScroll((int)player.getX() - WIDTH/2 + 8, (int)player.getY() - HEIGHT/2 + 8);
        }

        if(currentMenu != null)
            currentMenu.tick();
    }

    void render(){
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        screen.clear();

        if(level != null)
            level.render(screen);

        if(currentMenu != null)
            currentMenu.render(screen);

        for(int i = 0; i < pixels.length; i++){
            pixels[i] = screen.pixels[i];
        }

        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);

        g.dispose();
        bs.show();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        double ns = 1000000000 / 60.0;
        double delta = 0;

        int ticks = 0;
        int frames = 0;

        while (running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while(delta >= 1){
                tick();
                ticks++;
                delta--;
            }

            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                frame.setTitle(String.format("Arctic | TPS: %d, FPS: %d, ENT: %d", ticks, frames, level != null ? level.getEntities().size() : 0));
                ticks = 0;
                frames = 0;
            }
        }
        stop();
    }

    public Screen getScreen() {
        return screen;
    }

    public Menu getCurrentMenu() {
        return currentMenu;
    }

    public Level getLevel() {
        return level;
    }

    public static void main(String[] args) {
        Game game = new Game();

        game.frame.setResizable(false);
        game.frame.setTitle("Arctic");

        game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.frame.add(game);

        game.frame.pack();
        game.frame.setLocationRelativeTo(null);
        game.frame.setVisible(true);

        game.start();
    }
}
