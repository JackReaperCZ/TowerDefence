package Game;

import Game.Map.Map;
import Game.Map.MapData;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents the menu interface of the game.
 */
public class Menu extends MouseAdapter {
    /**
     * BufferedImage background
     */
    private BufferedImage background;
    /**
     * Big 120f bold magic school one font
     */
    private Font fontB;
    /**
     * Small 60f bold magic school one font
     */
    private Font fontS;
    /**
     * Handler
     */
    private Handler handler;
    /**
     *  ArrayList of active maps saved in MapData objects
     */
    private ArrayList<MapData> maps;

    /**
     * Represents the currently opened menu state
     */
    private enum MENU {
        MAIN,
        MAP_SELECT
    }

    /**
     * Actual menu state
     */
    private MENU openedMenu = MENU.MAIN;

    /**
     * Constructs a Menu object.
     *
     * @param handler The handler for the game.
     */
    public Menu(Handler handler) {
        this.handler = handler;
        this.maps = getActiveMaps();
        try {
            this.background = ImageIO.read(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("menu/menu_background.png")));
            InputStream is = new BufferedInputStream(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("font/magic-school.one.ttf")));
            Font magicschool = Font.createFont(Font.TRUETYPE_FONT, is);
            this.fontB = magicschool.deriveFont(Font.BOLD, 120f);
            this.fontS = magicschool.deriveFont(Font.BOLD, 60f);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Renders the menu interface.
     *
     * @param g The Graphics object to render the menu.
     */
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
                        if ((i * 4) + y < maps.size()) {
                            //Draw map miniature
                            ImageIcon mini = new ImageIcon(maps.get((i * 4) + y).getImage());
                            g.drawImage(mini.getImage(), 20 + ((238 + 25) * y), 202 + (204 * i), 234, 175, null);
                            //Draw string to map slot
                            g.setColor(Color.WHITE);
                            g.setFont(fontS);
                            g.drawString(maps.get((i * 4) + y).getName(), 75 + ((238 + 25) * y), 365 + (204 * i));
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

    /**
     * Retrieves active maps from the map directory.
     *
     * @return An ArrayList of map data.
     */
    private ArrayList<MapData> getActiveMaps() {
        ArrayList<MapData> maps = new ArrayList<>();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("maps/maps.txt");
        if (inputStream != null) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    maps.add(new MapData("maps/" + line, line));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return maps;
    }

    /**
     * Handles mouse pressed events.
     *
     * @param e The MouseEvent representing the mouse press event.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if (Game.gameState == Game.STATE.MENU) {
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
                            if ((i * 4) + y < maps.size()) {
                                if (mouseOver(mx, my, 18 + ((238 + 25) * y), 200 + (204 * i), 238, 179)) {
                                    AudioPlayer.getMusic("menu").stop();
                                    this.handler.setMap(new Map(handler, maps.get((i * 4) + y)));
                                    this.openedMenu = MENU.MAIN;
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
}