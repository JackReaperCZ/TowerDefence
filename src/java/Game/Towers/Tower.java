package Game.Towers;

import Game.GameObject;
import Game.ID;
import Game.Handler;
import Game.Monsters.Comparators.HPComparator;
import Game.Monsters.Monster;
import Game.Monsters.Comparators.ProgressionComparator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * An abstract class representing a Tower in the game.
 */
public abstract class Tower extends GameObject {
    //Name of the tower for finding files
    protected String towerName;
    protected Handler handler;
    protected int price;
    protected int dmg;
    //Radius at the tower will see the enemy
    protected Integer radius;
    //Cooldown for projectile in ticks
    protected int projectileCooldown;
    protected int coolDownFlag;
    //Control variable to render the radius
    protected boolean renderRadius;

    //Shooting style of the tower
    public enum SHOOTING_STYLE {
        FIRST,
        LAST,
        WEAK,
        STRONG
    }

    protected SHOOTING_STYLE shootingStyle;
    //Array for remembering upgrades levels
    protected Integer[] upgrades = new Integer[3];
    //Array for remembering upgrades costs
    protected Integer[][] upgrade_cost = new Integer[3][3];
    protected Image[][] upgrade_images = new Image[3][3];
    //Arraylist of enemies in radius
    protected ArrayList<Monster> enemies = new ArrayList<>();
    //Sprite of the tower
    protected Image image;
    //Angle for rotation of the image
    protected double angle = 0;

    /**
     * Constructs a Tower with the specified coordinates and handler.
     *
     * @param x       The x-coordinate of the tower.
     * @param y       The y-coordinate of the tower.
     * @param handler The handler managing the tower.
     */
    public Tower(int x, int y, Handler handler) {
        super(x, y);
        this.shootingStyle = SHOOTING_STYLE.FIRST;
        this.renderRadius = false;
        this.handler = handler;
        this.id = ID.Tower;
    }

    /**
     * Handles collision detection between the tower's radius and objects.
     */
    public void collision() {
        // Getting all enemies in radius of the tower
        for (GameObject go : handler.objects) {
            if (go.getId() == ID.Monster && getIntersects().intersects(go.getBounds())) {
                enemies.add((Monster) go);
            }
        }
        // Selecting target based on shooting style
        if (!enemies.isEmpty()) {
            switch (shootingStyle) {
                case FIRST -> {
                    Collections.sort(enemies, new ProgressionComparator());
                    shoot(enemies.get(enemies.size() - 1).getX(), enemies.get(enemies.size() - 1).getY());
                    enemies.clear();
                }
                case LAST -> {
                    Collections.sort(enemies, new ProgressionComparator());
                    shoot(enemies.get(0).getX(), enemies.get(0).getY());
                    enemies.clear();
                }
                case STRONG -> {
                    Collections.sort(enemies, new HPComparator());
                    shoot(enemies.get(enemies.size() - 1).getX(), enemies.get(enemies.size() - 1).getY());
                    enemies.clear();
                }
                case WEAK -> {
                    Collections.sort(enemies, new HPComparator());
                    shoot(enemies.get(0).getX(), enemies.get(0).getY());
                    enemies.clear();
                }
            }
        }
    }

    /**
     * Loads tower assets.
     */
    public void getTowerAssets() {
        //Getting cost of upgrades
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("towers/" + this.towerName + "/upgrades.txt");
        if (inputStream != null) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                br.readLine(); // Get head out
                String line = br.readLine();
                while (line != null) {
                    String[] valuesS = line.split(",");
                    int cost = Integer.parseInt(valuesS[1]);
                    upgrade_cost[Integer.parseInt(valuesS[0].substring(1, 2)) - 1][Integer.parseInt(valuesS[0].substring(0, 1)) - 1] = cost;
                    line = br.readLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //Main png
        ImageIcon icon = null;
        try {
            icon = new ImageIcon(ImageIO.read(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("towers/" + this.towerName + "/" + this.towerName + ".png"))));
            this.image = icon.getImage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Getting upgrade images
        for (int i = 1; i <= 3; i++) {
            for (int y = 1; y <= 3; y++) {
                try {
                    icon = new ImageIcon(ImageIO.read(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("towers/" + this.towerName + "/up_0" + i + "/up_" + y + "" + i + "_" + this.towerName + ".png"))));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                upgrade_images[i - 1][y - 1] = icon.getImage();
            }
        }
    }

    /**
     * Upgrades the tower's attributes.
     *
     * @param i The index of the upgrade.
     */
    public void upgrade(int i) {
        if (upgrades[i] == null) {
            upgrades[i] = 1;
        } else {
            upgrades[i] += 1;
        }

        switch (i) {
            case 0 -> {
                this.radius += (int) (this.radius / 100) * (upgrades[i] * 10);
            }
            case 1 -> {
                this.coolDownFlag -= (int) ((float) ((float) (float) this.coolDownFlag / (float) 100.0) * (float) ((float) upgrades[i] * (float) 10.0));
            }
            case 2 -> {
                this.dmg += (int) ((float) ((float) ((float) this.dmg / (float) 100.0)) * (float) ((float) upgrades[i] * (float) 10.0));
            }
        }
    }

    /**
     * The method for tower to shoot.
     *
     * @param xTarget The x-coordinate of the target.
     * @param yTarget The y-coordinate of the target.
     */
    public abstract void shoot(int xTarget, int yTarget);

    /**
     * Calculates the angle for tower image rotation.
     *
     * @param x2 The x-coordinate of the target.
     * @param y2 The y-coordinate of the target.
     * @return The angle in radians.
     */
    public double calculateAngle(int x2, int y2) {
        double deltaY = y2 - y;
        double deltaX = x2 - x;
        return Math.atan2(deltaY, deltaX);
    }

    /**
     * Switches to the next shooting style.
     */
    public void nextShootingStyle() {
        switch (shootingStyle) {
            case FIRST -> shootingStyle = SHOOTING_STYLE.LAST;
            case LAST -> shootingStyle = SHOOTING_STYLE.WEAK;
            case WEAK -> shootingStyle = SHOOTING_STYLE.STRONG;
            case STRONG -> shootingStyle = SHOOTING_STYLE.FIRST;
        }
    }

    /**
     * Switches to the previous shooting style.
     */
    public void previousShootingStyle() {
        switch (shootingStyle) {
            case FIRST -> shootingStyle = SHOOTING_STYLE.STRONG;
            case LAST -> shootingStyle = SHOOTING_STYLE.FIRST;
            case WEAK -> shootingStyle = SHOOTING_STYLE.LAST;
            case STRONG -> shootingStyle = SHOOTING_STYLE.WEAK;
        }
    }

    /**
     * Updates the tower's state.
     */
    @Override
    public void tick() {
        if (projectileCooldown == 0) {
            collision();
        } else {
            projectileCooldown--;
        }
    }

    /**
     * Renders the tower.
     *
     * @param g The Graphics object to render with.
     */
    @Override
    public void render(Graphics g) {
        if (renderRadius) {
            g.setColor(new Color(211, 211, 211, 51));
            g.fillOval(x - (radius / 2) + 38, y - (radius / 2) + 38, radius, radius);
        }
        //Render main image
        AffineTransform tr = new AffineTransform();
        tr.translate(getX(), getY());
        tr.rotate(angle, (double) 76 / 2, (double) 76 / 2);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, tr, null);

        //Render upgrade images
        for (int i = 0; i <= 2; i++) {
            if (upgrades[i] != null) {
                g2d.drawImage(upgrade_images[i][upgrades[i] - 1], tr, null);
            }
        }
    }

    /**
     * Gets the hitbox for the tower's radius.
     *
     * @return The Ellipse2D representing the hitbox.
     */
    @Override
    public Ellipse2D getIntersects() {
        return new Ellipse2D.Double(x - ((double) radius / 2) + 16, y - ((double) radius / 2) + 16, radius, radius);
    }

    /**
     * Gets the hitbox for selecting the tower.
     *
     * @return The Rectangle2D representing the hitbox.
     */
    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(x, y, 76, 76);
    }

    //Setters and getters
    public Integer getRadius() {
        return radius;
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public Integer[] getUpgrades() {
        return upgrades;
    }

    public Image[][] getUpgrade_images() {
        return upgrade_images;
    }

    public Image getImage() {
        return image;
    }

    public Integer[][] getUpgrade_cost() {
        return upgrade_cost;
    }

    public void setRenderRadius(boolean renderRadius) {
        this.renderRadius = renderRadius;
    }

    public SHOOTING_STYLE getShootingStyle() {
        return shootingStyle;
    }

    public int getPrice() {
        return price;
    }

    public String getTowerName() {
        return towerName;
    }
}
