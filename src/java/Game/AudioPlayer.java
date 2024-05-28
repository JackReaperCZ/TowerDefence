package Game;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import java.util.HashMap;
import java.util.Map;

/**
 * The AudioPlayer class manages loading and accessing audio files.
 */
public class AudioPlayer {
    /**
     * Map to store sound effects
     */
    public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
    /**
     * Map to store music
     */
    public static Map<String, Music> musicMap = new HashMap<String, Music>();

    /**
     * Loads all audio files.
     */
    public static void load() {
        try {
            soundMap.put("nope", new Sound("audio/nope.ogg"));
            soundMap.put("place", new Sound("audio/place.ogg"));
            soundMap.put("cannon", new Sound("audio/cannon.ogg"));
            soundMap.put("gameOver", new Sound("audio/gameOver.ogg"));
            soundMap.put("gameWin", new Sound("audio/gameWin.ogg"));
            soundMap.put("sell", new Sound("audio/sell.ogg"));
            soundMap.put("shot", new Sound("audio/shot.ogg"));
            soundMap.put("mage", new Sound("audio/mage.ogg"));

            // Load music for the menu
            musicMap.put("menu", new Music("audio/music/menu.ogg"));
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the music associated with the given key.
     *
     * @param key The key associated with the music.
     * @return The Music object.
     */
    public static Music getMusic(String key) {
        return musicMap.get(key);
    }

    /**
     * Retrieves the sound effect associated with the given key.
     *
     * @param key The key associated with the sound effect.
     * @return The Sound object.
     */
    public static Sound getSound(String key) {
        return soundMap.get(key);
    }
}
