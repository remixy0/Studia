package Zadanie1;

public class Process {
    int id;
    int arriveTime;
    int processTime;
    int waitingTime = 0;
    int finishTime = 0;
    int timeLeftToProcess = 0;
    boolean didStartProcessing = false;
    boolean didFinishProcessing = false;

    public Process(int id, int arriveTime, int processTime) {
        this.id = id;
        this.arriveTime = arriveTime;
        this.processTime = processTime;
        this.timeLeftToProcess = processTime;
    }

    public boolean startProcessing(int currentTime) {
        if(!didStartProcessing) {
            this.waitingTime = currentTime -  this.arriveTime;
        }
        this.didStartProcessing = true;
        if(this.timeLeftToProcess > 0) {
            this.timeLeftToProcess --;
        }else{
            this.finishTime = currentTime - this.arriveTime;
            this.didFinishProcessing = true;
            return true;
        }
        return false;
    }

    public void reset(){
        this.waitingTime = 0;
        this.timeLeftToProcess = processTime;
        this.didStartProcessing = false;
        this.didFinishProcessing = false;
    }

    @Override
    public String toString() {
        return "Id: " + this.id + " ArrT: " + this.arriveTime + " ProcessT: " + this.processTime;
    }

}
