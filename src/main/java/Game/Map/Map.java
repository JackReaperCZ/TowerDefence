package Game.Map;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import Game.Handler;
import Game.Game;
import Game.Monsters.Monster;

import javax.swing.*;

public class Map {
    private Path path;
    private Spawner spawner;
    private Handler handler;

    public Map(Handler handler) {
        //Create a test path
        Path path1 = new Path();
        path1.addFlag(new Flag(-76,620));
        path1.addFlag(new Flag(920,620));
        path1.addFlag(new Flag(920,426));
        path1.addFlag(new Flag(190, 426));
        path1.addFlag(new Flag(190,50));
        path1.addFlag(new Flag(Game.WIDTH + 76,50));
        path1.buildPath();
        this.path = path1;
        this.handler = handler;
        this.spawner = new Spawner(this.path,handler);
    }
    //Tick method
    public void tick(){
        spawner.tick();
    }

    //Rendering of the map
    public void render(Graphics g) {
        ImageIcon icon = new ImageIcon("src/main/data/maps/plains/map.png");
        Image image = icon.getImage();
        g.drawImage(image,0,0,null);
    }
    //Sends the monster object to the next flag
    public void nextFlag(int i, Monster monster){
        if (i == path.getFlags().size()-1){
            handler.removeGameObject(monster);
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
}