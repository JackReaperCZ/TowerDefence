package Game.Map;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import Game.GameObject;
import Game.Handler;
import Game.ID;
import Game.Map.Wave.Spawner;
import Game.Monsters.Monster;

import javax.swing.*;

public class Map {
    //Gold variable
    public static int COIN = 10000;
    //Gold variable
    public static int HEALTH = 100;
    //Path
    private Path path;
    //Spawner
    private Spawner spawner;
    //Handler
    private Handler handler;
    //Map image (background)
    private Image image;
    //Actual map status
    public static MapStatus mapStatus = MapStatus.IN_PROGRESS;
    private String sourcePath;

    //Constructor
    public Map(Handler handler,String sourcePath) {
        this.sourcePath = sourcePath;
        ImageIcon icon = new ImageIcon(sourcePath + "map.png");
        this.image = icon.getImage();
        this.path = new Path(sourcePath);
        this.handler = handler;
        this.spawner = new Spawner(this.path,handler,sourcePath);
    }
    //Tick method
    public void tick(){
        spawner.tick();
        if ((Spawner.ACTUAL_WAVE == this.handler.getMap().getSpawner().getWaves().size()) && !Spawner.SPAWN){
            boolean monsterOnMap = false;
            for (GameObject go : this.handler.objects){
                if (go.getId() == ID.Monster){
                    monsterOnMap = true;
                    break;
                }
            }
            if (!monsterOnMap){
                Map.mapStatus = MapStatus.WON;
            }
        }
    }

    //Rendering of the map
    public void render(Graphics g) {
        g.drawImage(image,0,0,null);
    }
    //Sends the monster object to the next flag
    public void nextFlag(int i, Monster monster){
        if (i == path.getFlags().size()-1){
            Map.HEALTH -= monster.getDmg();
            handler.removeGameObject(monster);
            //Check if we didn't lose
            if (HEALTH <= 0 && mapStatus != MapStatus.LOST){
                mapStatus = MapStatus.LOST;
            }
        } else {
            monster.goTo(path.getFlags().get(i+1).getX(),path.getFlags().get(i+1).getY());
        }
    }
    //Hitbox of the path
    public ArrayList<Rectangle2D> getBounds(){
        ArrayList<Rectangle2D> ar = new ArrayList<>();
        for (Flag f : path.getFlags()){
            if (f.getNextFlag()!= null){

                int xi = f.getX() - f.getNextFlag().getX();
                int yi = f.getY() - f.getNextFlag().getY();

                int x = 0;
                int y = 0;
                if (xi < 0 || yi < 0){
                        x = f.getX();
                        y = f.getY();
                } else {
                        x = f.getNextFlag().getX();
                        y = f.getNextFlag().getY();
                }
                if (yi == 0){
                        ar.add(new Rectangle2D.Double(x,y, Math.abs(xi) + 76,76));
                    } else {
                        ar.add(new Rectangle2D.Double(x,y, 76,Math.abs(yi) + 76));
                }
            }
        }
        return ar;
    }
    //Getters and setters
    public Spawner getSpawner() {
        return spawner;
    }

    public void setSpawner(Spawner spawner) {
        this.spawner = spawner;
    }

    public String getSourcePath() {
        return sourcePath;
    }
}