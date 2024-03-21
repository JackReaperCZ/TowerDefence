package Game.Towers;

import Game.GameObject;
import Game.ID;
import Game.Handler;

import java.awt.*;

public class Tower extends GameObject {
    protected Handler handler;
    public Tower(int x, int y,Handler handler) {
        super(x, y);
        this.handler = handler;
        this.id = ID.Tower;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x,y,32,32);
    }

    @Override
    public Rectangle getBounds() {
        return null;
    }
}
