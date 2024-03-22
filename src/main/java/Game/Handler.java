package Game;

import java.awt.Graphics;
import java.util.LinkedList;


public class Handler {
    //Linked list for all game objects
    public LinkedList<GameObject> objects = new LinkedList<>();
    //Linked list of game object that will be added the next tick
    public LinkedList<GameObject> toAdd = new LinkedList<>();

    //Ticks all game objects
    public void tick(){
        for (GameObject go : objects){
            go.tick();
        }
        addToList();
    }
    //Renders all game objects
    public void render(Graphics g){
        for (GameObject go : objects){
            go.render(g);
        }
    }
    //Method to add game object to the list
    public void addToList(){
        objects.addAll(toAdd);
        toAdd.clear();
    }
    //Adds game object
    public void addGameObject(GameObject gameObject){
        this.toAdd.add(gameObject);
    }
    //Removes game object
    public void removeGameObject(GameObject gameObject){
        this.objects.remove(gameObject);
    }
}