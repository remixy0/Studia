package org.example;

public class Task {
    public double load;
    public int remainingDuration;

    public Task(double load, int duration) {
        this.load = load;
        this.remainingDuration = duration;
    }

    public Task cloneTask() {
        return new Task(this.load, this.remainingDuration);
    }
}