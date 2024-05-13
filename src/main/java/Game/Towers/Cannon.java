package Game.Towers;

import Game.AudioPlayer;
import Game.Handler;
import Game.Map.Map;
import Game.Map.MapStatus;
import Game.Towers.Projectile.Cannonball;

/**
 * Represents a Cannon tower in the game.
 */
public class Cannon extends Tower {
    /**
     * The price of the Cannon tower.
     */
    public static int PRICE = 350;

    /**
     * Constructs a Cannon tower with the specified coordinates and handler.
     *
     * @param x       The x-coordinate of the Cannon tower.
     * @param y       The y-coordinate of the Cannon tower.
     * @param handler The handler managing the Cannon tower.
     */
    public Cannon(int x, int y, Handler handler) {
        super(x, y, handler);
        this.towerName = "cannon";
        this.dmg = 100;
        this.coolDownFlag = 120;
        this.projectileCooldown = this.coolDownFlag;
        this.radius = 400;
        this.price = 350;
        getTowerAssets();
    }

    /**
     * Shoots a cannonball projectile from the Cannon tower towards the target.
     *
     * @param xTarget The x-coordinate of the target.
     * @param yTarget The y-coordinate of the target.
     */
    @Override
    public void shoot(int xTarget, int yTarget) {
        if (Map.mapStatus == MapStatus.IN_PROGRESS) {
            angle = calculateAngle(xTarget, yTarget);
            handler.addGameObject(new Cannonball(x + 19, y + 19, xTarget + 19, yTarget + 19, dmg, handler));
            AudioPlayer.getSound("cannon").play();
            projectileCooldown = coolDownFlag;
        }
    }
}