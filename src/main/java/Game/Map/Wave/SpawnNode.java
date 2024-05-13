package Game.Map.Wave;

import Game.Monsters.Monster;

/**
 * The SpawnNode class represents a node in a wave indicating when and what monster to spawn.
 */
public class SpawnNode {
    private int time;
    private Monster monster;
    private int count;
    private int gap;

    /**
     * Constructs a SpawnNode object with the specified time, monster, count, and gap.
     *
     * @param time    The time at which to spawn the monster.
     * @param monster The type of monster to spawn.
     * @param count   The number of times to spawn the monster.
     * @param gap     The gap between each spawn.
     */
    public SpawnNode(int time, Monster monster, int count, int gap) {
        this.time = time;
        this.monster = monster;
        this.count = count;
        this.gap = gap;
    }

    /**
     * Gets the time at which to spawn the monster.
     *
     * @return The time.
     */
    public int getTime() {
        return time;
    }

    /**
     * Gets the type of monster to spawn.
     *
     * @return The monster.
     */
    public Monster getMonster() {
        return monster;
    }

    /**
     * Gets the number of times to spawn the monster.
     *
     * @return The count.
     */
    public int getCount() {
        return count;
    }

    /**
     * Gets the gap between each spawn.
     *
     * @return The gap.
     */
    public int getGap() {
        return gap;
    }
}
