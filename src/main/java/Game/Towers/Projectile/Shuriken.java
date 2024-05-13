package Game.Towers.Projectile;

import Game.Handler;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Represents a shuriken projectile fired by a tower in the game.
 */
public class Shuriken extends Projectile {
    /**
     * The angle of rotation for rendering the shuriken.
     */
    private int angle = 0;

    /**
     * Constructs a shuriken projectile with the specified coordinates, target coordinates, damage, and handler.
     *
     * @param x       The initial x-coordinate of the shuriken.
     * @param y       The initial y-coordinate of the shuriken.
     * @param x2      The x-coordinate of the target.
     * @param y2      The y-coordinate of the target.
     * @param dmg     The damage inflicted by the shuriken.
     * @param handler The handler managing the shuriken.
     */
    public Shuriken(int x, int y, int x2, int y2, int dmg, Handler handler) {
        super(x, y, dmg, handler);
        this.speed = 35;
        this.damageType = DamageType.NORMAL;
        this.name = "shuriken";
        velCalculate(x2, y2, speed);
        getAssets();
    }

    /**
     * Updates the position of the shuriken.
     */
    @Override
    public void tick() {
        x += velX;
        y += velY;
        angle++;
    }

    /**
     * Renders the shuriken with rotation.
     */
    @Override
    public void render(Graphics g) {
        AffineTransform tr = new AffineTransform();
        tr.translate(getX(), getY());
        tr.rotate(angle, (double) 38 / 2, (double) 38 / 2);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, tr, null);
    }
}
