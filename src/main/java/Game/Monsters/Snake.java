package Game.Monsters;

import Game.Handler;

public class Snake extends Monster{
    public Snake(int x, int y, Type type, Handler handler) {
        super(x, y, type, handler);
        this.speed = 10;
        this.dmg = 20;
        this.gold = 60;
        this.hp = 100;
        this.name = "snake";
        getAssets();
    }
}
