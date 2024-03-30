package Game.HUD;

import java.awt.*;
import java.awt.event.MouseAdapter;

public abstract class UI extends MouseAdapter {
    protected Font fontS;
    protected Font fontB;

    public abstract void render(Graphics g);

    public abstract void tick();

    //Check if we are hovering over button
    public boolean mouseOver(int mx, int my, int x, int y, int w, int h) {
        if (mx > x && mx < x + w) {
            if (my > y && my < y + h) {
                return true;
            }
        }
        return false;
    }
}
