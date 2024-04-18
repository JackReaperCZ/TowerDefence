package Game.Monsters;

import Game.Handler;

public class Spider extends Monster {
    public Spider(int x, int y, Type type, Handler handler) {
        super(x, y, type, handler);
        this.speed = 10;
        this.dmg = 10;
        this.gold = 100;
        this.hp = 50;
        this.name = "spider";
        getAssets();
    }
}