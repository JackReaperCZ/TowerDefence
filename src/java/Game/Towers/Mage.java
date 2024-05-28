package Game.Towers;

import Game.AudioPlayer;
import Game.Handler;
import Game.Map.Map;
import Game.Map.MapStatus;
import Game.Towers.Projectile.Fireball;

/**
 * Represents a Mage tower in the game.
 */
public class Mage extends Tower {
    /**
     * The price of the Mage tower.
     */
    public static int PRICE = 700;

    /**
     * Constructs a Mage tower with the specified coordinates and handler.
     *
     * @param x       The x-coordinate of the Mage tower.
     * @param y       The y-coordinate of the Mage tower.
     * @param handler The handler managing the Mage tower.
     */
    public Mage(int x, int y, Handler handler) {
        super(x, y, handler);
        this.towerName = "mage";
        this.dmg = 250;
        this.coolDownFlag = 50;
        this.projectileCooldown = this.coolDownFlag;
        this.radius = 300;
        this.price = 700;
        getTowerAssets();
    }

    /**
     * Shoots a fireball projectile from the Mage tower towards the target.
     *
     * @param xTarget The x-coordinate of the target.
     * @param yTarget The y-coordinate of the target.
     */
    @Override
    public void shoot(int xTarget, int yTarget) {
        if (Map.mapStatus == MapStatus.IN_PROGRESS) {
            angle = calculateAngle(xTarget, yTarget);
            handler.addGameObject(new Fireball(x + 19, y + 19, xTarget + 19, yTarget + 19, dmg, handler));
            AudioPlayer.soundMap.get("mage").play();
            projectileCooldown = coolDownFlag;
        }
    }
}
