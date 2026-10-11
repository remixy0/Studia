package Zadanie1;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MainLoop {
    static int PROCESSES_DEADLINE = 5000;
    static int TOTAL_PROCESSES = 10000;
    static ProcessGenerator processGenerator = new ProcessGenerator(2, 30, 12, TOTAL_PROCESSES);
    static LinkedList<Process> generatedProcesses = new LinkedList<>(processGenerator.generateProcesses());



    public static void main(String[] args) {
       FCFS();
       SJF();
       RoundRobin(5);
    }

    public static void FCFS(){
        LinkedList<Process> processes = (LinkedList<Process>) generatedProcesses.clone();
        processes.stream().forEach(process -> process.reset());
        int completedProcesses = 0;
        int processSwitches = 0;
        int timeElapsed = 0;
        List<Integer> waitingTimes = new ArrayList<>();
        Process currentProcess = null;
        Queue<Process> readyQueue = new LinkedList<>();


        while (completedProcesses < PROCESSES_DEADLINE) {

            while (!processes.isEmpty() && processes.peek().arriveTime <= timeElapsed) {
                readyQueue.offer(processes.remove());
            }

            if (currentProcess == null) {
                currentProcess = readyQueue.poll();
            }

            if (currentProcess != null) {
//                System.out.println("Time: " + timeElapsed + " | Processing ID: " + currentProcess.id);
                boolean isFinished = currentProcess.startProcessing(timeElapsed);

                if (isFinished) {
                    completedProcesses++;
                    processSwitches++;
                    waitingTimes.add(currentProcess.waitingTime);
                    currentProcess = null;
                }
            } else {
//                System.out.println("Time: " + timeElapsed + " | CPU Idle");
            }
            timeElapsed++;
        }

        System.out.println("");
        System.out.println("-----------------------");
        System.out.println("FCFS");
        System.out.println("Total Time: " + timeElapsed);
        System.out.println("Total Switches: " + processSwitches);
        System.out.println("Average waiting time: " + waitingTimes.stream().mapToInt(Integer::intValue).average().getAsDouble());
        System.out.println("Longest waiting time: " + waitingTimes.stream().mapToInt(Integer::intValue).max().getAsInt());
        System.out.println("-----------------------");
    }


    public static void SJF(){
        LinkedList<Process> processes = (LinkedList<Process>) generatedProcesses.clone();
        processes.stream().forEach(process -> process.reset());
        int completedProcesses = 0;
        int processSwitches = 0;
        int timeElapsed = 0;
        List<Integer> waitingTimes = new ArrayList<>();
        Process currentProcess = null;
        ArrayList<Process> readyQueue = new ArrayList<>();

        while (completedProcesses < PROCESSES_DEADLINE) {

            while (!processes.isEmpty() && processes.peek().arriveTime <= timeElapsed) {
                Process newProcess = processes.remove();
                readyQueue.add(binarySearch(readyQueue, newProcess), newProcess);
            }

            if (currentProcess == null && !readyQueue.isEmpty()) {
                currentProcess = readyQueue.get(0);
                readyQueue.remove(currentProcess);
            }

            if (currentProcess != null) {
//                System.out.println("Time: " + timeElapsed + " | Processing ID: " + currentProcess.id);
                boolean isFinished = currentProcess.startProcessing(timeElapsed);

                if (isFinished) {
                    completedProcesses++;
                    processSwitches++;
                    waitingTimes.add(currentProcess.waitingTime);
                    currentProcess = null;
                }
            } else {
//                System.out.println("Time: " + timeElapsed + " | CPU Idle");
            }
            timeElapsed++;
        }

        System.out.println("");
        System.out.println("-----------------------");
        System.out.println("SJF");
        System.out.println("Total Time: " + timeElapsed);
        System.out.println("Total Switches: " + processSwitches);
        System.out.println("Average waiting time: " + waitingTimes.stream().mapToInt(Integer::intValue).average().getAsDouble());
        System.out.println("Longest waiting time: " + waitingTimes.stream().mapToInt(Integer::intValue).max().getAsInt());
        System.out.println("-----------------------");
    }

    public static void RoundRobin(int k){
        LinkedList<Process> processes = (LinkedList<Process>) generatedProcesses.clone();
        processes.stream().forEach(process -> process.reset());
        int completedProcesses = 0;
        int processSwitches = 0;
        int timeElapsed = 0;
        int index = 0;
        List<Integer> waitingTimes = new ArrayList<>();
        Process currentProcess = null;
        List<Process> readyQueue = new ArrayList<>();

        while (completedProcesses < PROCESSES_DEADLINE) {

            while (!processes.isEmpty() && processes.peek().arriveTime <= timeElapsed) {
                readyQueue.add(processes.remove());
            }

            if(index >= readyQueue.size()){
                index = 0;
            }

            if(index < readyQueue.size()){
                currentProcess = readyQueue.get(index);
                processSwitches++;
            }else{
                timeElapsed++;
            }

            if (currentProcess != null) {
                for (int i = 0; i < k; i++) {
                    boolean isFinished = currentProcess.startProcessing(timeElapsed);
//                    System.out.println("Time: " + timeElapsed + " | Processing ID: " + currentProcess.id);
                    timeElapsed++;

                    if (isFinished) {
//                        System.out.println("Wykonano id: " + currentProcess.id );
                        completedProcesses++;
                        waitingTimes.add(currentProcess.waitingTime);
                        readyQueue.remove(currentProcess);
                        processes.remove(currentProcess);
                        currentProcess = null;
                        break;
                    }
                }
            } else {
//                System.out.println("Time: " + timeElapsed + " | CPU Idle");
            }
            index ++;

        }

        System.out.println("");
        System.out.println("-----------------------");
        System.out.println("RoundRobin");
        System.out.println("Total Time: " + timeElapsed);
        System.out.println("Total Switches: " + processSwitches);
        System.out.println("Average waiting time: " + waitingTimes.stream().mapToInt(Integer::intValue).average().getAsDouble());
        System.out.println("Longest waiting time: " + waitingTimes.stream().mapToInt(Integer::intValue).max().getAsInt());
        System.out.println("-----------------------");
    }





    public static int binarySearch(ArrayList<Process> processes, Process process){
        int low = 0;
        if(processes.size() > 0) {
            int processTime = process.processTime;
            int high = processes.size() - 1;

            while (low != high) {
                int mid = (low + high) / 2;
                if (processes.get(mid).arriveTime > processTime) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return low;
    }


}