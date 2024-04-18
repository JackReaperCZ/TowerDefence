package Game.Towers;

import Game.AudioPlayer;
import Game.Handler;
import Game.Map.Map;
import Game.Map.MapStatus;
import Game.Towers.Projectile.Fireball;

public class Mage extends Tower {
    public static int PRICE = 700;
    public Mage(int x, int y, Handler handler) {
        super(x, y, handler);
        this.towerName = "mage";
        this.dmg = 250;
        this.coolDownFlag = 50;
        this.projectileCooldown = this.coolDownFlag;
        this.radius = 300;
        this.price = 700;
        getTowerAssets();
    }

    @Override
    public void shoot(int xTarget, int yTarget) {
        if (Map.mapStatus == MapStatus.IN_PROGRESS) {
            angle = calculateAngle(xTarget, yTarget);
            handler.addGameObject(new Fireball(x + 19, y + 19, xTarget + 19, yTarget + 19, dmg, handler));
            AudioPlayer.soundMap.get("mage").play();
            projectileCooldown = coolDownFlag;
        }
    }
}
