package Game.Monsters;

import Game.Handler;

/**
 * Represents a type of monster called "Worm".
 * Worms are slow-moving monsters with moderate damage, health, and gold rewards.
 */
public class Worm extends Monster {
    /**
     * Constructs a new Worm object with the specified attributes.
     *
     * @param x       The x-coordinate of the Worm's initial position.
     * @param y       The y-coordinate of the Worm's initial position.
     * @param type    The type of the Worm (e.g., Normal, Armored, Magic Resistant).
     * @param handler The game handler responsible for managing the Worm.
     */
    public Worm(int x, int y, Type type, Handler handler) {
        super(x, y, type, handler);
        this.speed = 4;
        this.dmg = 15;
        this.gold = 25;
        this.hp = 250;
        this.name = "worm";
        getAssets();
    }
}
