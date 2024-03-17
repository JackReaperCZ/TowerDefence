package Game;

import java.awt.Graphics;
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
    //Getters and setters
    public abstract void tick();

    public abstract void render(Graphics g);

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
