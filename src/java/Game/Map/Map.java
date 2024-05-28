package Game.Map;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import Game.AudioPlayer;
import Game.GameObject;
import Game.Handler;
import Game.ID;
import Game.Map.Wave.Spawner;
import Game.Monsters.Monster;

import javax.swing.*;

/**
 * The Map class represents the game map, including the background image, path, and spawner for monsters.
 */
public class Map {
    /**
     * Gold variable
     */
    public static int COIN = 500;
    /**
     * Health variable
     */
    public static int HEALTH = 100;
    /**
     * Path
     */
    private Path path;
    /**
     * Spawner
     */
    private Spawner spawner;
    /**
     * Handler
     */
    private Handler handler;
    /**
     * Map image (background)
     */
    private Image image;
    /**
     * Actual map status
     */
    public static MapStatus mapStatus = MapStatus.IN_PROGRESS;
    /**
     * Source path
     */
    private String sourcePath;
    /**
     * Map data
     */
    private MapData mapData;


    /**
     * Constructs a new Map object.
     *
     * @param handler the handler for managing game objects
     */
    public Map(Handler handler, MapData mapData) {
        this.mapData = mapData;
        AudioPlayer.getMusic(mapData.getName()).loop();
        this.sourcePath = mapData.getUrl();
        ImageIcon icon = new ImageIcon(mapData.getImage());
        this.image = icon.getImage();
        this.path = new Path(sourcePath);
        this.handler = handler;
        this.spawner = new Spawner(this.path, handler, sourcePath);
    }

    /**
     * Updates the state of the map.
     */
    public void tick() {
        spawner.tick();
        if ((Spawner.ACTUAL_WAVE == this.handler.getMap().getSpawner().getWaves().size()) && !Spawner.SPAWN) {
            boolean monsterOnMap = false;
            for (GameObject go : this.handler.objects) {
                if (go.getId() == ID.Monster) {
                    monsterOnMap = true;
                    break;
                }
            }
            if (!monsterOnMap && Map.mapStatus != MapStatus.WON) {
                Map.mapStatus = MapStatus.WON;
                AudioPlayer.getSound("gameWin").play();
            }
        }
    }

    /**
     * Renders the map.
     *
     * @param g the graphics context
     */
    public void render(Graphics g) {
        g.drawImage(image, 0, 0, null);
    }

    /**
     * Moves the monster object to the next flag on the path.
     *
     * @param i       the index of the next flag
     * @param monster the monster object to move
     */
    public void nextFlag(int i, Monster monster) {
        if (i == path.getFlags().size() - 1) {
            Map.HEALTH -= monster.getDmg();
            handler.removeGameObject(monster);
            //Check if we didn't lose
            if (HEALTH <= 0 && mapStatus != MapStatus.LOST) {
                mapStatus = MapStatus.LOST;
                AudioPlayer.getSound("gameOver").play();
            }
        } else {
            monster.goTo(path.getFlags().get(i + 1).getX(), path.getFlags().get(i + 1).getY());
        }
    }

    /**
     * Gets the hitboxes of the path.
     *
     * @return an array list of rectangle hitboxes representing the path segments
     */
    public ArrayList<Rectangle2D> getBounds() {
        ArrayList<Rectangle2D> ar = new ArrayList<>();
        for (Flag f : path.getFlags()) {
            if (f.getNextFlag() != null) {

                int xi = f.getX() - f.getNextFlag().getX();
                int yi = f.getY() - f.getNextFlag().getY();

                int x = 0;
                int y = 0;
                if (xi < 0 || yi < 0) {
                    x = f.getX();
                    y = f.getY();
                } else {
                    x = f.getNextFlag().getX();
                    y = f.getNextFlag().getY();
                }
                if (yi == 0) {
                    ar.add(new Rectangle2D.Double(x, y, Math.abs(xi) + 80, 80));
                } else {
                    ar.add(new Rectangle2D.Double(x, y, 80, Math.abs(yi) + 80));
                }
            }
        }
        return ar;
    }
    //Getters and setters

    /**
     * Gets the spawner for monsters on this map.
     *
     * @return the spawner object
     */
    public Spawner getSpawner() {
        return spawner;
    }

    /**
     * Gets the source path of the map resources.
     *
     * @return the source path
     */

    public String getSourcePath() {
        return sourcePath;
    }

    /**
     * Gets the map data.
     *
     * @return the map data
     */
    public MapData getMapData() {
        return mapData;
    }
}