package org.example.alghoritms;

import org.example.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class FIFO {
    Queue<Task> tasks;
    int currentPos;
    int totalMovement;
    List<Task> allTasks;
    List<Task> readyQueue;
    int timeElapsed;
    Task currentTask;

    public FIFO(Queue<Task> tasks) {
        this.tasks = tasks;
        this.currentPos = 0;
        this.totalMovement = 0;
        this.timeElapsed = 0;
        this.readyQueue = new ArrayList<>();
        this.allTasks = new ArrayList<>(tasks);
    }

    public void start() {
        while(!tasks.isEmpty() || !readyQueue.isEmpty()) {

            while (!tasks.isEmpty() && tasks.peek().getArrivalTime() <= timeElapsed) {
                readyQueue.add(tasks.remove());
            }

            if (!readyQueue.isEmpty()) {
                Task activeTask = readyQueue.remove(0);

                int distance = calculateDistance(activeTask);
                totalMovement += distance;

                activeTask.execute(timeElapsed);

                timeElapsed += distance;

            } else {
                timeElapsed++;
            }
        }

        stats();
    }

    private void stats(){
        System.out.println("-----------------------------------");
        System.out.println("FIFO");
        System.out.println("Total distance is " + totalMovement);
        double avgMovement = totalMovement / (double)allTasks.size();
        System.out.println("Avg movement: " + avgMovement);
        int maxWaitingTime = allTasks.stream().mapToInt(x -> x.getWaitingTime()).max().orElse(0);
        System.out.println("Max waiting time is " + maxWaitingTime);
        double suma = allTasks.stream()
                .mapToDouble(x -> Math.pow(x.getMovementTime() - avgMovement, 2))
                .sum();

        double odchylenieStandardowe = Math.sqrt(suma / allTasks.size());
        System.out.println("Standard deviation " + Math.round(odchylenieStandardowe));
        System.out.println("-----------------------------------");
    }

    private int calculateDistance(Task task){
        int distance = Math.abs(this.currentPos -  task.getPosition());
        this.currentPos = task.getPosition();
        task.setMovementTime(distance);
        return distance;
    }
}
