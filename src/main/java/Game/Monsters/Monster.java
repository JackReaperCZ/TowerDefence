package Game.Monsters;

import Game.GameObject;
import Game.Handler;
import Game.ID;
import Game.Towers.Projectile.Projectile;

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
    //The speed of the monster
    protected int speed;
    protected int progression = 0;
    protected int xToGo;
    protected int yToGo;
    protected int flag = 0;
    protected Handler handler;
    //Constructor
    public Monster(int x, int y, int speed, Handler handler) {
        super(x, y);
        this.id = ID.Monster;
        this.handler = handler;
        this.speed = speed;
        dmg = 10;
        hp = 360;
    }
    //Tick method
    @Override
    public void tick() {
        collision();
        progression += speed;
        if (x == xToGo && y == yToGo){
            flag++;
            handler.map.nextFlag(flag,this);
        }
        x += velX;
        y += velY;
    }
    //Rendering of the object
    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x,y,76,76);
    }
    //Method to handle collision with projectiles
    public void collision(){
        for (GameObject go: handler.objects) {
            if (go.getId() == ID.Projectile && getBounds().intersects(go.getBounds())){
                handler.removeGameObject(go);
                Projectile p = (Projectile) go;
                hp -= p.getDmg();
                if (hp <= 0){
                    handler.removeGameObject(this);
                }
            }
        }
    }
    //Method that will tell monster where to go
    public void goTo(int x,int y){
        this.xToGo = x;
        this.yToGo = y;
        int xi = this.x - x;
        int yi = this.y - y;
        if (yi == 0){
            setVelY(0);
        } else if(yi > 0){
            setVelY(-1 * speed);
        } else {
            setVelY(speed);
        }
        if (xi == 0){
            setVelX(0);
        } else if(xi > 0){
            setVelX(-1 * speed);
        } else {
            setVelX(speed);
        }
    }
    //Getting hitbox of the monster
    @Override
    public Ellipse2D getIntersects() {
        return null;
    }

    @Override
    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(x,y,76,76);
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

    public int getProgression() {
        return progression;
    }

    public void setProgression(int progression) {
        this.progression = progression;
    }
}