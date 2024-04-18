package Game.Monsters;

import Game.Handler;

public class Worm extends Monster {
    public Worm(int x, int y, Type type, Handler handler) {
        super(x, y, type, handler);
        this.speed = 4;
        this.dmg = 15;
        this.gold = 50;
        this.hp = 250;
        this.name = "worm";
        getAssets();
    }
}
