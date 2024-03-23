package Game.Map;

import java.util.ArrayList;

public class Path {
    private ArrayList<Flag> flags = new ArrayList<>();
    //Method that connects all flags
    public void buildPath(){
        for (int i = 0; i < flags.size()-1; i++){
            flags.get(i).setNextFlag(flags.get(i+1));
        }
    }

    //Adds new flag to the arraylist flags
    public void addFlag(Flag f){
        flags.add(f);
    }
    //Getters and setters
    public Flag getFlag(int i){
        return flags.get(i);
    }
    public ArrayList<Flag> getFlags(){
        return flags;
    }
}
