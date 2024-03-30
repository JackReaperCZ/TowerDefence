package Game.HUD;

import Game.Handler;
import Game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.*;

//Class for HUD rendering layout
public class HUD extends UI {
    //Gold variable
    public static int COIN = 550;
    //Gold variable
    public static int HEALTH = 100;
    private Handler handler;
    private Game game;
    private Image coin;
    private Image health;
    private Sidebar sidebar;

    public HUD(Handler handler, Game game) {
        this.game = game;
        this.handler = handler;
        this.sidebar = new Sidebar(handler,game);

        ImageIcon iconC = new ImageIcon("src/main/data/hud/coin.png");
        this.coin = iconC.getImage();
        ImageIcon iconH = new ImageIcon("src/main/data/hud/heart.png");
        this.health = iconH.getImage();
        try {
            InputStream is = new BufferedInputStream(new FileInputStream("src/main/data/font/ComicSansMS3.ttf"));
            Font comicSans = Font.createFont(Font.TRUETYPE_FONT, is);
            this.fontS = comicSans.deriveFont(24f);
            this.fontB = comicSans.deriveFont(120f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
    }

    public void tick() {
        sidebar.tick();
    }

    public void render(Graphics g) {
        //Render hearts and coins
        g.setColor(Color.WHITE);
        g.setFont(fontS);
        g.drawImage(coin, 920, 15, null);
        g.drawImage(health, 800, 15, null);
        g.drawString(COIN + "", 970, 45);
        g.drawString(HEALTH + "", 850, 45);

        sidebar.render(g);
    }

    //Check if we are hovering over button
    public boolean mouseOver(int mx, int my, int x, int y, int w, int h) {
        if (mx > x && mx < x + w) {
            if (my > y && my < y + h) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
    }

    public Sidebar getSidebar() {
        return sidebar;
    }
}
