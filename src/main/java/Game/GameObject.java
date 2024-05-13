package Game;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Objects;

/**
 * An abstract class representing a game object.
 */
public abstract class GameObject {
    //Base variables
    protected int x, y;
    protected ID id;
    protected int velX, velY;
    protected Integer identification;

    /**
     * Constructs a GameObject with the specified coordinates.
     *
     * @param x The x-coordinate of the game object.
     * @param y The y-coordinate of the game object.
     */
    public GameObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Updates the state of the game object.
     */
    public abstract void tick();

    /**
     * Renders the game object.
     *
     * @param g The Graphics object to render the game object.
     */
    public abstract void render(Graphics g);

    /**
     * Returns the shape representing the collision area for round objects.
     *
     * @return The Ellipse2D shape representing the collision area.
     */
    public abstract Ellipse2D getIntersects();

    /**
     * Returns the shape representing the collision area for rectangle objects.
     *
     * @return The Rectangle2D shape representing the collision area.
     */
    public abstract Rectangle2D getBounds();

    /**
     * Checks if this game object is equal to another object.
     *
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameObject that = (GameObject) o;
        return Objects.equals(identification, that.identification);
    }

    /**
     * Generates a hash code for this game object.
     *
     * @return The hash code value for this game object.
     */
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
