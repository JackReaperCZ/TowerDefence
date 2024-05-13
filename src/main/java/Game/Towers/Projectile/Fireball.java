package Game.Towers.Projectile;

import Game.Handler;

/**
 * Represents a fireball projectile fired by a tower in the game.
 */
public class Fireball extends Projectile {
    /**
     * Constructs a fireball projectile with the specified coordinates, target coordinates, damage, and handler.
     *
     * @param x       The initial x-coordinate of the fireball.
     * @param y       The initial y-coordinate of the fireball.
     * @param x2      The x-coordinate of the target.
     * @param y2      The y-coordinate of the target.
     * @param dmg     The damage inflicted by the fireball.
     * @param handler The handler managing the fireball.
     */
    public Fireball(int x, int y, int x2, int y2, int dmg, Handler handler) {
        super(x, y, dmg, handler);
        this.speed = 25;
        this.damageType = DamageType.MAGICAL;
        this.name = "fireball";
        velCalculate(x2, y2, speed);
        getAssets();
    }
}
