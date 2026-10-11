package org.example;

public class TaskArrivalEvent {
    public int tick;
    public int targetProcessorId;
    public double load;
    public int duration;

    public TaskArrivalEvent(int tick, int targetProcessorId, double load, int duration) {
        this.tick = tick;
        this.targetProcessorId = targetProcessorId;
        this.load = load;
        this.duration = duration;
    }
}
