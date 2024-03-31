package Game.HUD;

import Game.Game;
import Game.Handler;

import java.awt.*;
import java.awt.event.MouseEvent;

public class PauseMenu extends UI {
    private Handler handler;

    public PauseMenu(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void render(Graphics g) {
        if (Game.gameState == Game.STATE.PAUSE) {
            int x = Game.WIDTH;
            int y = Game.HEIGHT;

            g.setColor(new Color(210, 210, 210, 131));
            g.fillRect(0, 0, x, y);

            g.setColor(new Color(150, 105, 25));
            g.fillRoundRect((x / 2) - 250, (y / 2) - 150, 500, 300, 20, 20);

            g.setColor(new Color(196, 164, 132));
            g.fillRoundRect((x / 2) - 240, (y / 2) - 140, 480, 280, 20, 20);

            g.setColor(Color.WHITE);
            g.setFont(fontS);
            g.drawString("PAUSE", (x / 2) - 43, (y / 2) - 100);

            //Render buttons
            for (int i = 0; i <= 1; i++) {
                g.setColor(new Color(150, 105, 25));
                g.fillRoundRect((x / 2) - 100, (y / 2) - 65 + (i * 85), 200, 75, 20, 20);

                g.setColor(new Color(196, 164, 132));
                g.fillRoundRect((x / 2) - 95, (y / 2) - 60 + (i * 85), 190, 65, 20, 20);

                g.setColor(Color.WHITE);
                switch (i){
                    case 0 -> {
                        g.drawString("RESUME",(x / 2) - 60, (y / 2) - 20);
                    }
                    case 1 -> {
                        g.drawString("MAIN MENU",(x / 2) - 80, (y / 2) + 65);
                    }
                }
            }
        }
    }

    @Override
    public void tick() {}

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if (Game.gameState == Game.STATE.PAUSE){
            if (mouseOver(mx,my,(Game.WIDTH / 2) - 100, (Game.HEIGHT / 2) - 65, 200, 75)){
                Game.gameState = Game.STATE.GAME;
            }
            if(mouseOver(mx,my,(Game.WIDTH / 2) - 100, (Game.HEIGHT / 2) + 20, 200, 75)){
                handler.clearHandler();
                Game.gameState = Game.STATE.MENU;
            }
        }
    }
}
