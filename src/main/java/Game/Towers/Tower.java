package Game.Towers;

import Game.GameObject;
import Game.ID;
import Game.Handler;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public abstract class Tower extends GameObject {
    protected Handler handler;
    //Radius at the tower will see the enemy
    protected Integer radius;
    //Cooldown for projectile in ticks
    protected int projectileCooldown;
    //Control variable to render the radius
    protected boolean renderRadius = true;
    //Sprite of the tower
    protected Image image;
    protected double angle = 0;

    //Constructor
    public Tower(int x, int y, Handler handler) {
        super(x, y);
        this.handler = handler;
        this.id = ID.Tower;
    }

    //Method to handle collision between the radius and objects
    public void collision() {
        for (GameObject go : handler.objects) {
            if (go.getId() == ID.Monster && getIntersects().intersects(go.getBounds())) {
                shoot(go.getX(), go.getY());
                break;
            }
        }
    }

    public abstract void shoot(int xTarget, int yTarget);

    //Method for calculating angle for image rotation
    public double calculateAngle(int x2, int y2) {
        double deltaY = y2 - y;
        double deltaX = x2 - x;
        return Math.atan2(deltaY, deltaX);
    }

    //Tick method
    @Override
    public void tick() {
        if (projectileCooldown == 0) {
            collision();
        } else {
            projectileCooldown--;
        }
    }

    //Render method
    @Override
    public void render(Graphics g) {
        if (renderRadius) {
            g.setColor(new Color(211, 211, 211, 51));
            g.fillOval(x - (radius / 2) + 38, y - (radius / 2) + 38, radius, radius);
        }
        AffineTransform tr = new AffineTransform();
        tr.translate(getX(), getY());
        tr.rotate(angle, (double) 76 / 2, (double) 76 / 2);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, tr, null);
    }

    //Hitbox for radius of the tower
    @Override
    public Ellipse2D getIntersects() {
        return new Ellipse2D.Double(x - ((double) radius / 2) + 16, y - ((double) radius / 2) + 16, radius, radius);
    }

    //Hitbox for selecting the tower
    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(x, y, 76, 76);
    }

    public Integer getRadius() {
        return radius;
    }
}
