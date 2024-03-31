package Game.Map.Wave;

import Game.Handler;
import Game.Map.Path;
import Game.Monsters.Monster;

public class Spawner {
    private int counter = 0;
    private Path path;
    private Handler handler;

    public Spawner(Path path, Handler handler, String sourcePath) {
        this.handler = handler;
        this.path = path;
    }
    public void tick(){
        if (counter == 240){
            Monster m = new Monster(path.getFlag(0).getX(),path.getFlag(0).getY(),2,handler);
            m.goTo(path.getFlag(1).getX(),path.getFlag(1).getY());
            handler.addGameObject(m);
            counter = 0;
        }
        counter++;
    }
}
