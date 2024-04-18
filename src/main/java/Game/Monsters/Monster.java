package Game.Monsters;

import Game.GameObject;
import Game.Handler;
import Game.ID;
import Game.Map.Map;
import Game.Towers.Projectile.DamageType;
import Game.Towers.Projectile.Projectile;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

//Monster class
public abstract class Monster extends GameObject implements Cloneable {
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
    //Name of the monster
    protected String name;
    //Progression on path in px
    protected int progression = 0;
    //Go to x
    protected int xToGo;
    //Go to y
    protected int yToGo;
    //Flag id on the path
    protected int flag = 0;
    //Handler
    protected Handler handler;
    //Sprite of the monster
    protected Image image;
    //Control variable to see if the image should render flipped
    protected boolean flipped = false;

    //Constructor
    public Monster(int x, int y, Type type, Handler handler) {
        super(x, y);
        this.type = type;
        this.id = ID.Monster;
        this.handler = handler;
    }

    //Tick method
    @Override
    public void tick() {
        collision();
        progression += speed;
        if (x == xToGo && y == yToGo) {
            flag++;
            handler.map.nextFlag(flag, this);
        }
        x += velX;
        y += velY;
    }

    //Rendering of the object
    @Override
    public void render(Graphics g) {
        //Rotation
        if (velX < 0 && !flipped) {
            flipped = true;
        }
        if (velX > 0 && flipped) {
            flipped = false;
        }

        //Draw
        if (flipped) {
            g.drawImage(image, x + 76, y, -76, 76, null);
        } else {
            g.drawImage(image, x, y, null);
        }
    }

    //Load assets for the monster
    public void getAssets() {
        //Main png
        ImageIcon icon = new ImageIcon("src/main/data/monsters/" + this.name + "/" + this.name + ".png");
        this.image = icon.getImage();
    }

    //Creates a clone of the object
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    //Method to handle collision with projectiles
    public void collision() {
        for (GameObject go : handler.objects) {
            if (go.getId() == ID.Projectile && getBounds().intersects(go.getBounds())) {
                if (!(handler.getToRemove().contains(go))) {
                    handler.removeGameObject(go);
                    Projectile p = (Projectile) go;
                    switch (type){
                        case NORMAL -> {
                            hp -= p.getDmg();
                        }
                        case ARMORED -> {
                            hp -= (int) (p.getDmg()/2);
                        }
                        case MAGICRESISTANT -> {
                            if (p.getDamageType() != DamageType.MAGICAL){
                                hp -= p.getDmg();
                            }
                        }
                    }
                    if (hp <= 0) {
                        handler.removeGameObject(this);
                        Map.COIN += this.gold;
                    }
                }
            }
        }
    }

    //Method that will tell monster where to go
    public void goTo(int x, int y) {
        this.xToGo = x;
        this.yToGo = y;
        int xi = this.x - x;
        int yi = this.y - y;
        if (yi == 0) {
            setVelY(0);
        } else if (yi > 0) {
            setVelY(-1 * speed);
        } else {
            setVelY(speed);
        }
        if (xi == 0) {
            setVelX(0);
        } else if (xi > 0) {
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
        return new Rectangle2D.Double(x, y, 76, 76);
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