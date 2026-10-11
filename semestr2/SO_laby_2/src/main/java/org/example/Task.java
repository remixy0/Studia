package org.example;

public class Task {
    int id;
    int position;
    int arrivalTime;
    int movementTime;
    int deadline;
    int deadlineLeft;
    boolean wasDeadlineMeet = false;
    int waitingTime;


    public Task(Task task){
        this.id = task.id;
        this.position = task.position;
        arrivalTime = task.arrivalTime;
        this.deadline = task.deadline;
        this.deadlineLeft = task.deadline;
        this.movementTime = 0;
        this.wasDeadlineMeet = false;
        this.waitingTime = 0;

    }

    public Task(int id, int position, int arrivalTime, int deadline) {
        this.id = id;
        this.position = position;
        this.arrivalTime = arrivalTime;
        this.deadline = deadline ;
        this.deadlineLeft = deadline;
    }

    public int getPosition() {
        return position;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public boolean didArrive(int currentTime) {
        return currentTime >= arrivalTime;
    }

    public void setMovementTime(int movementTime) {
        this.movementTime = movementTime;
    }

    public void showMovementTime(){
        System.out.println("id: "+id+"move: "+movementTime);
    }

    public int getMovementTime() {
        return movementTime;
    }

    public int getDeadline() {
        return deadline;
    }

    public int getDeadlineLeft() {
        return deadlineLeft;
    }

    public int updateDeadline(){
        return --deadlineLeft;
    }

    public void meetDeadline(){
        this.wasDeadlineMeet = true;
    }

    public boolean wasDeadlineMeet(){
        return this.wasDeadlineMeet;
    }

    public void execute(int currentTime){
        this.waitingTime = currentTime - this.arrivalTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }


    @Override
    public String toString() {
        return "id: " + this.id + "  pos: " + this.position + "  deadline: " + this.deadline;
    }
}
