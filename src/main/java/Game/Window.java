package Game;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Canvas;

/**
 * The Window class creates the main window for the game.
 */
public class Window extends Canvas {
    /**
     * Constructs a new Window object with the specified width, height, title, and game instance.
     *
     * @param width  The width of the window.
     * @param height The height of the window.
     * @param title  The title of the window.
     * @param game   The game instance to be added to the window.
     */
    public Window(int width, int height, String title, Game game) {
        // Creates a JFrame to hold the game
        JFrame frame = new JFrame(title);

        // Sets the dimensions of the frame
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        // Sets the behavior when the window is closed
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Prevents the window from being resizable
        frame.setResizable(false);

        // Centers the window on the screen
        frame.setLocationRelativeTo(null);

        // Adds the game instance to the frame
        frame.add(game);

        // Makes the window visible
        frame.setVisible(true);

        // Starts the game
        game.start();
    }
}