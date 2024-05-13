package Game.Monsters.Comparators;

import Game.Monsters.Monster;

import java.util.Comparator;

/**
 * A comparator implementation that compares monsters based on their health points (HP).
 */
public class HPComparator implements Comparator<Monster> {
    /**
     * Compares two monsters based on their health points (HP).
     *
     * @param o1 the first monster to compare
     * @param o2 the second monster to compare
     * @return a negative integer, zero, or a positive integer if the first monster's
     * HP is less than, equal to, or greater than the second monster's HP, respectively
     */
    @Override
    public int compare(Monster o1, Monster o2) {
        return Integer.compare(o1.getHp(), o2.getHp());
    }
}
