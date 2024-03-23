package Game.Map;

import java.awt.*;

import Game.Game;

public class Map {
    public Path path;

    public Map() {
        //Create a test path
        Path path1 = new Path();
        path1.addFlag(new Flag(500,0));
        path1.addFlag(new Flag(500,300));
        path1.addFlag(new Flag(250,300));
        path1.addFlag(new Flag(250, 550));
        path1.addFlag(new Flag(500,550));
        path1.addFlag(new Flag(500,Game.HEIGHT));
        path1.buildPath();
        this.path = path1;
    }

    //Rendering of the map
    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(0,0,Game.WIDTH,Game.HEIGHT);
        renderPath(g);
    }
    //Rendering of the path logic
    private void renderPath(Graphics g){
        g.setColor(Color.ORANGE);
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
                        g.fillRect(x,y, Math.abs(xi) + 32,32);
                    } else {
                        g.fillRect(x,y, 32,Math.abs(yi) + 32);
                }
            }
        }
    }
}
