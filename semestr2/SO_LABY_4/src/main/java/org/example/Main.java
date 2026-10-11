package org.example;

import java.util.List;

public class Main {
    public static final int frames = 30;

    public static void main(String[] args) {
        PageGenerator pageGenerator = new PageGenerator(10, 1000);
        List<Process> processes = pageGenerator.generateProcesses(10);
        List pages = pageGenerator.mixSequences(processes);

        System.out.println("Laczna ilosc odwolan: " + pages.size() );

        GlobalSimulator globalSimulator = new GlobalSimulator(pages,processes,frames);

        globalSimulator.runEqualAllocation();
        globalSimulator.runProportionalAllocation();
        globalSimulator.runPPF();
        globalSimulator.runWSS();
    }

}