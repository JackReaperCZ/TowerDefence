package Game;

import Game.Map.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class Menu extends MouseAdapter {
    private Image background;
    private Font fontB;
    private Font fontS;
    private Game game;
    private Handler handler;
    private File[] maps;

    private enum MENU {
        MAIN,
        MAP_SELECT
    }

    private MENU openedMenu = MENU.MAIN;

    public Menu(Game game, Handler handler) {
        this.game = game;
        this.handler = handler;
        this.maps = getActiveMaps();
        ImageIcon icon = new ImageIcon("src/main/data/menu/menu_background.png");
        this.background = icon.getImage();
        try {
            InputStream is = new BufferedInputStream(new FileInputStream("src/main/data/font/magic-school.one.ttf"));
            Font magicschool = Font.createFont(Font.TRUETYPE_FONT, is);
            this.fontB = magicschool.deriveFont(Font.BOLD, 120f);
            this.fontS = magicschool.deriveFont(Font.BOLD, 60f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
    }
    //Render method
    public void render(Graphics g) {
        g.drawImage(background, 0, 0, null);
        switch (openedMenu) {
            //Main menu
            case MAIN -> {
                g.setColor(Color.WHITE);
                g.setFont(fontB);
                g.drawString("MENU", 75, 150);

                g.setFont(fontS);
                //Play button
                g.drawRect(70, 300, 300, 100);
                g.drawString("Play", 175, 360);

                //Exit button
                g.drawRect(70, 425, 300, 100);
                g.drawString("Exit", 175, 485);
            }
            //Map select (play)
            case MAP_SELECT -> {
                g.setColor(Color.WHITE);
                g.setFont(fontB);
                g.drawString("MAP SELECT", 225, 150);

                for (int i = 0; i <= 1; i++) {
                    for (int y = 0; y <= 3; y++) {
                        g.setColor(Color.GRAY);
                        g.fillRect(18 + ((238 + 25) * y), 200 + (204 * i), 238, 179);
                        if ((i * 4) + y < maps.length) {
                            //Draw map miniature
                            ImageIcon mini = new ImageIcon(maps[(i * 4) + y].getAbsolutePath() + "/map.png");
                            g.drawImage(mini.getImage(), 20 + ((238 + 25) * y), 202 + (204 * i), 234, 175, null);
                            //Draw string to map slot
                            g.setColor(Color.WHITE);
                            g.setFont(fontS);
                            g.drawString(maps[(i * 4) + y].getName(), 75 + ((238 + 25) * y), 365 + (204 * i));
                        } else {
                            //Draw string to empty slots
                            g.setColor(Color.WHITE);
                            g.setFont(fontS);
                            g.drawString("EMPTY", 75 + ((238 + 25) * y), 365 + (204 * i));
                        }
                    }
                }
                //Back button
                g.setColor(Color.WHITE);
                g.drawRect(18, 630, 125, 125);
                g.setFont(fontS);
                g.drawString("<", 35, 710);
            }
        }
    }

    //Check if we are hovering over button
    private boolean mouseOver(int mx, int my, int x, int y, int w, int h) {
        if (mx > x && mx < x + w) {
            if (my > y && my < y + h) {
                return true;
            }
        }
        return false;
    }
    //Gets all maps from map directory
    private File[] getActiveMaps() {
        try {
            File directory = new File("src/main/data/maps");
            File[] folders = directory.listFiles(File::isDirectory);
            if (folders.length == 0) {
                throw new Exception("No maps found.");
            } else if (folders.length >= 8) {
                throw new Exception("Not enough map slots.");
            } else {
                return folders;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //Catch and react to mouse pressed event
    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        switch (openedMenu) {
            case MAIN -> {
                //Play button
                if (mouseOver(mx, my, 70, 300, 300, 100)) {
                    this.openedMenu = MENU.MAP_SELECT;
                }
                //Exit button
                if (mouseOver(mx, my, 70, 425, 300, 100)) {
                    System.exit(0);
                }
            }
            case MAP_SELECT -> {
                //Map slots
                for (int i = 0; i <= 1; i++) {
                    for (int y = 0; y <= 3; y++) {
                        if ((i * 4) + y < maps.length) {
                            if (mouseOver(mx, my, 18 + ((238 + 25) * y), 200 + (204 * i), 238, 179)) {
                                this.handler.setMap(new Map(handler, maps[(i * 4) + y].getAbsolutePath() + "/"));
                                game.removeMouseListener(this);
                                Game.gameState = Game.STATE.GAME;
                            }
                        }
                    }
                }
                //Back button
                if (mouseOver(mx, my, 18, 630, 125, 125)) {
                    this.openedMenu = MENU.MAIN;
                }
            }
        }
    }
}