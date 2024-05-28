package Game.Map;

import Game.Game;

import java.io.*;
import java.util.ArrayList;

/**
 * The Path class represents the path that monsters follow on the game map.
 * It loads flags from a file and builds the path by connecting these flags.
 */
public class Path {
    /**
     * Source path of the map folder
     */
    private String sourcePath;
    /**
     * ArrayList of flags
     */
    private ArrayList<Flag> flags = new ArrayList<>();

    /**
     * Constructs a Path object with the specified source path.
     *
     * @param sourcePath The source path of the map folder.
     */
    public Path(String sourcePath) {
        this.sourcePath = sourcePath;
        loadFlags();
        buildPath();
    }

    /**
     * Loads flag data from the "path.txt" file.
     * Each line in the file represents a flag's position on the map.
     */
    public void loadFlags() {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(sourcePath + "/path.txt");
        if (inputStream != null) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                br.readLine(); // Get head out
                String line = br.readLine();
                while (line != null) {
                    String[] valuesS = line.split(",");
                    Integer[] valuesI = new Integer[2];
                    for (int i = 0; i <= 1; i++) {
                        switch (valuesS[i]) {
                            case "GW" -> valuesI[i] = Game.WIDTH + 76;
                            case "GH" -> valuesI[i] = Game.HEIGHT + 76;
                            default -> valuesI[i] = Integer.valueOf(valuesS[i]);
                        }
                    }
                    flags.add(new Flag(valuesI[0], valuesI[1]));
                    line = br.readLine();
                }

            } catch (FileNotFoundException fnfe) {
                System.out.println("File path.txt not found.");
            } catch (NumberFormatException nfe) {
                System.out.println("Unexpected value.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Builds the path by connecting all flags.
     */
    public void buildPath() {
        for (int i = 0; i < flags.size() - 1; i++) {
            flags.get(i).setNextFlag(flags.get(i + 1));
        }
    }

    /**
     * Gets the flag at the specified index.
     *
     * @param i The index of the flag.
     * @return The flag at the specified index.
     */
    public Flag getFlag(int i) {
        return flags.get(i);
    }

    /**
     * Gets the list of flags representing the path.
     *
     * @return The list of flags representing the path.
     */
    public ArrayList<Flag> getFlags() {
        return flags;
    }
}
