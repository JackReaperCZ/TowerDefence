package Game.Towers.Projectile;

import Game.GameObject;
import Game.Handler;
import Game.ID;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public abstract class Projectile extends GameObject {
    protected Handler handler;
    protected Image image;
    protected int speed;
    protected int dmg;
    protected String name;
    protected DamageType damageType;

    public Projectile(int x, int y,int dmg,Handler handler) {
        super(x, y);
        this.dmg = dmg;
        this.handler = handler;
        this.id = ID.Projectile;
    }
    //Method that calculates vel of the projectile
    public void velCalculate(int x2, int y2, int speed) {
        int yc = y2 - y;
        int xc = x2 - x;
        double c = Math.sqrt((yc * yc) + (xc * xc));
        this.velX = (int) Math.round((speed*xc)/c);
        this.velY = (int) Math.round((speed*yc)/c);
    }
    public void getAssets(){
        ImageIcon icon = new ImageIcon("src/main/data/projectile/"+name+"/"+name+".png");
        this.image = icon.getImage();
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
        g.drawImage(image,x,y,null);
    }
    //Hitboxes
    @Override
    public Ellipse2D getIntersects() {
        return null;
    }
    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(x,y,38,38);
    }

    public int getDmg() {
        return dmg;
    }
}