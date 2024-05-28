package Game.Monsters;

import Game.Handler;

/**
 * Represents a Slime monster in the game.
 */
public class Slime extends Monster {
    /**
     * Constructs a new Slime object.
     *
     * @param x       The initial x-coordinate of the Slime.
     * @param y       The initial y-coordinate of the Slime.
     * @param type    The type of the Slime.
     * @param handler The game handler managing the Slime.
     */
    public Slime(int x, int y, Type type, Handler handler) {
        super(x, y, type, handler);
        this.speed = 2;
        this.dmg = 10;
        this.gold = 15;
        this.hp = 50;
        this.name = "slime";
        getAssets();
    }
}
