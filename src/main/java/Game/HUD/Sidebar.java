package Game.HUD;

import Game.Game;
import Game.GameObject;
import Game.Handler;
import Game.Towers.Cannon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.*;

public class Sidebar extends UI {
    private boolean sideBarOpened = false;
    private int sideBarXREF = 0;
    private int velX = 5;
    private Handler handler;
    private Image coin;
    private Image cannon;
    private Game game;
    private Dummy dummy;

    public Sidebar(Handler handler, Game game) {
        this.handler = handler;
        this.game = game;
        try {
            InputStream is = new BufferedInputStream(new FileInputStream("src/main/data/font/ComicSansMS3.ttf"));
            Font comicSans = Font.createFont(Font.TRUETYPE_FONT, is);
            this.fontS = comicSans.deriveFont(Font.BOLD, 24f);
            this.fontB = comicSans.deriveFont(120f);

            ImageIcon iconC = new ImageIcon("src/main/data/hud/coin.png");
            this.coin = iconC.getImage();
            ImageIcon icon = new ImageIcon("src/main/data/towers/cannon/cannon.png");
            this.cannon = icon.getImage();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
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
        g.fillRect(sideBarXREF + 0, 0, 65, 75);
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

        if (this.dummy != null) {
            this.dummy.render(g);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();

        if (sideBarOpened) {
            if (mouseOver(mx, my, sideBarXREF + 0, 0, 75, 75)) {
                closeSideBar();
            }
            if (mouseOver(mx, my, sideBarXREF - 145, 5, 140, 140)) {
                if (HUD.COIN >= Cannon.PRICE) {
                    setDummy(new Dummy(handler, new Cannon(0, 0, handler)));
                    game.addListenerForDummy(getDummy());
                    closeSideBar();
                } else {
                    //Play sound effect
                }
            }
        } else {
            if (mouseOver(mx, my, sideBarXREF + 0, 0, 75, 75)) {
                opedSideBar();
                if (getDummy() != null) {
                    game.removeListenerForDummy(getDummy());
                    setDummy(null);
                }
            }
        }
        if (getDummy() != null) {
            if (getDummy().place()) {
                if (getDummy().getTower() instanceof Cannon) {
                    handler.addGameObject(new Cannon(getDummy().getX() - 38, getDummy().getY() - 38, handler));
                }
                HUD.COIN -= Cannon.PRICE;
                game.removeListenerForDummy(getDummy());
                setDummy(null);
            } else {
                //Play sound effect
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
