package Game.Towers.Projectile;

import Game.Handler;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class Shuriken extends Projectile{
    private int angle = 0;
    public Shuriken(int x, int y, int x2, int y2, int dmg,Handler handler) {
        super(x, y, dmg, handler);
        this.speed = 35;
        this.damageType = DamageType.NORMAL;
        this.name = "shuriken";
        velCalculate(x2, y2, speed);
        getAssets();
    }
    @Override
    public void tick() {
        x  += velX;
        y += velY;
        angle++;
    }
    @Override
    public void render(Graphics g) {
        AffineTransform tr = new AffineTransform();
        tr.translate(getX(), getY());
        tr.rotate(angle, (double) 38 / 2, (double) 38 / 2);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, tr, null);
    }
}
