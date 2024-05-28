package Game.HUD;

import Game.Handler;
import Game.Game;
import Game.Map.Map;
import Game.Map.MapData;
import Game.Map.MapStatus;
import Game.Map.Wave.Spawner;
import Game.AudioPlayer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Objects;

/**
 * The HUD class represents the heads-up display in the game.
 * It handles rendering various UI components such as the sidebar,
 * upgrade bar, pause menu, and game status.
 */
public class HUD extends UI {

    private Image coin;
    private Image health;
    private Sidebar sidebar;
    private Upgradebar upgradeBar;
    private PauseMenu pauseMenu;
    private Handler handler;

    /**
     * Constructs a HUD object with the specified handler and game.
     *
     * @param handler The handler for game objects.
     * @param game    The game instance.
     */
    public HUD(Handler handler, Game game) {
        this.handler = handler;
        this.sidebar = new Sidebar(handler, game);
        this.upgradeBar = new Upgradebar(game, handler);
        this.pauseMenu = new PauseMenu(handler);
        try {
            ImageIcon iconC = new ImageIcon(ImageIO.read(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("hud/coin.png"))));
            this.coin = iconC.getImage();
            ImageIcon iconH = new ImageIcon(ImageIO.read(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("hud/heart.png"))));
            this.health = iconH.getImage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates the HUD components.
     */
    public void tick() {
        sidebar.tick();
        upgradeBar.tick();
        pauseMenu.tick();
    }

    /**
     * Renders the HUD components.
     *
     * @param g The Graphics context to render on.
     */
    public void render(Graphics g) {
        //Render hearts and coins
        g.setColor(Color.WHITE);
        g.setFont(fontS);
        g.drawImage(coin, 920, 15, null);
        g.drawImage(health, 800, 15, null);
        g.drawString(Map.COIN + "", 970, 45);
        g.drawString(Map.HEALTH + "", 850, 45);
        if (this.handler.getMap() != null) {
            //Wave counter and play button
            if (Spawner.ACTUAL_WAVE >= this.handler.getMap().getSpawner().getWaves().size() - 1) {
                g.drawString("WAVE : LAST", (Game.WIDTH / 2) - 50, 35);
            } else {
                g.drawString("WAVE : " + (Spawner.ACTUAL_WAVE + 1), (Game.WIDTH / 2) - 50, 35);
            }
            //Next wave button
            if (!(Spawner.ACTUAL_WAVE == this.handler.getMap().getSpawner().getWaves().size())) {
                if (!Spawner.SPAWN && Map.mapStatus == MapStatus.IN_PROGRESS) {
                    g.setColor(new Color(150, 105, 25));
                    g.fillRoundRect(Game.WIDTH - 235, Game.HEIGHT - 120, 200, 70, 20, 20);

                    g.setColor(new Color(196, 164, 132));
                    g.fillRoundRect(Game.WIDTH - 230, Game.HEIGHT - 115, 190, 60, 20, 20);

                    g.setColor(Color.WHITE);
                    g.drawString("NEXT WAVE >>", Game.WIDTH - 225, Game.HEIGHT - 75);
                }
            }
            sidebar.render(g);
            upgradeBar.render(g);
            //render win and lost screen
            if (Map.mapStatus != MapStatus.IN_PROGRESS) {
                int x = Game.WIDTH;
                int y = Game.HEIGHT;
                g.setColor(new Color(210, 210, 210, 131));
                g.fillRect(0, 0, x, y);

                g.setColor(Color.WHITE);
                g.setFont(fontB);
                switch (Map.mapStatus) {
                    case WON -> g.drawString("GAME WON", (x / 2) - 350, 200);
                    case LOST -> g.drawString("GAME OVER", (x / 2) - 350, 200);
                }

                //Render buttons
                for (int i = 0; i <= 1; i++) {
                    g.setFont(fontS);
                    g.setColor(new Color(150, 105, 25));
                    g.fillRoundRect((x / 2) - 100, (y / 2) - 65 + (i * 85), 200, 75, 20, 20);

                    g.setColor(new Color(196, 164, 132));
                    g.fillRoundRect((x / 2) - 95, (y / 2) - 60 + (i * 85), 190, 65, 20, 20);

                    g.setColor(Color.WHITE);
                    switch (i) {
                        case 0 -> g.drawString("RESTART", (x / 2) - 60, (y / 2) - 20);
                        case 1 -> g.drawString("MAIN MENU", (x / 2) - 80, (y / 2) + 65);
                    }
                }
            }
            pauseMenu.render(g);
        }
    }

    /**
     * Handles mouse press events on the HUD.
     *
     * @param e The MouseEvent object.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if (Game.gameState == Game.STATE.GAME) {
            if (!(Spawner.ACTUAL_WAVE == this.handler.getMap().getSpawner().getWaves().size())) {
                if (!Spawner.SPAWN && Map.mapStatus == MapStatus.IN_PROGRESS) {
                    if (mouseOver(mx, my, Game.WIDTH - 235, Game.HEIGHT - 120, 200, 70)) {
                        Spawner.SPAWN = true;
                    }
                }
            }
            if (Map.mapStatus != MapStatus.IN_PROGRESS) {
                if (mouseOver(mx, my, (Game.WIDTH / 2) - 100, (Game.HEIGHT / 2) - 65, 200, 75)) {
                    MapData mapData = handler.getMap().getMapData();
                    AudioPlayer.getMusic(handler.getMap().getMapData().getName()).stop();
                    handler.clearHandler();
                    this.handler.setMap(new Map(handler, mapData));
                }
                if (mouseOver(mx, my, (Game.WIDTH / 2) - 100, (Game.HEIGHT / 2) + 20, 200, 75)) {
                    AudioPlayer.getMusic(handler.getMap().getMapData().getName()).stop();
                    AudioPlayer.getMusic("menu").loop();
                    handler.clearHandler();
                    Game.gameState = Game.STATE.MENU;
                }
            }
        }
    }

    /**
     * Retrieves the sidebar.
     *
     * @return The sidebar.
     */
    public Sidebar getSidebar() {
        return sidebar;
    }

    /**
     * Retrieves the upgrade bar.
     *
     * @return The upgrade bar.
     */
    public Upgradebar getUpgradeBar() {
        return upgradeBar;
    }

    /**
     * Retrieves the pause menu.
     *
     * @return The pause menu.
     */
    public PauseMenu getPauseMenu() {
        return pauseMenu;
    }
}
