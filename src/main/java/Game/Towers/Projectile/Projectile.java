package Game.Towers.Projectile;

import Game.GameObject;
import Game.Handler;
import Game.ID;

import java.awt.*;

public class Projectile extends GameObject {
    protected Handler handler;
    public Projectile(int x, int y,Handler handler) {
        super(x, y);
        this.handler = handler;
        this.id = ID.Projectile;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(x,y,16,16);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,16,16);
    }
}