package Game.Monsters.Comparators;

import Game.Monsters.Monster;

import java.util.Comparator;
//Comparator that compares by hp
public class HPComparator implements Comparator<Monster> {
    @Override
    public int compare(Monster o1, Monster o2) {
        return Integer.compare(o1.getHp(),o2.getHp());
    }
}
