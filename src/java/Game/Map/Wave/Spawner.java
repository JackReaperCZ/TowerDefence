package Game.Map.Wave;

import Game.Game;
import Game.Handler;
import Game.Map.Path;
import Game.Monsters.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * The Spawner class manages the spawning of waves of monsters on the map.
 */
public class Spawner {
    /**
     * Arraylist of waves in this map
     */
    private final ArrayList<Wave> waves = new ArrayList<>();
    /**
     * Wave counter in ticks
     */
    public static int WAVE_COUNTER = 0;
    /**
     * Index of th wave
     */
    public static int ACTUAL_WAVE = 0;
    /**
     * Wave timer in seconds
     */
    public static int WAVE_TIMER = 0;
    /**
     * Control variable for switching spawning of monsters
     */
    public static boolean SPAWN = false;

    /**
     * Constructs a Spawner object and initializes it with waves based on data from the waves.txt file.
     *
     * @param path       The path for the monsters to follow.
     * @param handler    The handler to manage game objects.
     * @param sourcePath The source path of the map folder.
     */
    public Spawner(Path path, Handler handler, String sourcePath) {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(sourcePath + "/waves.txt");
        if (inputStream != null) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                br.readLine(); // Get head out
                String line = br.readLine();
                int waveId = 0;
                while (line != null) {
                    switch (line.charAt(0)) {
                        case ' ' -> {
                            //Getting parameters from string
                            String[] spawnNodeStrings = line.substring(1).split(",");
                            int time = Integer.parseInt(spawnNodeStrings[0]);
                            String mosterName = spawnNodeStrings[1];
                            int count = Integer.parseInt(spawnNodeStrings[2]);
                            int gap = Integer.parseInt(spawnNodeStrings[3]);
                            Type type = Type.valueOf(spawnNodeStrings[4]);

                            Monster m = null;

                            //Creating monster
                            switch (mosterName) {
                                case "slime" ->
                                        m = new Slime(path.getFlag(0).getX(), path.getFlag(0).getY(), type, handler);
                                case "snake" ->
                                        m = new Snake(path.getFlag(0).getX(), path.getFlag(0).getY(), type, handler);
                                case "spider" ->
                                        m = new Spider(path.getFlag(0).getX(), path.getFlag(0).getY(), type, handler);
                                case "worm" ->
                                        m = new Worm(path.getFlag(0).getX(), path.getFlag(0).getY(), type, handler);
                            }
                            if (m != null) {
                                m.goTo(path.getFlag(1).getX(), path.getFlag(1).getY());
                            }

                            //Creating and adding spawn node to the wave
                            this.waves.get(waveId - 1).addSpawnNode(new SpawnNode(time, m, count, gap));
                        }
                        case 'w' -> {
                            waveId++;
                            this.waves.add(new Wave(handler));
                        }
                    }
                    line = br.readLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Updates the spawner and progresses the wave.
     */
    public void tick() {
        if (SPAWN && Game.gameState == Game.STATE.GAME) {
            if (WAVE_COUNTER == 120) {
                WAVE_COUNTER = 0;
                WAVE_TIMER++;
            } else {
                WAVE_COUNTER++;
            }
            try {
                this.waves.get(ACTUAL_WAVE).tick();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
            if (this.waves.get(ACTUAL_WAVE).isDone()) {
                WAVE_TIMER = 0;
                WAVE_COUNTER = 0;
                ACTUAL_WAVE++;
                SPAWN = false;
            }
        }
    }

    /**
     * Retrieves the list of waves.
     *
     * @return The list of waves.
     */
    public ArrayList<Wave> getWaves() {
        return waves;
    }
}