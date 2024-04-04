package Game.Monsters;

import Game.Handler;
//Slime class
public class Slime extends Monster {
    public Slime(int x, int y, Type type, Handler handler) {
        super(x, y, type, handler);
        this.speed = 2;
        this.dmg = 10;
        this.hp = 50;
        this.name = "slime";
        getAssets();
    }
}
