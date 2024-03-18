package Game.Monsters;

import Game.Game;
import Game.GameObject;
import Game.ID;

import java.awt.*;
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
    //Constructor
    public Monster(int x, int y) {
        super(x, y);
        this.id = ID.Monster;
        velX = 5;
        velY = 5;
    }

    @Override
    public void tick() {
        //Test movement
        x += velX;
        y += velY;
    }
    //Rendering of the object
    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(x,y,32,32);

        if (y <= 0) velY = 5;
        if (y >= Game.HEIGHT - 64) velY =-5;
        if (x <= 0) velX = 5;
        if (x >= Game.WIDTH - (32+16)) velX = -5;
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