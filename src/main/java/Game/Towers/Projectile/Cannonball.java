package Game.Towers.Projectile;

import Game.Handler;

/**
 * Represents a cannonball projectile fired by a cannon tower.
 */
public class Cannonball extends Projectile {
    /**
     * Constructs a new Cannonball projectile.
     *
     * @param x       The initial x-coordinate of the projectile.
     * @param y       The initial y-coordinate of the projectile.
     * @param x2      The x-coordinate of the target.
     * @param y2      The y-coordinate of the target.
     * @param dmg     The damage inflicted by the cannonball.
     * @param handler The game handler managing the projectile.
     */
    public Cannonball(int x, int y, int x2, int y2, int dmg, Handler handler) {
        super(x, y, dmg, handler);
        this.speed = 25;
        this.damageType = DamageType.NORMAL;
        this.name = "cannonball";
        velCalculate(x2, y2, speed);
        getAssets();
    }
}
