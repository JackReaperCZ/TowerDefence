import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements  Runnable {
    //Static parameters for main window
    public static final int WIDTH = 1080, HEIGHT = WIDTH / 12 * 9;

    //Declaration of main thread
    private Thread thread;
    //Control variable for stopping and starting the game
    private boolean running = false;

    //Declaration of handler of game objects
    private Handler handler;

    //Main method
    public static void main(String[] args) {
        new Game();
    }

    public Game() {
        //Initialization of the handler
        this.handler = new Handler();
        //Creating a main window
        new Window(WIDTH,HEIGHT,"Tower Defence",this);
    }

    //Method to initialize and start the thread
    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    //Method to stop the thread
    public synchronized void stop(){
        try {
            thread.join();
            running = false;
        } catch (Exception e){
          e.printStackTrace();
        }
    }

    //Main game loop
    @Override
    public void run() {
        long lastTime = System.nanoTime();
        //60 ticks a second
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1){
                tick();
                delta--;
            }
            if (running){
                render();
                frames++;

                if (System.currentTimeMillis() - timer > 1000){
                    timer += 1000;
                    System.out.println("FPS: " + frames);
                    frames = 0;
                }
            }
        }
        stop();
    }

    //Method to tell handled to tick all game objects
    private void tick(){
        handler.tick();
    }

    //Method to tell handled to render all game objects
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        handler.render(g);

        g.dispose();
        bs.show();
    }
}