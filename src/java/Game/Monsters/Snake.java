package Game.Monsters;

import Game.Handler;

/**
 * Represents a Snake monster in the game.
 */
public class Snake extends Monster {
    /**
     * Constructs a new Snake object.
     *
     * @param x       The initial x-coordinate of the Snake.
     * @param y       The initial y-coordinate of the Snake.
     * @param type    The type of the Snake.
     * @param handler The game handler managing the Snake.
     */
    public Snake(int x, int y, Type type, Handler handler) {
        super(x, y, type, handler);
        this.speed = 10;
        this.dmg = 20;
        this.gold = 15;
        this.hp = 100;
        this.name = "snake";
        getAssets();
    }
}
