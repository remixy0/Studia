package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {

    System.out.println("");
    System.out.println("---------------------");
    System.out.println("Simulation 1 - edging");
    Simulation simulation = new Simulation(100,5,3, List.of(5),List.of(9,1,1,1,1,1,1,1,1,9));
    startSimulation(simulation);

    System.out.println("");
    System.out.println("---------------------");
    System.out.println("Simulation 2 - most task in one sector");
    Simulation simulation2 = new Simulation(100,7,5, List.of(9,1,9,1,1,1,1,5,5,5),List.of(9,1,1,1,1,1,1,1,1,1));
    startSimulation(simulation2);


    System.out.println("");
    System.out.println("---------------------");
    System.out.println("Simulation 3 - low disk usage");
    Simulation simulation3 = new Simulation(100,2,10, List.of(1),List.of(1));
    startSimulation(simulation3);

    }

    private static void startSimulation(Simulation simulation){
        simulation.generateTasks();
        simulation.startFIFO();
        simulation.startSSTF();
        simulation.startSCAN();
        simulation.startCSCAN();
        simulation.startEDF();
        simulation.startFDSCAN();

        for(int i = 0; i < 5; i++){
            System.out.println("");
        }
    }

}