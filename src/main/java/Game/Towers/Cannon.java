package Game.Towers;

import Game.Handler;
import Game.Map.Map;
import Game.Map.MapStatus;
import Game.Towers.Projectile.Cannonball;

public class Cannon extends Tower {
    public static int PRICE = 350;
    public Cannon(int x, int y, Handler handler) {
        super(x, y, handler);
        this.towerName = "cannon";
        this.dmg = 100;
        this.coolDownFlag = 120;
        this.projectileCooldown = this.coolDownFlag;
        this.radius = 400;
        this.price = 350;
        getTowerAssets();
    }
    //Shooting at the selected target
    @Override
    public void shoot(int xTarget,int yTarget){
        if (Map.mapStatus == MapStatus.IN_PROGRESS) {
            angle = calculateAngle(xTarget, yTarget);
            handler.addGameObject(new Cannonball(x + 19, y + 19, xTarget + 19, yTarget + 19, dmg, handler));
            projectileCooldown = coolDownFlag;
        }
    }
}