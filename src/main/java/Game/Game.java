package Game;

import Game.HUD.Dummy;
import Game.HUD.HUD;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {
    //Static parameters for main window
    public static final int WIDTH = 1080, HEIGHT = WIDTH / 12 * 9;

    //Declaration of main thread
    private Thread thread;
    //Control variable for stopping and starting the game
    private boolean running = false;

    //Enum for game state
    public enum STATE {
        MENU,
        GAME,
        PAUSE
    }

    public static STATE gameState = STATE.MENU;

    //Declaration of handler of game objects
    private Handler handler;
    //Declaration of HUD object
    private HUD hud;
    //Declaration of Game.Menu object
    private Menu menu;
    private KeyInput keyInput;

    //Main method
    public static void main(String[] args) {
        new Game();
    }

    public Game() {
        //Initialization of the handler
        this.handler = new Handler();
        //Initialization of the HUD
        this.hud = new HUD(handler,this);
        //Initialization of the Game.Menu
        this.menu = new Menu(handler);
        //Initialization of the KeyInput
        this.keyInput = new KeyInput(handler, this);

        //Audio
        AudioPlayer.load();

        //KeyInput
        this.addKeyListener(keyInput);

        //Mouse input for menu
        this.addMouseListener(menu);

        //Mouse input for hud
        this.addMouseListener(hud);
        this.addMouseListener(hud.getSidebar());
        this.addMouseListener(hud.getUpgradeBar());
        this.addMouseListener(hud.getPauseMenu());
        //Creating a main window
        new Window(WIDTH, HEIGHT, "Tower Defence", this);
    }

    //Method to initialize and start the thread
    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    //Method to stop the thread
    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Main game loop
    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        //120 ticks a second
        double amountOfTicks = 120.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        //long timer = System.currentTimeMillis();
        //int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                tick();
                delta--;
            }
            if (running) {
                render();
                /*frames++;
                if (System.currentTimeMillis() - timer > 1000){
                    timer += 1000;
                    System.out.println("FPS: " + frames);
                    frames = 0;
                }*/
            }
        }
        stop();
    }

    //Method to tell handled to tick all game objects
    private void tick() {
        if (gameState == STATE.GAME) {
            handler.tick();
            hud.tick();
        }
    }

    //Method to tell handled to render all game objects
    private void render() {
        //Triple buffering
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        if (gameState == STATE.GAME || gameState == STATE.PAUSE) {
            handler.render(g);
            hud.render(g);
        } else if (gameState == STATE.MENU) {
            menu.render(g);
        }

        g.dispose();
        bs.show();
    }

    public HUD getHud() {
        return hud;
    }

    public void addListenerForDummy(Dummy dummy){
        this.addMouseMotionListener(dummy);
    }
    public void removeListenerForDummy(Dummy dummy){
        this.removeMouseMotionListener(dummy);
    }
}