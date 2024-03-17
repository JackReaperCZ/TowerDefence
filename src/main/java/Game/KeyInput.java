package Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
//KeyInput class to handle user input
public class KeyInput extends KeyAdapter {
    private Handler handler;
    //Constructor
    public KeyInput(Handler handler) {
        this.handler = handler;
    }
    //Method to get pressed key
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

    }
    //Method to get released key
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

    }
}