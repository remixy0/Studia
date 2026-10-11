package org.example;

import java.util.*;

public class Simulator {

    private final List<Integer> references;

    public Simulator(List<Integer> references) {
        this.references = references;
    }


    public int runFIFO(int frames) {
        int faults = 0;
        List<Integer> memory = new LinkedList<>();

        List<Boolean> recentHistory = new ArrayList<>();
        int thrashingCounter = 0;

        for (int page : references) {
            boolean wasFault = false;

            if (!memory.contains(page)) {
                wasFault = true;
                faults++;
                if (memory.size() >= frames) {
                    memory.remove(0);
                }
                memory.add(page);
            }

            recentHistory.add(wasFault);

            if (recentHistory.size() > 10) {
                recentHistory.remove(0);
            }

            if (recentHistory.size() == 10) {
                long errorsInWindow = recentHistory.stream().filter(b -> b).count();
                if (errorsInWindow > 7) {
                    thrashingCounter++;
                    recentHistory.clear();
                }
            }
        }


        System.out.println("FIFO Błędy strony: " + faults + " szamotania: " + thrashingCounter);
        return faults;
    }


    public int runOPT(int frames) {
        int faults = 0;
        List<Integer> memory = new ArrayList<>();
        List<Integer> references2 = new ArrayList<>(references);

        List<Boolean> recentHistory = new ArrayList<>();
        int thrashingCounter = 0;

        while (!references2.isEmpty()) {
            int page = references2.get(0);
            references2.remove(0);

            boolean wasFault = false;

            if (!memory.contains(page)) {
                wasFault = true;
                faults++;

                if (memory.size() >= frames) {
                    int longestNotUsed = memory.get(0);
                    int howLong = -1;

                    for (int j = 0; j < memory.size(); j++) {
                        int nextUseIndex = references2.indexOf(memory.get(j));

                        if(nextUseIndex == -1){
                            longestNotUsed = memory.get(j);
                            break;
                        }
                        if(nextUseIndex > howLong) {
                            longestNotUsed = memory.get(j);
                            howLong = nextUseIndex;
                        }
                    }

                    memory.remove(Integer.valueOf(longestNotUsed));
                }
                memory.add(page);
            }

            recentHistory.add(wasFault);

            if (recentHistory.size() > 10) {
                recentHistory.remove(0);
            }

            if (recentHistory.size() == 10) {
                long errorsInWindow = recentHistory.stream().filter(b -> b).count();
                if (errorsInWindow > 7) {
                    thrashingCounter++;
                    recentHistory.clear();
                }
            }
        }

        System.out.println("OPT Błędy strony: " + faults + " szamotania: " + thrashingCounter);
        return faults;
    }


    public int runLRU(int frames) {
        int faults = 0;
        List<Integer> memory = new ArrayList<>();

        List<Boolean> recentHistory = new ArrayList<>();
        int thrashingCounter = 0;

        for (int page : references) {
            boolean wasFault = false;

            if (!memory.contains(page)) {
                wasFault = true;
                faults++;
                if (memory.size() == frames) {
                    memory.remove(0);
                }
                memory.add(page);
            } else {
                memory.remove(Integer.valueOf(page));
                memory.add(page);
            }


            recentHistory.add(wasFault);

            if (recentHistory.size() > 10) {
                recentHistory.remove(0);
            }

            if (recentHistory.size() == 10) {
                long errorsInWindow = recentHistory.stream().filter(b -> b).count();
                if (errorsInWindow > 7) {
                    thrashingCounter++;
                    recentHistory.clear();
                }
            }
        }

        System.out.println("LRU Błędy strony: " + faults + " szamotania: " + thrashingCounter);
        return faults;
    }


    public int runALRU(int frames) {
        int faults = 0;
        List<Integer> memory = new ArrayList<>();
        List<Boolean> chance = new ArrayList<>();
        int pointer = 0;

        List<Boolean> recentHistory = new ArrayList<>();
        int thrashingCounter = 0;

        for (int page : references) {
            boolean wasFault = false;

            if (memory.contains(page)) {
                int index = memory.indexOf(page);
                chance.set(index, true);
            }
            else {
                wasFault = true;
                faults++;

                if (memory.size() < frames) {
                    memory.add(page);
                    chance.add(true);
                }
                else {
                    while (true) {
                        if (chance.get(pointer)) {
                            chance.set(pointer, false);
                            pointer = (pointer + 1) % frames;
                        } else {
                            memory.set(pointer, page);
                            chance.set(pointer, true);
                            pointer = (pointer + 1) % frames;
                            break;
                        }
                    }
                }
            }

            recentHistory.add(wasFault);

            if (recentHistory.size() > 10) {
                recentHistory.remove(0);
            }

            if (recentHistory.size() == 10) {
                long errorsInWindow = recentHistory.stream().filter(b -> b).count();
                if (errorsInWindow > 7) {
                    thrashingCounter++;
                    recentHistory.clear();
                }
            }
        }

        System.out.println("ALRU Błędy strony: " + faults + "  szamotania: " + thrashingCounter);
        return faults;
    }


    public int runRAND(int frames) {
        int faults = 0;
        List<Integer> memory = new ArrayList<>();
        Random rand = new Random();

        List<Boolean> recentHistory = new ArrayList<>();
        int thrashingCounter = 0;

        for (int page : references) {
            boolean wasFault = false;

            if (!memory.contains(page)) {
                wasFault = true;
                faults++;
                if (memory.size() == frames) {
                    int evictIndex = rand.nextInt(frames);
                    memory.remove(evictIndex);
                }
                memory.add(page);
            }

            recentHistory.add(wasFault);

            if (recentHistory.size() > 10) {
                recentHistory.remove(0);
            }

            if (recentHistory.size() == 10) {
                long errorsInWindow = recentHistory.stream().filter(b -> b).count();
                if (errorsInWindow > 7) {
                    thrashingCounter++;
                    recentHistory.clear();
                }
            }
        }

        System.out.println("RAND  Błędy strony: " + faults + " szamotania: " + thrashingCounter);
        return faults;
    }
}