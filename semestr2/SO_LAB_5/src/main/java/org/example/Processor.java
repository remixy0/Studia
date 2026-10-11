package org.example;

import java.util.ArrayList;
import java.util.List;

public class Processor {
    public int id;
    public List<Task> activeTasks;

    public Processor(int id) {
        this.id = id;
        this.activeTasks = new ArrayList<>();
    }

    public double getCurrentLoad() {
        double totalLoad = 0;
        for (Task t : activeTasks) {
            totalLoad += t.load;
        }
        return totalLoad;
    }

    public void addTask(Task task) {
        activeTasks.add(task);
    }

    public void updateTasks() {
        activeTasks.removeIf(task -> {
            task.remainingDuration--;
            return task.remainingDuration <= 0;
        });
    }

    public void clear() {
        activeTasks.clear();
    }
}
