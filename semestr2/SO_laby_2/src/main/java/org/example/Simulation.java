package org.example;
import org.example.alghoritms.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class Simulation {

    List<Integer> spawnRateSequence;
    List<Integer> spawnSectorSequence;
    int sizeOfDisk;
    int spawnRate;
    int realTimeSpawnRate;
    TaskGenerator generator;
    Queue<Task> tasks;

    public Simulation(int sizeOfDisk, int spawnRate,int realTimeSpawnRate,List<Integer> spawnRateSequence,List<Integer> spawnSectorSequence) {
        this.sizeOfDisk = sizeOfDisk;
        this.spawnRate = spawnRate;
        this.realTimeSpawnRate = realTimeSpawnRate;
        this.spawnRateSequence = spawnRateSequence;
        this.spawnSectorSequence = spawnSectorSequence;
        this.generator = new TaskGenerator(sizeOfDisk,spawnRate,1000,this.spawnRateSequence,this.spawnSectorSequence,realTimeSpawnRate);
    }

    public Queue<Task> generateTasks() {
        this.tasks = this.generator.generateTasks();
        return this.tasks;
    }

    public Queue<Task> generateTasks(List<Integer> spawnRateSequence, List<Integer> spawnSectorSequence, int spawnRate,int realTimeSpawnRate) {
        this.generator = new TaskGenerator(sizeOfDisk,spawnRate,10000,spawnRateSequence,spawnSectorSequence,realTimeSpawnRate);
        this.tasks = this.generator.generateTasks();
        return this.tasks;
    }

    public void startFIFO(){
        FIFO fifo = new FIFO(getDeepCopyOfTasks());
        fifo.start();
    }

    public void startSSTF(){
        SSTF sstf = new SSTF(getDeepCopyOfTasks());
        sstf.start();
    }

    public void startSCAN(){
        SCAN scan = new SCAN(getDeepCopyOfTasks(),sizeOfDisk);
        scan.start();
    }

    public void startCSCAN(){
        C_SCAN scan = new C_SCAN(getDeepCopyOfTasks(),sizeOfDisk);
        scan.start();
    }

    public void startEDF(){
        EDF scan = new EDF(getDeepCopyOfTasks(),sizeOfDisk);
        scan.start();
    }

    public void startFDSCAN(){
        FD_SCAN scan = new FD_SCAN(getDeepCopyOfTasks(),sizeOfDisk);
        scan.start();
    }

    private Queue<Task> getDeepCopyOfTasks() {
        Queue<Task> copyQueue = new LinkedList<>();
        for (Task originalTask : this.tasks) {
            copyQueue.add(new Task(originalTask));
        }
        return copyQueue;
    }


}
