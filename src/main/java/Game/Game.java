package Game;

import Game.HUD.Dummy;
import Game.HUD.HUD;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import java.awt.*;
import java.awt.image.BufferStrategy;

/**
 * The main class that controls the game flow.
 * Responsible for initialization, game loop execution, rendering, and input handling.
 */
public class Game extends Canvas implements Runnable {
    /**
     * The width of the game window.
     */
    public static final int WIDTH = 1080;

    /**
     * The height of the game window.
     */
    public static final int HEIGHT = WIDTH / 12 * 9;

    /**
     * The main thread responsible for running the game loop.
     */
    private Thread thread;

    /**
     * Flag indicating whether the game is running.
     */
    private boolean running = false;

    /**
     * The current state of the game.
     */
    public enum STATE {
        MENU,
        GAME,
        PAUSE
    }

    /**
     * The current state of the game.
     */
    public static STATE gameState = STATE.MENU;

    /**
     * The handler responsible for managing game objects.
     */
    private Handler handler;

    /**
     * The heads-up display (HUD) showing game information.
     */
    private HUD hud;

    /**
     * The menu displayed during the game.
     */
    private Menu menu;

    /**
     * The key input handler for the game.
     */
    private KeyInput keyInput;

    /**
     * Main method to start the game.
     *
     * @param args Command-line arguments (not used).
     */

    //Main method
    public static void main(String[] args) {
        FlatMacDarkLaf.setup();
        new Game();
    }

    /**
     * Constructs a new instance of the Game class.
     * Initializes game components, sets up input listeners, and creates the game window.
     */
    public Game() {
        //Initialization of the handler
        this.handler = new Handler();
        //Initialization of the HUD
        this.hud = new HUD(handler, this);
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

        AudioPlayer.musicMap.get("menu").loop();
    }

    /**
     * Starts the game loop by initializing and starting the thread.
     */
    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    /**
     * Stops the game loop by stopping the thread.
     */
    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * The main game loop that runs continuously until the game is stopped.
     * Responsible for updating game logic and rendering.
     */
    @Override
    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        //120 ticks a second
        double amountOfTicks = 120.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
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
            }
        }
        stop();
    }

    /**
     * Updates game logic for each frame.
     * Called within the game loop.
     */
    private void tick() {
        if (gameState == STATE.GAME) {
            handler.tick();
            hud.tick();
        }
    }

    /**
     * Renders game objects and HUD components.
     * Called within the game loop.
     */
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

    /**
     * Retrieves the HUD of the game.
     *
     * @return The HUD object.
     */
    public HUD getHud() {
        return hud;
    }

    /**
     * Adds a mouse motion listener for a Dummy object in the HUD.
     *
     * @param dummy The Dummy object to add the listener to.
     */
    public void addListenerForDummy(Dummy dummy) {
        this.addMouseMotionListener(dummy);
    }

    /**
     * Removes a mouse motion listener from a Dummy object in the HUD.
     *
     * @param dummy The Dummy object to remove the listener from.
     */
    public void removeListenerForDummy(Dummy dummy) {
        this.removeMouseMotionListener(dummy);
    }
}