package Game;

import java.awt.Graphics;
import java.util.LinkedList;

import Game.Map.Map;
import Game.Map.Wave.Spawner;


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

    //Ticks all game objects
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

    //Renders all game objects
    public void render(Graphics g) {
        if (map != null) {
            map.render(g);
            for (GameObject go : objects) {
                go.render(g);
            }
        }
    }
    //Clears handler and setts all static variables to the default value
    public void clearHandler(){
        this.map = null;
        Map.COIN = 10000;
        Map.HEALTH = 100;
        Spawner.SPAWN = false;
        Spawner.WAVE_COUNTER = 0;
        Spawner.ACTUAL_WAVE = 0;
        Spawner.WAVE_TIMER = 0;
        objects.clear();
        toRemove.clear();
        toAdd.clear();
    }

    //Method to add game object to the list
    public void addToList() {
        objects.addAll(toAdd);
        toAdd.clear();
    }

    //Method to remove game object from the list
    public void removeFromList() {
        objects.removeAll(toRemove);
        toRemove.clear();
    }

    //Adds game object
    public void addGameObject(GameObject gameObject) {
        gameObject.setIdentification(this.idFlag);
        idFlag++;
        this.toAdd.add(gameObject);
    }

    //Removes game object
    public void removeGameObject(GameObject gameObject) {
        this.toRemove.add(gameObject);
    }
    //Getters and setters
    public void setMap(Map map) {
        this.map = map;
    }

    public Map getMap() {
        return map;
    }
}