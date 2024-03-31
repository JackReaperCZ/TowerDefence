package Game.Monsters.Comparators;

import Game.Monsters.Monster;

import java.util.Comparator;

public class ProgressionComparator implements Comparator<Monster> {
    @Override
    public int compare(Monster o1, Monster o2) {
        return Integer.compare(o1.getProgression(), o2.getProgression());
    }
}
