package Game.Towers.Projectile;

import Game.GameObject;
import Game.Handler;
import Game.ID;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Projectile extends GameObject {
    protected Handler handler;

    public Projectile(int x, int y, int x2, int y2, int speed, Handler handler) {
        super(x, y);
        this.handler = handler;
        this.id = ID.Projectile;
        velCalculate(x2, y2, speed);
    }
    //Method that calculates vel of the projectile
    public void velCalculate(int x2, int y2, int speed) {
        int yc = y2 - y;
        int xc = x2 - x;
        double c = Math.sqrt((yc * yc) + (xc * xc));
        this.velX = (int) Math.round((speed*xc)/c);
        this.velY = (int) Math.round((speed*yc)/c);
    }
    //Tick method
    @Override
    public void tick() {
        x  += velX;
        y += velY;
    }
    //Render method
    @Override
    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(x, y, 16, 16);
    }
    //Hitboxes
    @Override
    public Ellipse2D getIntersects() {
        return null;
    }
    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(x,y,16,16);
    }
}