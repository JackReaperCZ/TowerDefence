package Game.Towers;

import Game.AudioPlayer;
import Game.Handler;
import Game.Map.Map;
import Game.Map.MapStatus;
import Game.Towers.Projectile.Shuriken;

public class Ninja extends Tower {
    public static int PRICE = 450;
    public Ninja(int x, int y, Handler handler) {
        super(x, y, handler);
        this.towerName = "ninja";
        this.dmg = 45;
        this.coolDownFlag = 20;
        this.projectileCooldown = this.coolDownFlag;
        this.radius = 350;
        this.price = 450;
        getTowerAssets();
    }

    @Override
    public void shoot(int xTarget, int yTarget) {
        if (Map.mapStatus == MapStatus.IN_PROGRESS) {
            angle = calculateAngle(xTarget, yTarget);
            handler.addGameObject(new Shuriken(x + 19, y + 19, xTarget + 19, yTarget + 19, dmg, handler));
            AudioPlayer.getSound("shot").play();
            projectileCooldown = coolDownFlag;
        }
    }
}
