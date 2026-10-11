package Zadanie1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ProcessGenerator {
    int minDuration;
    int maxDuration;
    int spawnRate;
    int numberOfProcesses;
    Random random = new Random();

    public ProcessGenerator(int minDuration, int maxDuration, int spawnRate, int numberOfProcesses) {
        this.minDuration = minDuration;
        this.maxDuration = maxDuration;
        this.spawnRate = spawnRate;
        this.numberOfProcesses = numberOfProcesses;
    }

    public LinkedList<Process> generateProcesses() {
        int arriveTimePrev = 0;
        LinkedList<Process> processes = new LinkedList<>();
        for (int i = 0; i < numberOfProcesses; i++) {
            int processTime = random.nextInt(minDuration,maxDuration);
            int arriveTime = arriveTimePrev + random.nextInt(spawnRate,2*spawnRate);
            arriveTimePrev = arriveTime;
            processes.add(new Process(i, arriveTime, processTime));
        }
        return processes;
    }

}
