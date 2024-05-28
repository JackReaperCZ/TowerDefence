package Game.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static Game.AudioPlayer.musicMap;

/**
 * Represents data associated with a map in the game.
 */
public class MapData {
    private String name;
    private BufferedImage image;
    private Music music;
    private String url;

    /**
     * Constructs a MapData object.
     *
     * @param url  the relative URL of the map resources directory.
     * @param name the name of the map.
     * @throws RuntimeException if there is an error loading the map image or music.
     */
    public MapData(String url, String name) {
        this.name = name;
        this.url = url;
        try {
            this.image = ImageIO.read(Objects.requireNonNull(this.getClass().getClassLoader().getResourceAsStream(url + "/map.png")));
            this.music = new Music(url + "/music.ogg");
            musicMap.put(name, music);
        } catch (IOException | SlickException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets the image associated with the map.
     *
     * @return the image of the map.
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Gets the name of the map.
     *
     * @return the name of the map.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the URL of the map resources directory.
     *
     * @return the URL of the map resources directory.
     */
    public String getUrl() {
        return url;
    }
}
