package org.example.alghoritms;

import org.example.Task;

import java.awt.*;
import java.util.*;
import java.util.List;

public class SSTF {
    Queue<Task> tasks;
    List<Task> readyQueue;
    int timeElapsed;
    Task currentTask;
    int currentPos;
    int totalDistance;
    List<Task> allTasks;

    public SSTF(Queue<Task> processes) {
        this.tasks = processes;
        this.readyQueue = new ArrayList<>();
        this.totalDistance = 0;
        this.currentPos = 0;
        this.currentTask = null;
        this.allTasks = new ArrayList<>(processes);
    }

    public void start() {
        while (!tasks.isEmpty() || !readyQueue.isEmpty()) {

            while (!tasks.isEmpty() && tasks.peek().getArrivalTime() <= timeElapsed) {
                readyQueue.add(tasks.remove());
            }
            if (!readyQueue.isEmpty()) {
                Task activeTask = findClosestTask();
                readyQueue.remove(activeTask);

                int distance = Math.abs(currentPos - activeTask.getPosition());
                totalDistance += distance;
                currentPos = activeTask.getPosition();



                activeTask.setMovementTime(distance);
                activeTask.execute(timeElapsed);

                timeElapsed += distance;


            } else {
                timeElapsed = tasks.peek().getArrivalTime();
            }
        }

        stats();
    }

    private void stats(){
        System.out.println("-----------------------------------");
        System.out.println("SSTF");
        System.out.println("Total distance is " + totalDistance);
        double avgMovement = totalDistance / (double)allTasks.size();
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

    private Task findClosestTask() {
        Task closestTask = null;
        int minDistance = Integer.MAX_VALUE;

        for (Task t : readyQueue) {
            int distance = Math.abs(this.currentPos - t.getPosition());

            if (distance < minDistance) {
                minDistance = distance;
                closestTask = t;
            }
        }
        return closestTask;
    }

    private int calculateDistance(Task task) {
        if(task == null){return Integer.MAX_VALUE;}
        int distance = Math.abs(this.currentPos -  task.getPosition());
        return distance;
    }






}
