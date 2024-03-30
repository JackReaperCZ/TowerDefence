package Game;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Objects;

//Abstract class of game object
public abstract class GameObject {
    //Base variables
    protected int x,y;
    protected ID id;
    protected int velX, velY;
    protected Integer identification;
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

    //equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameObject that = (GameObject) o;
        return Objects.equals(identification, that.identification);
    }
    @Override
    public int hashCode() {
        return Objects.hash(identification);
    }

    //Getters and setters

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public ID getId() {
        return id;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public void setIdentification(Integer identification) {
        this.identification = identification;
    }
}
