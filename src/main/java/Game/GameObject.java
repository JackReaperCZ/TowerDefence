package Game;

import java.awt.*;

//Abstract class of game object
public abstract class GameObject {
    //Base variables
    protected int x,y;
    protected ID id;
    protected double velX, velY;
    //Constructor
    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }
    //Tick
    public abstract void tick();
    //Render
    public abstract void render(Graphics g);
    //Collision
    public abstract Rectangle getBounds();
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

    public double getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public double getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }
}
