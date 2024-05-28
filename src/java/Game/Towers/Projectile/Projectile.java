package Game.Towers.Projectile;

import Game.GameObject;
import Game.Handler;
import Game.ID;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.Objects;

/**
 * Abstract class representing a projectile fired by a tower in the game.
 */
public abstract class Projectile extends GameObject {
    /**
     * The handler managing the projectile.
     */
    protected Handler handler;
    /**
     * The image of the projectile.
     */
    protected Image image;
    /**
     * The speed of the projectile.
     */
    protected int speed;
    /**
     * The damage inflicted by the projectile.
     */
    protected int dmg;
    /**
     * The name of the projectile.
     */
    protected String name;
    /**
     * The type of damage dealt by the projectile.
     */
    protected DamageType damageType;

    /**
     * Constructs a projectile with the specified coordinates, damage, and handler.
     *
     * @param x       The x-coordinate of the projectile.
     * @param y       The y-coordinate of the projectile.
     * @param dmg     The damage inflicted by the projectile.
     * @param handler The handler managing the projectile.
     */
    public Projectile(int x, int y, int dmg, Handler handler) {
        super(x, y);
        this.dmg = dmg;
        this.handler = handler;
        this.id = ID.Projectile;
    }

    /**
     * Calculates the velocity of the projectile towards the target.
     *
     * @param x2    The x-coordinate of the target.
     * @param y2    The y-coordinate of the target.
     * @param speed The speed of the projectile.
     */
    public void velCalculate(int x2, int y2, int speed) {
        int yc = y2 - y;
        int xc = x2 - x;
        double c = Math.sqrt((yc * yc) + (xc * xc));
        this.velX = (int) Math.round((speed * xc) / c);
        this.velY = (int) Math.round((speed * yc) / c);
    }

    /**
     * Loads the assets (image) of the projectile.
     */
    public void getAssets() {
        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("projectile/" + name + "/" + name + ".png"))));
            this.image = icon.getImage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates the position of the projectile.
     */
    @Override
    public void tick() {
        x += velX;
        y += velY;
    }

    /**
     * Renders the projectile.
     */
    @Override
    public void render(Graphics g) {
        g.drawImage(image, x, y, null);
    }

    /**
     * Returns the intersection area of the projectile (not applicable for this abstract class).
     *
     * @return null
     */
    @Override
    public Ellipse2D getIntersects() {
        return null;
    }

    /**
     * Returns the bounding box of the projectile.
     *
     * @return The bounding box of the projectile.
     */
    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(x, y, 38, 38);
    }

    /**
     * Gets the damage inflicted by the projectile.
     *
     * @return The damage inflicted by the projectile.
     */
    public int getDmg() {
        return dmg;
    }

    /**
     * Gets the type of damage dealt by the projectile.
     *
     * @return The type of damage dealt by the projectile.
     */

    public DamageType getDamageType() {
        return damageType;
    }
}