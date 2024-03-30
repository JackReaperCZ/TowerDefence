package Game;

import java.awt.Graphics;
import java.util.LinkedList;
import Game.Map.Map;


public class Handler {
    //Linked list for all game objects
    public LinkedList<GameObject> objects = new LinkedList<>();
    //Linked list of game object that will be added the next tick
    public LinkedList<GameObject> toAdd = new LinkedList<>();
    //Linked list of game object that will be removed the next tick
    public LinkedList<GameObject> toRemove = new LinkedList<>();
    //Actual map to render
    public Map map = new Map(this, "src/main/data/maps/plains/");
    //Flag for the ids
    private int idFlag = 0;

    //Ticks all game objects
    public void tick(){
        map.tick();
        for (GameObject go : objects){
            go.tick();
        }
        removeFromList();
        addToList();
    }
    //Renders all game objects
    public void render(Graphics g){
        map.render(g);
        for (GameObject go : objects){
            go.render(g);
        }
    }
    //Method to add game object to the list
    public void addToList(){
        objects.addAll(toAdd);
        toAdd.clear();
    }
    //Method to remove game object from the list
    public void removeFromList(){
        objects.removeAll(toRemove);
        toRemove.clear();
    }
    //Adds game object
    public void addGameObject(GameObject gameObject){
        gameObject.setIdentification(this.idFlag);
        idFlag++;
        this.toAdd.add(gameObject);
    }
    //Removes game object
    public void removeGameObject(GameObject gameObject){
        this.toRemove.add(gameObject);
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Map getMap() {
        return map;
    }
}