import java.awt.*;
import java.util.LinkedList;


public class Handler {
    //Linked list for all game objects
    LinkedList<GameObject> objects = new LinkedList<GameObject>();
    //Ticks all game objects
    public void tick(){
        for (GameObject go : objects){
            go.tick();
        }
    }
    //Renders all game objects
    public void render(Graphics g){
        for (GameObject go : objects){
            go.render(g);
        }
    }
    //Adds game object
    public void addGameObject(GameObject gameObject){
        this.objects.add(gameObject);
    }
    //Removes game object
    public void removeGameObject(GameObject gameObject){
        this.objects.remove(gameObject);
    }
}
