package Game.Monsters;

import Game.Handler;

/**
 * Represents a Spider monster in the game.
 */
public class Spider extends Monster {
    /**
     * Constructs a new Spider object.
     *
     * @param x       The initial x-coordinate of the Spider.
     * @param y       The initial y-coordinate of the Spider.
     * @param type    The type of the Spider.
     * @param handler The game handler managing the Spider.
     */
    public Spider(int x, int y, Type type, Handler handler) {
        super(x, y, type, handler);
        this.speed = 10;
        this.dmg = 10;
        this.gold = 100;
        this.hp = 50;
        this.name = "spider";
        getAssets();
    }
}