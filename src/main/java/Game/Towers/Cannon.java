package Game.Towers;

import Game.Handler;
import Game.Towers.Projectile.Cannonball;

import javax.swing.*;

public class Cannon extends Tower {
    private int coolDownFlag;
    public static int PRICE = 350;
    public Cannon(int x, int y, Handler handler) {
        super(x, y, handler);
        ImageIcon icon = new ImageIcon("src/main/data/towers/cannon/cannon.png");
        this.image = icon.getImage();
        this.coolDownFlag = 120;
        this.projectileCooldown = this.coolDownFlag;
        this.radius = 400;
    }
    @Override
    public void shoot(int xTarget,int yTarget){
        angle = calculateAngle(xTarget,yTarget);
        handler.addGameObject(new Cannonball(x + 19, y + 19, xTarget + 19, yTarget + 19, handler));
        projectileCooldown = coolDownFlag;
    }
}
