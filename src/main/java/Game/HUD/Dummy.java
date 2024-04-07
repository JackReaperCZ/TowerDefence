package Game.HUD;

import Game.GameObject;

import Game.Handler;
import Game.Towers.Cannon;
import Game.Game;
import Game.ID;
import Game.Towers.Tower;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;

public class Dummy extends MouseMotionAdapter {
    private Handler handler;
    private Image image;
    private Tower tower;
    private int radius;
    private int x = Game.WIDTH / 2;
    private int y = Game.HEIGHT / 2;

    public Dummy(Handler handler, Tower tower) {
        this.tower = tower;
        this.handler = handler;
        this.radius = tower.getRadius();
        if (tower instanceof Cannon) {
            ImageIcon icon = new ImageIcon("src/main/data/towers/cannon/cannon.png");
            this.image = icon.getImage();
        }
    }

    public void render(Graphics g) {
        g.setColor(new Color(211, 211, 211, 51));
        g.fillOval(x - (radius / 2), y - (radius / 2), radius, radius);
        g.drawImage(image, x - 38, y - 38, null);
    }

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

    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(x, y, 76, 76);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        x = mx;
        y = my;
    }

    public Tower getTower() {
        return tower;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
