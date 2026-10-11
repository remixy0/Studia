package org.example;

import java.util.List;

public class Main {
    public static final int frames = 400;

    public static void main(String[] args) {
        PageGenerator pageGenerator = new PageGenerator(500, 10000, List.of(1,5,1,1,9));
        List sekwencja = pageGenerator.generatePages();
        System.out.println(sekwencja);
        Simulator simulator = new Simulator(sekwencja);

        simulator.runFIFO(frames);
        simulator.runOPT(frames);
        simulator.runLRU(frames);
        simulator.runALRU(frames);
        simulator.runRAND(frames);
    }

}