package Game;

import java.awt.*;
//Class for HUD rendering layout
public class HUD {
    //Gold variable
    public static int GOLD = 550;
    //Gold variable
    public static int HEALTH = 100;
    public void render(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect(0,0,200,50);
        g.setColor(Color.WHITE);
        g.drawString("Gold : " + GOLD, 5, 25);
        g.drawString("Health : " + HEALTH, 100, 25);
    }
}
