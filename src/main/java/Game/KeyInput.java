package Game;

import Game.HUD.Dummy;
import Game.HUD.HUD;
import Game.Towers.Cannon;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//KeyInput class to handle user input
public class KeyInput extends KeyAdapter {
    private Handler handler;
    private Game game;
    private boolean enabled;

    //Constructor
    public KeyInput(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;
        this.enabled = true;
    }

    //Method to get pressed key
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        System.out.println(key);
        switch (key) {
            case 39 -> {
                game.getHud().getSidebar().opedSideBar();
                if (game.getHud().getSidebar().getDummy() != null) {
                   game.removeListenerForDummy(game.getHud().getSidebar().getDummy());
                   game.getHud().getSidebar().setDummy(null);
                }
            }
            case 37 -> {
                game.getHud().getSidebar().closeSideBar();
            }
            case 67 -> {
                if (HUD.COIN > Cannon.PRICE) {
                    game.getHud().getSidebar().setDummy(new Dummy(handler, new Cannon(0, 0, handler)));
                    game.addListenerForDummy(game.getHud().getSidebar().getDummy());
                    game.getHud().getSidebar().closeSideBar();
                } else {
                    //Play sound effect
                }
            }
        }
    }

    //Method to get released key
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}