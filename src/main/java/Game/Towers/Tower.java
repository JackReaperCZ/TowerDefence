package Game.Towers;

import Game.GameObject;
import Game.ID;
import Game.Handler;
import Game.Towers.Projectile.Projectile;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class Tower extends GameObject {
    protected Handler handler;
    //Radius at the tower will see the enemy
    protected Integer radius;
    //Cooldown for projectile in ticks
    protected int projectileCooldown = 0;
    //Control variable to render the radius
    protected boolean renderRadius = true;
    //Constructor
    public Tower(int x, int y, Handler handler) {
        super(x, y);
        this.handler = handler;
        this.id = ID.Tower;
        this.radius = 250;
    }
    //Tick method
    @Override
    public void tick() {
        if (projectileCooldown == 120){
            collision();
        } else {
            projectileCooldown++;
        }
    }
    //Method to handle collisions with the radius zone
    public void collision(){
        for (GameObject go: handler.objects) {
            if (go.getId() == ID.Monster && getIntersects().intersects(go.getBounds())){
                handler.addGameObject(new Projectile(x+8,y+8,go.getX()+8,go.getY()+8,25,handler));
                projectileCooldown = 0;
            }
        }
    }
    //Render method
    @Override
    public void render(Graphics g) {
        if (renderRadius) {
            g.setColor(Color.GRAY);
            ((Graphics2D) g).draw(new Ellipse2D.Double(x - ((double) radius / 2) + 16, y - ((double) radius / 2) + 16, radius, radius));
        }
        g.setColor(Color.WHITE);
        g.fillRect(x, y, 32, 32);
    }
    //Hitboxes
    @Override
    public Ellipse2D getIntersects() {
        return new Ellipse2D.Double(x - ((double) radius / 2) + 16, y - ((double) radius / 2) + 16, radius, radius);
    }
    @Override
    public Rectangle2D getBounds() {
        return null;
    }
}
