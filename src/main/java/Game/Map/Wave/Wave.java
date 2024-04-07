package Game.Map.Wave;

import Game.Handler;
import Game.Monsters.Monster;

import java.util.ArrayList;

public class Wave {
    //Handler
    private Handler handler;
    //Control variable to see if the wave is done
    private boolean done = false;
    //Control variable to see if the spawn node is done with spawning
    private boolean spawnNodeDone = true;
    //Gap between each spawn in ticks
    private int spawnNodeGap = 0;
    //Number of spawn nodes in wave
    private int spawnNodeCount = 0;
    //Number of spawn nodes that has been done in the wave
    private int spawnNodeCountDone = 0;
    //next activation of spawn in ticks
    private int spawnNodeNextActivation = 0;
    //Flag for the actual spawn node to execute
    private int spawnNodeFlag = 0;
    //Arraylist of spawn nodes
    private ArrayList<SpawnNode> spawnNodes = new ArrayList<>();
    //Constrictor
    public Wave(Handler handler) {
        this.handler = handler;
    }
    //tick method
    public void tick() throws CloneNotSupportedException {
        if (!done) {
            if (spawnNodeDone) {
                if (spawnNodes.get(spawnNodeFlag).getTime() == Spawner.WAVE_TIMER) {
                    //Spawn the monster
                    handler.addGameObject((Monster) spawnNodes.get(spawnNodeFlag).getMonster().clone());

                    //Do I need to repeat?
                    if (spawnNodes.get(spawnNodeFlag).getCount() > 1) {
                        //Yes
                        this.spawnNodeGap = spawnNodes.get(spawnNodeFlag).getGap();
                        this.spawnNodeCount = spawnNodes.get(spawnNodeFlag).getCount();
                        this.spawnNodeCountDone = 1;
                        this.spawnNodeDone = false;

                        this.spawnNodeNextActivation = this.spawnNodeGap + Spawner.WAVE_COUNTER;
                        if (spawnNodeNextActivation > 120) {
                            spawnNodeNextActivation -= 120;
                        }

                    } else {
                        //No
                        checkIfDone();
                        this.spawnNodeFlag++;
                    }
                }
            } else {
                if (spawnNodeNextActivation == Spawner.WAVE_COUNTER) {
                    //Spawn the monster
                    handler.addGameObject((Monster) spawnNodes.get(spawnNodeFlag).getMonster().clone());
                    spawnNodeCountDone++;

                    this.spawnNodeNextActivation = this.spawnNodeGap + Spawner.WAVE_COUNTER;
                    if (spawnNodeNextActivation > 120) {
                        spawnNodeNextActivation -= 120;
                    }

                    //Check if the node is done
                    if (this.spawnNodeCountDone - 1 == this.spawnNodeCount) {
                        this.spawnNodeFlag++;
                        spawnNodeDone = true;
                        checkIfDone();
                    }
                }
            }
        }
    }
    //Getters and setters
    public void checkIfDone() {
        if (spawnNodeFlag == spawnNodes.size()) {
            this.done = true;
        }
    }

    public void addSpawnNode(SpawnNode spawnNode) {
        spawnNodes.add(spawnNode);
    }

    public boolean isDone() {
        return done;
    }
}
