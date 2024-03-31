package Game.HUD;

import Game.Handler;
import Game.Game;
import Game.Map.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.*;

//Class for HUD rendering layout
public class HUD extends UI {
    private Image coin;
    private Image health;
    private Sidebar sidebar;
    private Upgradebar upgradeBar;
    private PauseMenu pauseMenu;

    public HUD(Handler handler, Game game) {
        this.sidebar = new Sidebar(handler,game);
        this.upgradeBar = new Upgradebar(game, handler);
        this.pauseMenu = new PauseMenu(handler);

        ImageIcon iconC = new ImageIcon("src/main/data/hud/coin.png");
        this.coin = iconC.getImage();
        ImageIcon iconH = new ImageIcon("src/main/data/hud/heart.png");
        this.health = iconH.getImage();
    }

    public void tick() {
        sidebar.tick();
        upgradeBar.tick();
        pauseMenu.tick();
    }

    public void render(Graphics g) {
        //Render hearts and coins
        g.setColor(Color.WHITE);
        g.setFont(fontS);
        g.drawImage(coin, 920, 15, null);
        g.drawImage(health, 800, 15, null);
        g.drawString(Map.COIN + "", 970, 45);
        g.drawString(Map.HEALTH + "", 850, 45);

        sidebar.render(g);
        upgradeBar.render(g);
        pauseMenu.render(g);
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

    public Upgradebar getUpgradeBar() {
        return upgradeBar;
    }

    public PauseMenu getPauseMenu() {
        return pauseMenu;
    }
}
