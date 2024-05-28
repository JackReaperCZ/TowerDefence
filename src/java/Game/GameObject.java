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
    /**
     * x and y location of GameObject
     */
    protected int x, y;
    /**
     * Object id (type)
     */
    protected ID id;
    /**
     * Object velocities
     */
    protected int velX, velY;
    /**
     * Object identification number
     */
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

    /**
     * Sets the x-coordinate of this object.
     *
     * @param x the new x-coordinate value
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the y-coordinate of this object.
     *
     * @param y the new y-coordinate value
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Gets the x-coordinate of this object.
     *
     * @return the current x-coordinate value
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y-coordinate of this object.
     *
     * @return the current y-coordinate value
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the ID of this object.
     *
     * @return the current ID
     */
    public ID getId() {
        return id;
    }

    /**
     * Sets the x-velocity of this object.
     *
     * @param velX the new x-velocity value
     */
    public void setVelX(int velX) {
        this.velX = velX;
    }

    /**
     * Sets the y-velocity of this object.
     *
     * @param velY the new y-velocity value
     */
    public void setVelY(int velY) {
        this.velY = velY;
    }

    /**
     * Sets the identification number of this object.
     *
     * @param identification the new identification number
     */
    public void setIdentification(Integer identification) {
        this.identification = identification;
    }
}
