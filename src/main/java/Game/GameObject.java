package Game;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

//Abstract class of game object
public abstract class GameObject {
    //Base variables
    protected int x,y;
    protected ID id;
    protected int velX, velY;
    //Constructor
    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }
    //Tick
    public abstract void tick();
    //Render
    public abstract void render(Graphics g);
    //Collisions
    //For round objects
    public abstract Ellipse2D getIntersects();
    //For rectangle objects
    public abstract Rectangle2D getBounds();
    //Getters and setters

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }
}
