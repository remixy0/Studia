package org.example.alghoritms;

import org.example.Task;

import java.util.*;

public class SCAN {
    Queue<Task> tasks;
    List<Task> readyQueue;
    int timeElapsed;
    Task currentTask;
    int currentPos;
    int totalDistance;
    int sizeOfDisk;
    List<Task> allTasks;

    public SCAN(Queue<Task> processes, int sizeOfDisk) {
        this.tasks = processes;
        this.readyQueue = new ArrayList<>();
        this.totalDistance = 0;
        this.currentPos = 0;
        this.currentTask = null;
        this.sizeOfDisk = sizeOfDisk;
        this.allTasks = new ArrayList<>(processes);
    }

    public void start() {
        boolean isIncreasing = true;
        while(!tasks.isEmpty() || !readyQueue.isEmpty()) {

            while (!tasks.isEmpty() && tasks.peek().getArrivalTime() <= timeElapsed) {
                Task newProcess = tasks.remove();
                readyQueue.add(newProcess);
            }

            while( !readyQueue.isEmpty() && isTaskAtPosition(currentPos)) {
                currentTask = getTaskAtPosition(currentPos);
                currentTask.execute(timeElapsed);
                readyQueue.remove(currentTask);
            }

            timeElapsed ++;
            totalDistance ++;
            if(isIncreasing) {
                currentPos ++;
            }else{
                currentPos--;
            }
            if (currentPos >= sizeOfDisk) {
                isIncreasing = false;
            }
            if(currentPos <= 0) {
                isIncreasing = true;
            }

        }


        stats();

    }

    private void stats(){
        System.out.println("-----------------------------------");
        System.out.println("SCAN");
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


   private boolean isTaskAtPosition(int position){
        return readyQueue.stream().anyMatch(task -> task.getPosition() == position);
   }

   private Task getTaskAtPosition(int position){
        return readyQueue.stream().filter(task -> task.getPosition() == position).findFirst().orElse(null);
   }

    private int calculateDistance(Task task) {
        int distance = Math.abs(this.currentPos -  task.getPosition());
        this.currentPos = task.getPosition();
        task.setMovementTime(distance);
        return distance;
    }






}

