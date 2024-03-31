package Game.HUD;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;

public abstract class UI extends MouseAdapter {
    protected Font fontS;
    protected Font fontB;

    public UI() {
        try {
            InputStream is = new BufferedInputStream(new FileInputStream("src/main/data/font/ComicSansMS3.ttf"));
            Font comicSans = Font.createFont(Font.TRUETYPE_FONT, is);
            this.fontS = comicSans.deriveFont(Font.BOLD, 24f);
            this.fontB = comicSans.deriveFont(120f);
        } catch (Exception e){

        }
    }

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
