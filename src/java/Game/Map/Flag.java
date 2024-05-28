package Game.Map;

/**
 * The Flag class represents a flag on the game map, used to define the path that monsters follow.
 */
public class Flag {
    /**
     * x and y location of the flag
     */
    private int x, y;
    /**
     * Pointer to the next flag
     */
    private Flag nextFlag;

    /**
     * Constructs a Flag object with the specified coordinates.
     *
     * @param x The x-coordinate of the flag.
     * @param y The y-coordinate of the flag.
     */
    public Flag(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the next flag in the path.
     *
     * @return The next flag in the path.
     */
    public Flag getNextFlag() {
        return nextFlag;
    }

    /**
     * Sets the next flag in the path.
     *
     * @param nextFlag The next flag in the path.
     */
    public void setNextFlag(Flag nextFlag) {
        this.nextFlag = nextFlag;
    }

    /**
     * Gets the x-coordinate of the flag.
     *
     * @return The x-coordinate of the flag.
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the x-coordinate of the flag.
     *
     * @param x The x-coordinate of the flag.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Gets the y-coordinate of the flag.
     *
     * @return The y-coordinate of the flag.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the y-coordinate of the flag.
     *
     * @param y The y-coordinate of the flag.
     */
    public void setY(int y) {
        this.y = y;
    }
}
