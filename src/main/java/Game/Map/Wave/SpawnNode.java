package Game.Map.Wave;

import Game.Monsters.Monster;

public class SpawnNode {
    private int time;
    private Monster monster;
    private int count;
    private int gap;

    public SpawnNode(int time, Monster monster, int count, int gap) {
        this.time = time;
        this.monster = monster;
        this.count = count;
        this.gap = gap;
    }

    public int getTime() {
        return time;
    }

    public Monster getMonster() {
        return monster;
    }

    public int getCount() {
        return count;
    }

    public int getGap() {
        return gap;
    }
}
