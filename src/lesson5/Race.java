package lesson5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CyclicBarrier;

public class Race {

    private ArrayList<Stage> stages;

    public CyclicBarrier getAllReady() {
        return allReady;
    }

    private final CyclicBarrier allReady;

    private final ArrayBlockingQueue<String> finish;

    public ArrayList<Stage> getStages() { return stages; }

    public Race(int countPart, Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
        this.finish = new ArrayBlockingQueue<>(countPart);
        this.allReady = new CyclicBarrier(countPart + 1);
    }

    public void finishRace(String name) {
        try {
            finish.put(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getWiner() throws InterruptedException {
            return finish.take();
    }

}
