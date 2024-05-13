package Game;

import java.awt.Graphics;
import java.util.LinkedList;

import Game.Map.Map;
import Game.Map.MapStatus;
import Game.Map.Wave.Spawner;

/**
 * The Handler class manages game objects and updates their state.
 */
public class Handler {
    //Linked list for all game objects
    public LinkedList<GameObject> objects = new LinkedList<>();
    //Linked list of game object that will be added the next tick
    public LinkedList<GameObject> toAdd = new LinkedList<>();
    //Linked list of game object that will be removed the next tick
    public LinkedList<GameObject> toRemove = new LinkedList<>();
    //Actual map to render
    public Map map;
    //Flag for the ids
    private int idFlag = 0;

    /**
     * Ticks all game objects.
     */
    public void tick() {
        if (map != null) {
            map.tick();
            for (GameObject go : objects) {
                go.tick();
            }
            removeFromList();
            addToList();
        }
    }

    /**
     * Renders all game objects.
     *
     * @param g The Graphics object to render the game objects.
     */
    public void render(Graphics g) {
        if (map != null) {
            map.render(g);
            for (GameObject go : objects) {
                go.render(g);
            }
        }
    }

    /**
     * Clears the handler and sets all static variables to their default values.
     */
    public void clearHandler() {
        this.map = null;
        Map.COIN = 10000;
        Map.HEALTH = 100;
        Map.mapStatus = MapStatus.IN_PROGRESS;
        Spawner.SPAWN = false;
        Spawner.WAVE_COUNTER = 0;
        Spawner.ACTUAL_WAVE = 0;
        Spawner.WAVE_TIMER = 0;
        objects.clear();
        toRemove.clear();
        toAdd.clear();
    }

    /**
     * Adds game objects to the list.
     */
    public void addToList() {
        objects.addAll(toAdd);
        toAdd.clear();
    }

    /**
     * Removes game objects from the list.
     */
    public void removeFromList() {
        objects.removeAll(toRemove);
        toRemove.clear();
    }

    /**
     * Adds a game object to the handler.
     *
     * @param gameObject The game object to add.
     */
    public void addGameObject(GameObject gameObject) {
        gameObject.setIdentification(this.idFlag);
        idFlag++;
        this.toAdd.add(gameObject);
    }

    /**
     * Removes a game object from the handler.
     *
     * @param gameObject The game object to remove.
     */
    public void removeGameObject(GameObject gameObject) {
        this.toRemove.add(gameObject);
    }

    // Getters and setters

    /**
     * Sets the map for the game.
     *
     * @param map The map to set.
     */
    public void setMap(Map map) {
        this.map = map;
    }

    /**
     * Retrieves the current map.
     *
     * @return The current map.
     */
    public Map getMap() {
        return map;
    }

    /**
     * Retrieves the list of objects to be removed.
     *
     * @return The list of objects to be removed.
     */
    public LinkedList<GameObject> getToRemove() {
        return toRemove;
    }
}