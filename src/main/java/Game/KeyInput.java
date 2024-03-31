package Game;

import Game.HUD.Dummy;
import Game.HUD.HUD;
import Game.Map.Map;
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
            //Left
            case 39 -> {
                if (Game.gameState == Game.STATE.GAME) {
                    game.getHud().getSidebar().opedSideBar();
                    if (game.getHud().getSidebar().getDummy() != null) {
                        game.removeListenerForDummy(game.getHud().getSidebar().getDummy());
                        game.getHud().getSidebar().setDummy(null);
                    }
                }
            }
            //Right
            case 37 -> {
                if (Game.gameState == Game.STATE.GAME) {
                    game.getHud().getSidebar().closeSideBar();
                }
            }
            //C
            case 67 -> {
                if (Game.gameState == Game.STATE.GAME) {
                    if (Map.COIN > Cannon.PRICE) {
                        game.getHud().getSidebar().setDummy(new Dummy(handler, new Cannon(0, 0, handler)));
                        game.addListenerForDummy(game.getHud().getSidebar().getDummy());
                        game.getHud().getSidebar().closeSideBar();
                        game.getHud().getUpgradeBar().closeUpgradeBar();
                    } else {
                        //Play sound effect
                    }
                }
            }
            //ESC
            case 27 -> {
                if (Game.gameState == Game.STATE.PAUSE){
                    Game.gameState = Game.STATE.GAME;
                } else if (Game.gameState == Game.STATE.GAME){
                    Game.gameState = Game.STATE.PAUSE;
                }
            }
        }
    }
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}