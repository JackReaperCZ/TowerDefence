package Game.HUD;

import Game.Handler;
import Game.Game;
import Game.Map.Map;
import Game.Map.Wave.Spawner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

//Class for HUD rendering layout
public class HUD extends UI {
    private Image coin;
    private Image health;
    private Sidebar sidebar;
    private Upgradebar upgradeBar;
    private PauseMenu pauseMenu;

    public HUD(Handler handler, Game game) {
        this.sidebar = new Sidebar(handler, game);
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

        //Wave counter and play button
        g.drawString("WAVE : " + (Spawner.ACTUAL_WAVE + 1), (Game.WIDTH / 2) - 50, 35);
        if (!Spawner.SPAWN) {
            g.setColor(new Color(150, 105, 25));
            g.fillRoundRect(Game.WIDTH - 235, Game.HEIGHT - 120, 200, 70, 20, 20);

            g.setColor(new Color(196, 164, 132));
            g.fillRoundRect(Game.WIDTH - 230, Game.HEIGHT - 115, 190, 60, 20, 20);

            g.setColor(Color.WHITE);
            g.drawString("NEXT WAVE >>", Game.WIDTH - 225, Game.HEIGHT - 80);
        }
        sidebar.render(g);
        upgradeBar.render(g);
        pauseMenu.render(g);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (!Spawner.SPAWN && Game.gameState == Game.STATE.GAME) {
            if (mouseOver(mx, my, Game.WIDTH - 235, Game.HEIGHT - 120, 200, 70)) {
                Spawner.SPAWN = true;
            }
        }
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
