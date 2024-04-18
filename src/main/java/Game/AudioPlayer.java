package Game;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

import java.util.HashMap;
import java.util.Map;

public class AudioPlayer {
    public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
    public static Map<String, Music> musicMap = new HashMap<String, Music>();

    public static void load() {
        try {
            soundMap.put("nope", new Sound("src/main/data/audio/nope.ogg"));
            soundMap.put("place", new Sound("src/main/data/audio/place.ogg"));
            soundMap.put("cannon", new Sound("src/main/data/audio/cannon.ogg"));
            soundMap.put("gameOver", new Sound("src/main/data/audio/gameOver.ogg"));
            soundMap.put("gameWin", new Sound("src/main/data/audio/gameWin.ogg"));
            soundMap.put("sell", new Sound("src/main/data/audio/sell.ogg"));
            soundMap.put("shot", new Sound("src/main/data/audio/shot.ogg"));
            soundMap.put("mage", new Sound("src/main/data/audio/mage.ogg"));
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public static Music getMusic(String key) {
        return musicMap.get(key);
    }

    public static Sound getSound(String key) {
        return soundMap.get(key);
    }
}
