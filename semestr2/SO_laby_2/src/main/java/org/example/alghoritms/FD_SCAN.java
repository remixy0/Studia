package org.example.alghoritms;

import org.example.Task;

import java.util.*;

public class FD_SCAN {
    Queue<Task> tasks;
    List<Task> taskQueue;
    List<Task> realTimeTaskQueue;
    int timeElapsed;
    Task currentTask;
    Task currentRealTimeTask;
    int currentPos;
    int totalDistance;
    int sizeOfDisk;
    boolean isIncreasing = true;
    List<Task> allTasks;

    public FD_SCAN(Queue<Task> processes, int sizeOfDisk) {
        this.tasks = processes;
        this.taskQueue = new ArrayList<>();
        this.realTimeTaskQueue = new ArrayList<>();
        this.totalDistance = 0;
        this.currentPos = 0;
        this.currentTask = null;
        this.sizeOfDisk = sizeOfDisk;
        this.currentRealTimeTask = null;
        this.allTasks = new ArrayList<>(processes);
    }

    public void start() {

        while(!tasks.isEmpty() || !taskQueue.isEmpty()) {

            while (!tasks.isEmpty() && tasks.peek().getArrivalTime() <= timeElapsed) {
                Task newProcess = tasks.remove();
                if(newProcess.getDeadline() > 0){
                    realTimeTaskQueue.add(newProcess);
                    realTimeTaskQueue.sort(Comparator.comparingInt(Task::getDeadline));
                }
                taskQueue.add(newProcess);
            }

            getNewRealTimeTask();

            checkIfThereIsTaskAndUpdate();

            checkIfValid();

            if(currentRealTimeTask != null && currentRealTimeTask.wasDeadlineMeet()){
                currentRealTimeTask = null;
            }

            timeElapsed ++;
            totalDistance ++;


            if (currentPos >= sizeOfDisk) {
                isIncreasing = false;
            }
            if(currentPos <= 0) {
                isIncreasing = true;
            }

            if(isIncreasing) {
                currentPos ++;
            }else{
                currentPos--;
            }


        }


        stats();

    }


    private void stats(){
        double percentage = 100 * allTasks.stream().filter(x -> x.getDeadline() > 0 && x.wasDeadlineMeet()).count() / allTasks.stream().filter(x -> x.getDeadline() > 0).count();
        System.out.println("-----------------------------------");
        System.out.println("FD-SCAN");
        System.out.println("Total distance is " + totalDistance);
        double avgMovement = totalDistance / (double)allTasks.size();
        System.out.println("Percentage of deadlines met " + Math.round(percentage) + "%");
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

    private void getNewRealTimeTask() {
        if (currentRealTimeTask != null) return;

        for (int i = 0; i < realTimeTaskQueue.size(); i++) {
            Task potentialTask = realTimeTaskQueue.get(i);

            if (calculateDistance(potentialTask, currentPos) < potentialTask.getDeadlineLeft()) {
                currentRealTimeTask = realTimeTaskQueue.remove(i);
                isIncreasing = currentRealTimeTask.getPosition() > currentPos;
                return;
            }
        }
    }



    private void checkIfThereIsTaskAndUpdate(){

        if(taskQueue.isEmpty()){return;}

        while(isTaskAtPosition(currentPos)) {

            Task newTask = getTaskAtPosition(currentPos);

            if(currentTask == null){currentTask = newTask;}

            newTask.setMovementTime(calculateDistance(currentTask,newTask));
            newTask.execute(timeElapsed);
            currentTask = newTask;

            if(newTask.getDeadline() > 0){

                if (newTask.getDeadlineLeft() > 0) {
                    newTask.meetDeadline();
                }

                if (newTask.equals(currentRealTimeTask)) {
                    currentRealTimeTask = null;
                }

            }

            taskQueue.remove(newTask);
            realTimeTaskQueue.remove(newTask);
        }
    }

    private void checkIfValid(){
        realTimeTaskQueue.stream().forEach(x -> x.updateDeadline());
        realTimeTaskQueue.removeIf(task -> task.getDeadlineLeft() <= 0);
        taskQueue.removeIf(task -> task.getDeadline() > 0 && task.getDeadlineLeft() <= 0);

        if(currentRealTimeTask == null) {return;}

        currentRealTimeTask.updateDeadline();
        if(currentRealTimeTask.getDeadlineLeft() < 0){
            taskQueue.remove(currentRealTimeTask);
            currentRealTimeTask = null;
        }
    }

    private boolean isTaskAtPosition(int position){
        return taskQueue.stream().anyMatch(task -> task.getPosition() == position);
    }

    private Task getTaskAtPosition(int position){
        return taskQueue.stream().filter(task -> task.getPosition() == position).findFirst().orElse(null);
    }

    private int calculateDistance(Task task, Task currentTask) {
        int distance = Math.abs(currentTask.getPosition() -  task.getPosition());
        return distance;
    }

    private int calculateDistance(Task task, int currentPosition) {
        int distance = Math.abs(currentPosition -  task.getPosition());
        return distance;
    }






}

