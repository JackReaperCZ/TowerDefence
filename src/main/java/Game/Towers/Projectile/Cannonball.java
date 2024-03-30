package Game.Towers.Projectile;

import Game.Handler;

import javax.swing.*;

public class Cannonball extends Projectile{
    public Cannonball(int x, int y, int x2, int y2, Handler handler) {
        super(x, y, handler);
        this.speed = 25;
        velCalculate(x2, y2, speed);
        ImageIcon icon = new ImageIcon("src/main/data/projectile/cannonball/cannonball.png");
        this.image = icon.getImage();
    }
}
