package Game.HUD;

import Game.GameObject;

import Game.Handler;
import Game.Game;
import Game.ID;
import Game.Towers.Tower;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.Objects;

/**
 * The Dummy class represents a preview of a tower before placement on the map.
 * It provides a visual representation of the tower's range and appearance.
 */
public class Dummy extends MouseMotionAdapter {
    /**
     * The handler associated with this object.
     */
    private Handler handler;

    /**
     * The image associated with this object.
     */
    private Image image;

    /**
     * The tower associated with this object.
     */
    private Tower tower;

    /**
     * The radius of this object.
     */
    private int radius;

    /**
     * The x-coordinate of this object, initialized to the center of the game width.
     */
    private int x = Game.WIDTH / 2;

    /**
     * The y-coordinate of this object, initialized to the center of the game height.
     */
    private int y = Game.HEIGHT / 2;

    /**
     * Constructs a Dummy object with the specified handler and tower.
     *
     * @param handler The handler for game objects.
     * @param tower   The tower to be previewed.
     */
    public Dummy(Handler handler, Tower tower) {
        this.tower = tower;
        this.handler = handler;
        this.radius = tower.getRadius();
        try {
            ImageIcon icon = new ImageIcon(ImageIO.read(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("towers/" + tower.getTowerName() + "/" + tower.getTowerName() + ".png"))));
            this.image = icon.getImage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Renders the dummy tower preview on the graphics context.
     *
     * @param g The Graphics context to render on.
     */
    public void render(Graphics g) {
        g.setColor(new Color(211, 211, 211, 51));
        g.fillOval(x - (radius / 2), y - (radius / 2), radius, radius);
        g.drawImage(image, x - 38, y - 38, null);
    }

    /**
     * Checks if the tower can be placed at the current location.
     *
     * @return True if the tower can be placed, false otherwise.
     */
    public boolean place() {
        for (GameObject go : handler.objects) {
            if (go.getId() == ID.Tower && getBounds().intersects(go.getBounds())) {
                return false;
            }
        }
        for (Rectangle2D rc : handler.getMap().getBounds()) {
            if (rc.intersects(getBounds())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Retrieves the bounding rectangle of the dummy tower.
     *
     * @return The bounding rectangle.
     */
    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(x - 38, y - 38, 76, 76);
    }

    /**
     * Updates the position of the dummy tower based on mouse movement.
     *
     * @param e The MouseEvent object.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        x = mx;
        y = my;
    }

    /**
     * Retrieves the tower being previewed.
     *
     * @return The tower being previewed.
     */
    public Tower getTower() {
        return tower;
    }

    /**
     * Retrieves the x-coordinate of the dummy tower.
     *
     * @return The x-coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Retrieves the y-coordinate of the dummy tower.
     *
     * @return The y-coordinate.
     */
    public int getY() {
        return y;
    }
}
