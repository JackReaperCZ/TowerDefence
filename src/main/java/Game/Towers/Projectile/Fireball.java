package Game.Towers.Projectile;

import Game.Handler;

public class Fireball extends Projectile{
    public Fireball(int x, int y, int x2, int y2, int dmg, Handler handler) {
        super(x, y, dmg, handler);
        this.speed = 25;
        this.damageType = DamageType.MAGICAL;
        this.name = "fireball";
        velCalculate(x2, y2, speed);
        getAssets();
    }
}
