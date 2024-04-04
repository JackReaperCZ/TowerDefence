package Game.Map;

import Game.Game;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Path {
    //Source path of the map folder
    private String sourcePath;
    //Arraylist of flags
    private ArrayList<Flag> flags = new ArrayList<>();

    //Constructor
    public Path(String sourcePath) {
        this.sourcePath = sourcePath;
        loadFlags();
        buildPath();
    }

    //Loading data from path.txt
    public void loadFlags() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(sourcePath + "path.txt"));
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
                flags.add(new Flag(valuesI[0],valuesI[1]));
                line = br.readLine();
            }

        } catch (FileNotFoundException fnfe){
            System.out.println("File path.txt not found.");
        } catch (NumberFormatException nfe) {
            System.out.println("Unexpected value.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Method that connects all flags
    public void buildPath() {
        for (int i = 0; i < flags.size() - 1; i++) {
            flags.get(i).setNextFlag(flags.get(i + 1));
        }
    }
    //Getters and setters
    public Flag getFlag(int i) {
        return flags.get(i);
    }
    public ArrayList<Flag> getFlags() {
        return flags;
    }
}
