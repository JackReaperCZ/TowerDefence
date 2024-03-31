package Game.Towers;

import Game.GameObject;
import Game.ID;
import Game.Handler;
import Game.Monsters.Comparators.HPComparator;
import Game.Monsters.Monster;
import Game.Monsters.Comparators.ProgressionComparator;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

public abstract class Tower extends GameObject {
    //Name of the tower for finding files
    protected String towerName;
    protected Handler handler;
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

    //Constructor
    public Tower(int x, int y, Handler handler) {
        super(x, y);
        this.shootingStyle = SHOOTING_STYLE.FIRST;
        this.renderRadius = false;
        this.handler = handler;
        this.id = ID.Tower;
    }

    //Method to handle collision between the radius and objects
    public void collision() {
        //Getting all enemies in radius of the tower
        for (GameObject go : handler.objects) {
            if (go.getId() == ID.Monster && getIntersects().intersects(go.getBounds())) {
                enemies.add((Monster) go);
            }
        }
        //Selecting target based on shooting style
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

    //Loading tower assets
    public void getTowerAssets() {
        //Getting cost of upgrades
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/data/towers/" + this.towerName + "/upgrades.txt"));
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

        //Main png
        ImageIcon icon = new ImageIcon("src/main/data/towers/" + this.towerName + "/" + this.towerName + ".png");
        this.image = icon.getImage();

        //Getting upgrade images
        for (int i = 1; i <= 3; i++) {
            for (int y = 1; y <= 3; y++) {
                icon = new ImageIcon("src/main/data/towers/" + this.towerName + "/up_0" + i + "/up_" + y + "" + i + "_" + this.towerName + ".png");
                upgrade_images[i - 1][y - 1] = icon.getImage();
            }
        }
    }

    //Method to upgrade the tower
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

    //Shoot method
    public abstract void shoot(int xTarget, int yTarget);

    //Method for calculating angle for image rotation
    public double calculateAngle(int x2, int y2) {
        double deltaY = y2 - y;
        double deltaX = x2 - x;
        return Math.atan2(deltaY, deltaX);
    }

    //Tick method
    @Override
    public void tick() {
        if (projectileCooldown == 0) {
            collision();
        } else {
            projectileCooldown--;
        }
    }

    //Render method
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

    //Hitbox for radius of the tower
    @Override
    public Ellipse2D getIntersects() {
        return new Ellipse2D.Double(x - ((double) radius / 2) + 16, y - ((double) radius / 2) + 16, radius, radius);
    }

    //Hitbox for selecting the tower
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
}
