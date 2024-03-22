package Game.Monsters;

import Game.GameObject;
import Game.Handler;
import Game.ID;
import Game.Game;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

//Monster class
public class Monster extends GameObject {
    //Type of the monster
    protected Type type;
    //Damage done by monster when it gets thought
    protected int dmg;
    //Health of the monster
    protected int hp;
    //The gold player gets when the monster is killed
    protected int gold;
    protected Handler handler;
    //Constructor
    public Monster(int x, int y, Handler handler) {
        super(x, y);
        this.id = ID.Monster;
        this.handler = handler;
        velX = 2;
        velY = 2;
    }
    //Tick method
    @Override
    public void tick() {
        collision();
        //Test movement
        x += velX;
        y += velY;
    }
    //Rendering of the object
    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x,y,32,32);

        if (y <= 0) velY = 5;
        if (y >= Game.HEIGHT - 64) velY =-5;
        if (x <= 0) velX = 5;
        if (x >= Game.WIDTH - (32+16)) velX = -5;
    }
    //Methot to handle collision with projectiles
    public void collision(){
        for (GameObject go: handler.objects) {
            if (go.getId() == ID.Projectile && getBounds().intersects(go.getBounds())){
                System.out.println(true);
            }
        }
    }
    //Getting hitbox of the monster
    @Override
    public Ellipse2D getIntersects() {
        return null;
    }

    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(x,y,32,32);
    }

    //Getters and setters
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}