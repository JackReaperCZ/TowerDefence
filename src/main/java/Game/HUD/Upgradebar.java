package Game.HUD;

import java.awt.*;
import java.awt.event.MouseEvent;

import Game.*;
import Game.Map.Map;
import Game.Map.MapStatus;
import Game.Towers.Tower;

import javax.swing.*;

public class Upgradebar extends UI {
    private final Game game;
    private final Handler handler;
    private final Image coin;
    private Tower selectedTower;
    private int upgradeBarXREF = 0;
    private boolean upgradeBarOpened = false;

    public Upgradebar(Game game, Handler handler) {
        this.game = game;
        this.handler = handler;
        ImageIcon iconC = new ImageIcon("src/main/data/hud/coin.png");
        this.coin = iconC.getImage();
    }

    public void openUpgradeBar() {
        game.getHud().getSidebar().closeSideBar();
        this.upgradeBarOpened = true;
    }

    public void closeUpgradeBar() {
        this.upgradeBarOpened = false;
    }

    public String getUpgradeMsg(int i) {
        switch (i) {
            case 0 -> {
                if (selectedTower.getUpgrades()[i] == null) {
                    return "+" + (10) + "% range";
                } else if (selectedTower.getUpgrades()[i] == 3) {
                    return "+30% range";
                } else {
                    return "+" + ((selectedTower.getUpgrades()[i] + 1) * 10) + "% range";
                }
            }
            case 1 -> {
                if (selectedTower.getUpgrades()[i] == null) {
                    return "+" + (10) + "% fire rate";
                } else if (selectedTower.getUpgrades()[i] == 3) {
                    return "+30% fire rate";
                } else {
                    return "+" + ((selectedTower.getUpgrades()[i] + 1) * 10) + "% fire rate";
                }
            }
            case 2 -> {
                if (selectedTower.getUpgrades()[i] == null) {
                    return "+" + (10) + "% damage";
                } else if (selectedTower.getUpgrades()[i] == 3) {
                    return "+30% damage";
                } else {
                    return "+" + ((selectedTower.getUpgrades()[i] + 1) * 10) + "% damage";
                }
            }
        }
        return null;
    }

    @Override
    public void render(Graphics g) {
        if (selectedTower != null) {
            g.setColor(new Color(150, 105, 25));
            g.fillRoundRect(upgradeBarXREF - 300, 0, 300, 750, 20, 20);

            //Render tower image
            g.setColor(new Color(196, 164, 132));
            g.fillRoundRect(upgradeBarXREF - 290, 10, 280, 280, 20, 20);
            g.drawImage(selectedTower.getImage(), upgradeBarXREF - 280, 20, 260, 260, null);
            for (int i = 0; i <= 2; i++) {
                if (selectedTower.getUpgrades()[i] != null) {
                    g.drawImage(selectedTower.getUpgrade_images()[i][selectedTower.getUpgrades()[i] - 1], upgradeBarXREF - 280, 20, 260, 260, null);
                }
            }
            int cost;
            //Rendering upgrade buttons
            for (int i = 0; i <= 2; i++) {
                g.setColor(new Color(196, 164, 132));
                g.fillRoundRect(upgradeBarXREF - 290, 300 + (105 * i), 280, 95, 20, 20);
                if (selectedTower.getUpgrades()[i] == null) {
                    cost = selectedTower.getUpgrade_cost()[i][0];
                } else if (selectedTower.getUpgrades()[i] == 3) {
                    cost = 0;
                } else {
                    cost = selectedTower.getUpgrade_cost()[i][selectedTower.getUpgrades()[i]];
                }
                if (Map.COIN >= cost) {
                    g.setColor(new Color(180, 196, 36));
                } else {
                    g.setColor(Color.GRAY);
                }
                g.fillRoundRect(upgradeBarXREF - 290, 300 + (105 * i), 95, 95, 20, 20);
                g.setColor(Color.WHITE);
                if (selectedTower.getUpgrades()[i] != null) {
                    if (selectedTower.getUpgrades()[i] == 3) {
                        g.drawString("MAX", upgradeBarXREF - 270, 340 + (105 * i));
                    } else {
                        g.drawString(cost + "", upgradeBarXREF - 270, 340 + (105 * i));
                    }
                } else {
                    g.drawString(cost + "", upgradeBarXREF - 270, 340 + (105 * i));
                }
                g.drawImage(coin, upgradeBarXREF - 265, 345 + (105 * i), 35, 35, null);

                g.drawString(getUpgradeMsg(i), upgradeBarXREF - 185, 350 + (105 * i));

                String msg = "";

                //Draw firing mode switch
                switch (selectedTower.getShootingStyle()) {
                    case LAST -> msg = "<< SHOOT LAST >>";
                    case FIRST -> msg = "<< SHOOT FIRST >>";
                    case STRONG -> msg = "<< SHOOT STRONG >>";
                    case WEAK -> msg = "<< SHOOT WEAK >>";
                }
                //Draw render button
                g.drawString(msg, upgradeBarXREF - 270, 650);

                g.setColor(new Color(88, 0, 0));
                g.fillRoundRect(upgradeBarXREF - 290, 665, 280, 75, 20, 20);

                g.setColor(Color.RED);
                g.fillRoundRect(upgradeBarXREF - 285, 670, 270, 65, 20, 20);

                g.setColor(Color.WHITE);
                g.drawString("SELL", upgradeBarXREF - 180, 710);
            }
        }
    }

    @Override
    public void tick() {
        int velX = 20;
        if (upgradeBarOpened) {
            if (upgradeBarXREF != 300) {
                upgradeBarXREF += velX;
            }
        } else {
            if (upgradeBarXREF != 0) {
                upgradeBarXREF -= velX;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        boolean clicked = false;
        Tower lastTower = selectedTower;
        Point p = new Point(mx, my);
        if (Game.gameState == Game.STATE.GAME && Map.mapStatus == MapStatus.IN_PROGRESS) {
            if (upgradeBarOpened) {
                for (int i = 0; i <= 2; i++) {
                    if (mouseOver(mx, my, upgradeBarXREF - 290, 300 + (105 * i), 95, 95)) {
                        int cost = 0;
                        if (selectedTower.getUpgrades()[i] == null) {
                            cost = selectedTower.getUpgrade_cost()[i][0];
                        } else {
                            if (!(selectedTower.getUpgrades()[i] == 3)) {
                                cost = selectedTower.getUpgrade_cost()[i][selectedTower.getUpgrades()[i]];
                            }
                        }
                        if (Map.COIN >= cost) {
                            if (selectedTower.getUpgrades()[i] != null) {
                                if (!(selectedTower.getUpgrades()[i] == 3)) {
                                    Map.COIN -= cost;
                                    selectedTower.upgrade(i);
                                }
                            } else {
                                Map.COIN -= cost;
                                selectedTower.upgrade(i);
                            }
                        } else {
                            //Play sound effect
                        }
                    }
                }
                if (mouseOver(mx, my, upgradeBarXREF - 300, 620, 150, 45)) {
                    selectedTower.previousShootingStyle();
                }
                if (mouseOver(mx, my, upgradeBarXREF - 150, 620, 150, 45)) {
                    selectedTower.nextShootingStyle();
                }
                if (mouseOver(mx, my, upgradeBarXREF - 285, 670, 270, 65)) {
                    int cost = selectedTower.getPrice();
                    for (Integer y : selectedTower.getUpgrades()) {
                        if (y != null) {
                            cost += y * 100;
                        }
                    }
                    Map.COIN += cost;
                    closeUpgradeBar();
                    handler.removeGameObject(selectedTower);
                }
            }
            if (mouseOver(mx, my, upgradeBarXREF - 300, 0, 300, 750)) {
                clicked = true;
            } else {
                for (GameObject go : handler.objects) {
                    if (go.getId() == ID.Tower && go.getBounds().contains(p)) {
                        selectedTower = (Tower) go;
                        selectedTower.setRenderRadius(true);
                        clicked = true;
                        openUpgradeBar();
                    }
                }
            }
            if (clicked) {
                if (lastTower != selectedTower && lastTower != null) {
                    lastTower.setRenderRadius(false);
                }
            }
            if (!clicked) {
                closeUpgradeBar();
                if (selectedTower != null) {
                    selectedTower.setRenderRadius(false);
                }
            }
        }
    }

    public boolean isUpgradeBarOpened() {
        return upgradeBarOpened;
    }
}
