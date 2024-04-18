package Game.HUD;

import Game.Game;
import Game.Handler;
import Game.Map.Map;
import Game.Map.MapStatus;
import Game.Towers.Cannon;
import Game.Towers.Mage;
import Game.Towers.Ninja;
import Game.AudioPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class Sidebar extends UI {
    private boolean sideBarOpened = false;
    private int sideBarXREF = 0;
    private int velX = 5;
    private Handler handler;
    private Image coin;
    private Image cannon;
    private Image ninja;
    private Image mage;
    private Game game;
    private Dummy dummy;

    public Sidebar(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;
        ImageIcon iconC = new ImageIcon("src/main/data/hud/coin.png");
        this.coin = iconC.getImage();
        ImageIcon icon = new ImageIcon("src/main/data/towers/cannon/cannon.png");
        this.cannon = icon.getImage();
        icon = new ImageIcon("src/main/data/towers/ninja/ninja.png");
        this.ninja = icon.getImage();
        icon = new ImageIcon("src/main/data/towers/mage/mage.png");
        this.mage = icon.getImage();
    }

    @Override
    public void tick() {
        if (sideBarOpened) {
            if (sideBarXREF != 150) {
                sideBarXREF += velX;
            }
        } else {
            if (sideBarXREF != 0) {
                sideBarXREF -= velX;
            }
        }
    }

    public void opedSideBar() {
        this.sideBarOpened = true;
    }

    public void closeSideBar() {
        this.sideBarOpened = false;
    }

    @Override
    public void render(Graphics g) {
        //Sidebar open button
        g.setColor(new Color(150, 105, 25));
        g.fillRect(sideBarXREF, 0, 65, 75);
        g.fillRect(sideBarXREF + 55, 10, 20, 55);
        g.fillArc(sideBarXREF + 55, 0, 20, 20, 0, 90);
        g.fillArc(sideBarXREF + 55, 55, 20, 20, 0, -90);

        g.setColor(new Color(196, 164, 132));
        g.fillRoundRect(sideBarXREF + 5, 5, 63, 63, 20, 20);

        g.setColor(Color.WHITE);
        g.setFont(fontB);
        if (sideBarOpened) {
            g.drawString("<", sideBarXREF + 13, 75);
        } else {
            g.drawString(">", sideBarXREF + 13, 75);
        }

        //Sidebar
        g.setColor(new Color(150, 105, 25));
        g.fillRect(sideBarXREF - 150, 0, 150, 500);
        g.fillRect(sideBarXREF - 140, 500, 130, 10);
        g.fillArc(sideBarXREF - 150, 490, 20, 20, 180, 90);
        g.fillArc(sideBarXREF - 20, 490, 20, 20, 0, -90);

        //Cannon
        g.setColor(new Color(196, 164, 132));
        g.fillRoundRect(sideBarXREF - 145, 5, 140, 140, 20, 20);
        g.drawImage(cannon, sideBarXREF - 140, 10, 130, 130, null);

        g.setColor(Color.WHITE);
        g.setFont(fontS);
        g.drawString("C", sideBarXREF - 30, 30);
        g.drawImage(coin, sideBarXREF - 137, 100, 36, 36, null);
        g.drawString(Cannon.PRICE + "", sideBarXREF - 100, 127);

        //Ninja
        g.setColor(new Color(196, 164, 132));
        g.fillRoundRect(sideBarXREF - 145, 155, 140, 140, 20, 20);
        g.drawImage(ninja, sideBarXREF - 140, 160, 130, 130, null);

        g.setColor(Color.WHITE);
        g.setFont(fontS);
        g.drawString("N", sideBarXREF - 30, 180);
        g.drawImage(coin, sideBarXREF - 137, 250, 36, 36, null);
        g.drawString(Ninja.PRICE + "", sideBarXREF - 100, 277);

        //Mage
        g.setColor(new Color(196, 164, 132));
        g.fillRoundRect(sideBarXREF - 145, 305, 140, 140, 20, 20);
        g.drawImage(mage, sideBarXREF - 140, 310, 130, 130, null);

        g.setColor(Color.WHITE);
        g.setFont(fontS);
        g.drawString("M", sideBarXREF - 30, 330);
        g.drawImage(coin, sideBarXREF - 137, 400, 36, 36, null);
        g.drawString(Mage.PRICE + "", sideBarXREF - 100, 427);

        if (this.dummy != null) {
            this.dummy.render(g);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if (Game.gameState == Game.STATE.GAME && Map.mapStatus == MapStatus.IN_PROGRESS) {
            if (!game.getHud().getUpgradeBar().isUpgradeBarOpened()) {
                if (sideBarOpened) {
                    if (mouseOver(mx, my, sideBarXREF, 0, 75, 75)) {
                        closeSideBar();
                    }
                    if (mouseOver(mx, my, sideBarXREF - 145, 5, 140, 140)) {
                        if (Map.COIN >= Cannon.PRICE) {
                            setDummy(new Dummy(handler, new Cannon(0, 0, handler)));
                            game.addListenerForDummy(getDummy());
                            closeSideBar();
                        } else {
                            AudioPlayer.getSound("nope").play();
                        }
                    }
                    if (mouseOver(mx, my, sideBarXREF - 145, 155, 140, 140)) {
                        if (Map.COIN >= Ninja.PRICE) {
                            setDummy(new Dummy(handler, new Ninja(0, 0, handler)));
                            game.addListenerForDummy(getDummy());
                            closeSideBar();
                        } else {
                            AudioPlayer.getSound("nope").play();
                        }
                    }
                    if (mouseOver(mx, my, sideBarXREF - 145, 305, 140, 140)) {
                        if (Map.COIN >= Mage.PRICE) {
                            setDummy(new Dummy(handler, new Mage(0, 0, handler)));
                            game.addListenerForDummy(getDummy());
                            closeSideBar();
                        } else {
                            AudioPlayer.getSound("nope").play();
                        }
                    }
                } else {
                    if (mouseOver(mx, my, sideBarXREF, 0, 75, 75)) {
                        opedSideBar();
                        if (getDummy() != null) {
                            game.removeListenerForDummy(getDummy());
                            setDummy(null);
                        }
                    }
                }
                if (getDummy() != null) {
                    if (getDummy().place()) {
                        switch (getDummy().getTower().getTowerName()) {
                            case "cannon" -> {
                                handler.addGameObject(new Cannon(getDummy().getX() - 38, getDummy().getY() - 38, handler));
                                Map.COIN -= Cannon.PRICE;
                            }
                            case "ninja" -> {
                                handler.addGameObject(new Ninja(getDummy().getX() - 38, getDummy().getY() - 38, handler));
                                Map.COIN -= Ninja.PRICE;
                            }
                            case "mage" -> {
                                handler.addGameObject(new Mage(getDummy().getX() - 38, getDummy().getY() - 38, handler));
                                Map.COIN -= Mage.PRICE;
                            }
                        }
                        AudioPlayer.getSound("place").play();
                        game.removeListenerForDummy(getDummy());
                        setDummy(null);
                    } else {
                        if (!(mouseOver(mx, my, 0, 0, 150, 500)) && (sideBarOpened)) {
                            AudioPlayer.getSound("nope").play();
                        }
                    }
                }
            }
        }
    }

    public Dummy getDummy() {
        return dummy;
    }

    public void setDummy(Dummy dummy) {
        this.dummy = dummy;
    }
}
