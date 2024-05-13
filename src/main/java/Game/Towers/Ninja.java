package Game.Towers;

import Game.AudioPlayer;
import Game.Handler;
import Game.Map.Map;
import Game.Map.MapStatus;
import Game.Towers.Projectile.Shuriken;

/**
 * Represents a Ninja tower in the game.
 */
public class Ninja extends Tower {
    /**
     * The price of the Ninja tower.
     */
    public static int PRICE = 450;

    /**
     * Constructs a Ninja tower with the specified coordinates and handler.
     *
     * @param x       The x-coordinate of the Ninja tower.
     * @param y       The y-coordinate of the Ninja tower.
     * @param handler The handler managing the Ninja tower.
     */
    public Ninja(int x, int y, Handler handler) {
        super(x, y, handler);
        this.towerName = "ninja";
        this.dmg = 45;
        this.coolDownFlag = 20;
        this.projectileCooldown = this.coolDownFlag;
        this.radius = 350;
        this.price = 450;
        getTowerAssets();
    }

    /**
     * Shoots a shuriken projectile from the Ninja tower towards the target.
     *
     * @param xTarget The x-coordinate of the target.
     * @param yTarget The y-coordinate of the target.
     */
    @Override
    public void shoot(int xTarget, int yTarget) {
        if (Map.mapStatus == MapStatus.IN_PROGRESS) {
            angle = calculateAngle(xTarget, yTarget);
            handler.addGameObject(new Shuriken(x + 19, y + 19, xTarget + 19, yTarget + 19, dmg, handler));
            AudioPlayer.getSound("shot").play();
            projectileCooldown = coolDownFlag;
        }
    }
}
