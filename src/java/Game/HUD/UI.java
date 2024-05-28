package Game.HUD;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Objects;

/**
 * The UI class serves as a base class for user interface elements in the game.
 * It provides methods for rendering and updating UI components.
 */
public abstract class UI extends MouseAdapter {
    /**
     * Big 120f bold Comic Sans MS3 font
     */
    protected Font fontB;
    /**
     * Small 60f bold Comic Sans MS3 font
     */
    protected Font fontS;

    /**
     * Constructs a UI object and initializes the fonts.
     */
    public UI() {
        try {
            InputStream is = new BufferedInputStream(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream("font/ComicSansMS3.ttf")));
            Font comicSans = Font.createFont(Font.TRUETYPE_FONT, is);
            this.fontS = comicSans.deriveFont(Font.BOLD, 24f);
            this.fontB = comicSans.deriveFont(120f);
        } catch (Exception ignored) {
        }
    }

    /**
     * Renders the UI component.
     *
     * @param g The Graphics context to render on.
     */
    public abstract void render(Graphics g);

    /**
     * Updates the UI component.
     */
    public abstract void tick();

    /**
     * Checks if the mouse cursor is over a specified area.
     *
     * @param mx The x-coordinate of the mouse cursor.
     * @param my The y-coordinate of the mouse cursor.
     * @param x  The x-coordinate of the area.
     * @param y  The y-coordinate of the area.
     * @param w  The width of the area.
     * @param h  The height of the area.
     * @return True if the mouse cursor is over the area, false otherwise.
     */
    public boolean mouseOver(int mx, int my, int x, int y, int w, int h) {
        if (mx > x && mx < x + w) {
            return my > y && my < y + h;
        }
        return false;
    }
}
