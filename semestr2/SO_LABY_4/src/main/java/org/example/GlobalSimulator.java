package org.example;

import java.awt.*;
import java.util.*;
import java.util.List;

public class GlobalSimulator {

    private final List<PageReference> globalSequence;
    private final List<Process> processes;
    private final int totalFramesInSystem;

    public GlobalSimulator(List<PageReference> globalSequence, List<Process> processes, int totalFrames) {
        this.globalSequence = globalSequence;
        this.processes = processes;
        this.totalFramesInSystem = totalFrames;
    }

    public void runEqualAllocation() {

        Map<Integer, Integer> framesAllocation = new HashMap<>();
        int framesPerProcess = totalFramesInSystem / processes.size();

        for (Process p : processes) {
            framesAllocation.put(p.getId(), framesPerProcess);
        }
        Map<Integer, List<Integer>> localMemories = new HashMap<>();
        for (Process p : processes) {
            localMemories.put(p.getId(), new ArrayList<>());
        }

        runALU(framesAllocation,localMemories,"Rowny przydzial");
    }


    public void runProportionalAllocation() {
        Map<Integer, Integer> framesAllocation = new HashMap<>();
        int framesPerProcess = totalFramesInSystem / processes.size();

        double total = processes.stream().mapToInt(x -> x.getCiagOdwolan().size()).sum();

        Map<Integer, List<Integer>> localMemories = new HashMap<>();
        for (Process p : processes) {
            double rozmiar = p.getCiagOdwolan().size()/total;
            int size = (int)(rozmiar  * totalFramesInSystem);
            if(size <= 0){size = 1;}
            framesAllocation.put(p.getId(), size);
        }
        for (Process p : processes) {
            localMemories.put(p.getId(), new ArrayList<>());
        }

        runALU(framesAllocation,localMemories,"Proporcjonalny przydzial");
    }


    public void runPPF() {

        Map<Integer, Integer> framesAllocation = new HashMap<>();

        double total = processes.stream().mapToInt(x -> x.getCiagOdwolan().size()).sum();

        Map<Integer, List<Integer>> localMemories = new HashMap<>();
        for (Process p : processes) {
            double rozmiar = p.getCiagOdwolan().size()/total;
            int size = (int)(rozmiar  * totalFramesInSystem);
            if(size <= 0){size = 1;}
            framesAllocation.put(p.getId(), size);
        }
        for (Process p : processes) {
            localMemories.put(p.getId(), new ArrayList<>());
        }

        PPF(framesAllocation,localMemories,"PPF");
    }


    public void runWSS() {

        Map<Integer, Integer> framesAllocation = new HashMap<>();
        int framesPerProcess = totalFramesInSystem / processes.size();

        Map<Integer, List<Integer>> localMemories = new HashMap<>();
        for (Process p : processes) {
            framesAllocation.put(p.getId(), framesPerProcess);
        }
        for (Process p : processes) {
            localMemories.put(p.getId(), new ArrayList<>());
        }

        WSS(framesAllocation,localMemories,"WSS");
    }



    private void runALU(Map<Integer,Integer> framesPerProcess, Map<Integer, List<Integer>> localMemories, String alghoritmName) {
        int globalFaults = 0;
        int oknoCzasoweW = 50;
        int thrashingCounter = 0;
        List<Boolean> recentHistory = new ArrayList<>();

        for (PageReference ref : globalSequence) {
            int pid = ref.processId();
            int page = ref.pageNumber();

            List<Integer> processMemory = localMemories.get(pid);
            boolean wasFault = false;

            if (!processMemory.contains(page)) {
                wasFault = true;
                globalFaults++;

                if (processMemory.size() >= framesPerProcess.get(pid)) {
                    processMemory.remove(0);
                }
                processMemory.add(page);
            } else {
                processMemory.remove(Integer.valueOf(page));
                processMemory.add(page);
            }

            recentHistory.add(wasFault);
            if (recentHistory.size() > oknoCzasoweW) {
                recentHistory.remove(0);
            }

            if (recentHistory.size() == oknoCzasoweW) {
                long errorsInWindow = recentHistory.stream().filter(b -> b).count();
                if (errorsInWindow > (oknoCzasoweW / 2)) {
                    thrashingCounter++;
                    recentHistory.clear();
                }
            }
        }

        System.out.println(alghoritmName + " - bledy strony: " + globalFaults + "    szamotania: " + thrashingCounter);
    }



    private void PPF(Map<Integer,Integer> framesPerProcess, Map<Integer, List<Integer>> localMemories, String alghoritmName) {
        int maxErrors = 8;
        int minErrors = 4;
        int oknoCzasowe2 = 10;

        int globalFaults = 0;
        int oknoCzasoweW = 50;
        int thrashingCounter = 0;

        List<Boolean> recentHistory = new ArrayList<>();
        Map<Integer, List<Boolean>> faultNumber = new HashMap<>();

        for (Integer pid : framesPerProcess.keySet()) {
            faultNumber.put(pid, new ArrayList<>());
        }

        int assignedFrames = framesPerProcess.values().stream().mapToInt(Integer::intValue).sum();
        int freeFramesPool = totalFramesInSystem - assignedFrames;

        Set<Integer> pausedProcesses = new HashSet<>();

        for (PageReference ref : globalSequence) {
            int pid = ref.processId();
            int page = ref.pageNumber();

            if (pausedProcesses.contains(pid)) {
                continue;
            }

            List<Integer> processMemory = localMemories.get(pid);
            boolean wasFault = false;

            if (!processMemory.contains(page)) {
                wasFault = true;
                globalFaults++;

                if (processMemory.size() >= framesPerProcess.get(pid)) {
                    processMemory.remove(0);
                }
                processMemory.add(page);
            } else {
                processMemory.remove(Integer.valueOf(page));
                processMemory.add(page);
            }


            recentHistory.add(wasFault);
            if (recentHistory.size() > oknoCzasoweW) recentHistory.remove(0);
            if (recentHistory.size() == oknoCzasoweW) {
                long errorsInWindow = recentHistory.stream().filter(b -> b).count();
                if (errorsInWindow > (oknoCzasoweW / 2)) {
                    thrashingCounter++;
                    recentHistory.clear();
                }
            }

            faultNumber.get(pid).add(wasFault);

            if (faultNumber.get(pid).size() > oknoCzasowe2) {
                faultNumber.get(pid).remove(0);
            }

            if (faultNumber.get(pid).size() == oknoCzasowe2) {
                long errorsInPeriod = faultNumber.get(pid).stream().filter(b -> b).count();

                if (errorsInPeriod > maxErrors) {
                    if (freeFramesPool > 0) {
                        framesPerProcess.put(pid, framesPerProcess.get(pid) + 1);
                        freeFramesPool--;
                        faultNumber.get(pid).clear();
                    } else {
                        pausedProcesses.add(pid);
                        freeFramesPool += framesPerProcess.get(pid);
                        framesPerProcess.put(pid, 0);
                        localMemories.get(pid).clear();
                        faultNumber.get(pid).clear();
                    }
                }

                else if (errorsInPeriod < minErrors) {
                    int currentLimit = framesPerProcess.get(pid);
                    if (currentLimit > 1) {
                        framesPerProcess.put(pid, currentLimit - 1);
                        freeFramesPool++;
                        faultNumber.get(pid).clear();

                        if (localMemories.get(pid).size() > currentLimit - 1) {
                            localMemories.get(pid).remove(0);
                        }
                    }
                }
            }
        }

        System.out.println(alghoritmName + " - bledy strony: " + globalFaults +
                "    szamotania: " + thrashingCounter +
                "    wstrzymane procesy: " + pausedProcesses.size());
    }


    private void WSS(Map<Integer,Integer> framesPerProcess, Map<Integer, List<Integer>> localMemories, String alghoritmName) {
        int globalFaults = 0;
        int oknoCzasoweW = 50;
        int thrashingCounter = 0;

        int deltaT = 20;
        int c = 10;
        int timeToCalculate = 0;

        List<Boolean> recentHistory = new ArrayList<>();
        Set<Integer> pausedProcesses = new HashSet<>();

        Map<Integer, List<Integer>> processHistory = new HashMap<>();
        for (Integer pid : framesPerProcess.keySet()) {
            processHistory.put(pid, new ArrayList<>());
        }

        for (PageReference ref : globalSequence) {
            int pid = ref.processId();
            int page = ref.pageNumber();

            processHistory.get(pid).add(page);
            if (processHistory.get(pid).size() > deltaT) {
                processHistory.get(pid).remove(0);
            }

            if (pausedProcesses.contains(pid)) continue;

            List<Integer> processMemory = localMemories.get(pid);
            boolean wasFault = false;

            if (!processMemory.contains(page)) {
                wasFault = true;
                globalFaults++;

                if (processMemory.size() >= framesPerProcess.get(pid)) {
                    if (!processMemory.isEmpty()) processMemory.remove(0);
                }
                if (framesPerProcess.get(pid) > 0) {
                    processMemory.add(page);
                }
            } else {
                processMemory.remove(Integer.valueOf(page));
                processMemory.add(page);
            }


            recentHistory.add(wasFault);
            if (recentHistory.size() > oknoCzasoweW) recentHistory.remove(0);
            if (recentHistory.size() == oknoCzasoweW) {
                long errorsInWindow = recentHistory.stream().filter(b -> b).count();
                if (errorsInWindow > (oknoCzasoweW / 2)) {
                    thrashingCounter++;
                    recentHistory.clear();
                }
            }

            timeToCalculate++;

            if (timeToCalculate == c) {
                int totalD = 0;
                Map<Integer, Integer> currentWSS = new HashMap<>();

                for (Integer p : framesPerProcess.keySet()) {
                    if (pausedProcesses.contains(p)) continue;

                    Set<Integer> uniquePages = new HashSet<>(processHistory.get(p));
                    int wss = uniquePages.size();

                    wss = Math.max(1, wss);

                    currentWSS.put(p, wss);
                    totalD += wss;
                }


                while (totalD > totalFramesInSystem && !currentWSS.isEmpty()) {
                    int victimPid = -1;
                    int maxWss = -1;

                    for (Map.Entry<Integer, Integer> entry : currentWSS.entrySet()) {
                        if (entry.getValue() > maxWss) { //szukamy maksymalnego WSS do usuenicia
                            maxWss = entry.getValue();
                            victimPid = entry.getKey();
                        }
                    }

                    pausedProcesses.add(victimPid);
                    framesPerProcess.put(victimPid, 0);
                    localMemories.get(victimPid).clear();

                    totalD -= maxWss; //zmniejszamy totala o nasze wSS
                    currentWSS.remove(victimPid);
                }


                for (Map.Entry<Integer, Integer> entry : currentWSS.entrySet()) {
                    int pId = entry.getKey();
                    int assignedWSS = entry.getValue();
                    framesPerProcess.put(pId, assignedWSS);
                    while (localMemories.get(pId).size() > assignedWSS) {
                        localMemories.get(pId).remove(0);
                    }
                }


                timeToCalculate = 0;
            }
        }

        System.out.println(alghoritmName + " - bledy strony: " + globalFaults +
                "    szamotania: " + thrashingCounter +
                "    wstrzymane procesy: " + pausedProcesses.size());
    }
}