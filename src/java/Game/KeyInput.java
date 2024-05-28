package Game;

import Game.HUD.Dummy;
import Game.Map.Map;
import Game.Map.MapStatus;
import Game.Map.Wave.Spawner;
import Game.Towers.Cannon;
import Game.Towers.Mage;
import Game.Towers.Ninja;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Handles user keyboard input.
 */
public class KeyInput extends KeyAdapter {
    private Handler handler;
    private Game game;

    /**
     * Constructs a KeyInput object.
     *
     * @param handler The handler for the game.
     * @param game    The game instance.
     */
    public KeyInput(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;
    }

    /**
     * Responds to key press events.
     *
     * @param e The KeyEvent representing the key press event.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (Map.mapStatus == MapStatus.IN_PROGRESS) {
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
                            AudioPlayer.getSound("nope").play();
                        }
                    }
                }
                //N
                case 78 -> {
                    if (Game.gameState == Game.STATE.GAME) {
                        if (Map.COIN > Ninja.PRICE) {
                            game.getHud().getSidebar().setDummy(new Dummy(handler, new Ninja(0, 0, handler)));
                            game.addListenerForDummy(game.getHud().getSidebar().getDummy());
                            game.getHud().getSidebar().closeSideBar();
                            game.getHud().getUpgradeBar().closeUpgradeBar();
                        } else {
                            AudioPlayer.getSound("nope").play();
                        }
                    }
                }
                //M
                case 77 -> {
                    if (Game.gameState == Game.STATE.GAME) {
                        if (Map.COIN > Mage.PRICE) {
                            game.getHud().getSidebar().setDummy(new Dummy(handler, new Mage(0, 0, handler)));
                            game.addListenerForDummy(game.getHud().getSidebar().getDummy());
                            game.getHud().getSidebar().closeSideBar();
                            game.getHud().getUpgradeBar().closeUpgradeBar();
                        } else {
                            AudioPlayer.getSound("nope").play();
                        }
                    }
                }
                //ESC
                case 27 -> {
                    if (Game.gameState == Game.STATE.PAUSE) {
                        Game.gameState = Game.STATE.GAME;
                    } else if (Game.gameState == Game.STATE.GAME) {
                        Game.gameState = Game.STATE.PAUSE;
                    }
                }
                //Space and ENTER
                case 32, 10 -> {
                    if (!Spawner.SPAWN && Game.gameState == Game.STATE.GAME) {
                        Spawner.SPAWN = true;
                    }
                }
            }
        }
    }
}