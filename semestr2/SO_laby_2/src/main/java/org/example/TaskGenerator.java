package org.example;

import java.util.*;

public class TaskGenerator {
    int sizeOfDisk;
    int spawnRate;
    int numberOfTasks;
    List<Integer> spawnRateSequence;
    List<Integer> spawnSectorSequence;
    int chanceOfGettingRealTime;

    Queue<Task> tasks = new LinkedList<>();

    public TaskGenerator(int sizeOfDisk, int spawnRate, int numberOfTasks, List<Integer> spawnRateSequence, List<Integer> spawnSectorSequence, int chanceOfGettingRealTime) {
        this.sizeOfDisk = sizeOfDisk;
        this.spawnRate = spawnRate;
        this.numberOfTasks = numberOfTasks;
        this.spawnRateSequence = spawnRateSequence;
        this.spawnSectorSequence = spawnSectorSequence;
        this.chanceOfGettingRealTime = chanceOfGettingRealTime;
    }

    public Queue<Task> generateTasks(){
        int arrivalTime = 0;
        for (int i = 0; i < numberOfTasks; i++) {
            arrivalTime += calculateBreak(i);
            tasks.add(new Task(i+1,calculateSpawnPlace(),arrivalTime,calculateDeadline()));
        }
        return tasks;
    }

    private int calculateBreak(int index){
        double part =  index / numberOfTasks;
        int element = (int)(part * spawnRateSequence.size());
        return 10 * sizeOfDisk/(spawnRateSequence.get(element) * this.spawnRate);
    }

    private int calculateDeadline(){
        Random rand = new Random();
        int deadline = rand.nextInt(5,sizeOfDisk/2);
        int isRealTimeTask = rand.nextInt(100/chanceOfGettingRealTime);
        if(isRealTimeTask == 1){return deadline;}
        return 0;

    }

    private int calculateSpawnPlace(){
        Random random = new Random();
        int sumOfWeights = this.spawnSectorSequence.stream()
                .mapToInt(Integer::intValue)
                .sum();

        int sectorSize = sizeOfDisk/this.spawnSectorSequence.size();
        int los = random.nextInt(sumOfWeights);
        for (int i = 0; i < this.spawnSectorSequence.size(); i++) {
            los -= this.spawnSectorSequence.get(i);
            if (los < 0) {
                return random.nextInt(i*sectorSize,(i+1)*sectorSize);
            }
        }
        return -1;
    }


}
