package Game.Towers.Projectile;

import Game.Handler;

public class Cannonball extends Projectile{
    public Cannonball(int x, int y, int x2, int y2, int dmg,Handler handler) {
        super(x, y, dmg, handler);
        this.speed = 25;
        this.damageType = DamageType.NORMAL;
        this.name = "cannonball";
        velCalculate(x2, y2, speed);
        getAssets();
    }
}
