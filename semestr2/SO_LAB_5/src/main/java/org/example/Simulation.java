package org.example;

import java.util.*;

public class Simulation {

    private static final int SIMULATION_TICKS = 10000;

    public static void main(String[] args) {
        int N = 60;       // Liczba procesorów
        double p = 70.0;  // Próg max (%)
        double r = 20.0;  // Próg min  (%)
        int z = 5;        // max liczba prób

        List<TaskArrivalEvent> generatedEvents = generateWorkload(N, SIMULATION_TICKS);

        Map<Integer, List<TaskArrivalEvent>> eventsByTick = new HashMap<>();
        for (TaskArrivalEvent e : generatedEvents) {
            eventsByTick.computeIfAbsent(e.tick, k -> new ArrayList<>()).add(e);
        }


        SimulationMetrics m1 = runStrategy1(N, p, z, eventsByTick);
        SimulationMetrics m2 = runStrategy2(N, p, eventsByTick);
        SimulationMetrics m3 = runStrategy3(N, p, r, eventsByTick);


        printResultsTable(m1, m2, m3);
    }

    private static List<TaskArrivalEvent> generateWorkload(int N, int ticks) {
        List<TaskArrivalEvent> events = new ArrayList<>();
        Random rand = new Random(457);

        double[] processorFrequencies = new double[N];
        for (int i = 0; i < N; i++) {
            processorFrequencies[i] = 0.05 + (rand.nextDouble() * 0.40);
        }

        for (int tick = 0; tick < ticks; tick++) {
            for (int i = 0; i < N; i++) {
                if (rand.nextDouble() < processorFrequencies[i]) {
                    double taskLoad = 1.0 + (rand.nextDouble() * 5.0);
                    int duration = 20 + rand.nextInt(81);
                    events.add(new TaskArrivalEvent(tick, i, taskLoad, duration));
                }
            }
        }
        return events;
    }

    // STRATEGIA 1
    private static SimulationMetrics runStrategy1(int N, double p, int z, Map<Integer, List<TaskArrivalEvent>> eventsByTick) {
        Processor[] processors = createProcessors(N);
        SimulationMetrics metrics = new SimulationMetrics();
        Random rand = new Random(1);

        for (int tick = 0; tick < SIMULATION_TICKS; tick++) {
            for (Processor proc : processors) proc.updateTasks();

            List<TaskArrivalEvent> arrivals = eventsByTick.getOrDefault(tick, Collections.emptyList());
            for (TaskArrivalEvent event : arrivals) {
                int x = event.targetProcessorId;
                Task task = new Task(event.load, event.duration);
                boolean migrated = false;

                for (int attempt = 0; attempt < z; attempt++) {
                    int y = rand.nextInt(N);
                    if (y == x) continue;

                    metrics.totalQueries++;
                    if (processors[y].getCurrentLoad() < p) {
                        processors[y].addTask(task);
                        metrics.totalMigrations++;
                        migrated = true;
                        break;
                    }
                }

                if (!migrated) {
                    processors[x].addTask(task);
                }
            }
            metrics.recordSnapshot(processors);
        }
        return metrics;
    }

    // STRATEGIA 2
    private static SimulationMetrics runStrategy2(int N, double p, Map<Integer, List<TaskArrivalEvent>> eventsByTick) {
        Processor[] processors = createProcessors(N);
        SimulationMetrics metrics = new SimulationMetrics();
        Random rand = new Random(2);

        for (int tick = 0; tick < SIMULATION_TICKS; tick++) {
            for (Processor proc : processors) proc.updateTasks();

            List<TaskArrivalEvent> arrivals = eventsByTick.getOrDefault(tick, Collections.emptyList());
            for (TaskArrivalEvent event : arrivals) {
                int x = event.targetProcessorId;
                Task task = new Task(event.load, event.duration);

                if (processors[x].getCurrentLoad() > p) {
                    boolean migrated = false;
                    int maxAttempts = N * 2;
                    int attempts = 0;

                    while (attempts < maxAttempts) {
                        int y = rand.nextInt(N);
                        if (y == x) continue;

                        metrics.totalQueries++;
                        if (processors[y].getCurrentLoad() < p) {
                            processors[y].addTask(task);
                            metrics.totalMigrations++;
                            migrated = true;
                            break;
                        }
                        attempts++;
                    }

                    if (!migrated) {
                        processors[x].addTask(task);
                    }
                } else {
                    processors[x].addTask(task);
                }
            }
            metrics.recordSnapshot(processors);
        }
        return metrics;
    }

    // STRATEGIA 3
    private static SimulationMetrics runStrategy3(int N, double p, double r, Map<Integer, List<TaskArrivalEvent>> eventsByTick) {
        Processor[] processors = createProcessors(N);
        SimulationMetrics metrics = new SimulationMetrics();
        Random rand = new Random(3);

        for (int tick = 0; tick < SIMULATION_TICKS; tick++) {
            for (Processor proc : processors) proc.updateTasks();

            List<TaskArrivalEvent> arrivals = eventsByTick.getOrDefault(tick, Collections.emptyList());

            for (TaskArrivalEvent event : arrivals) {
                int x = event.targetProcessorId;
                Task task = new Task(event.load, event.duration);

                if (processors[x].getCurrentLoad() > p) {
                    boolean migrated = false;
                    int attempts = 0;
                    while (attempts < N * 2) {
                        int y = rand.nextInt(N);
                        if (y == x) continue;

                        metrics.totalQueries++;
                        if (processors[y].getCurrentLoad() < p) {
                            processors[y].addTask(task);
                            metrics.totalMigrations++;
                            migrated = true;
                            break;
                        }
                        attempts++;
                    }
                    if (!migrated) processors[x].addTask(task);
                } else {
                    processors[x].addTask(task);
                }
            }

            for (int i = 0; i < N; i++) {
                if (processors[i].getCurrentLoad() < r) {
                    int y = rand.nextInt(N);
                    if (y == i) continue;

                    metrics.totalQueries++;
                    if (processors[y].getCurrentLoad() > p) {
                        List<Task> yTasks = processors[y].activeTasks;
                        for (int t = yTasks.size() - 1; t >= 0; t--) {
                            Task candidate = yTasks.get(t);

                            yTasks.remove(t);
                            processors[i].addTask(candidate);
                            metrics.totalMigrations++;

                            if (processors[i].getCurrentLoad() >= r || processors[y].getCurrentLoad() <= p) {
                                break;
                            }
                        }
                    }
                }
            }
            metrics.recordSnapshot(processors);
        }
        return metrics;
    }

    private static Processor[] createProcessors(int N) {
        Processor[] processors = new Processor[N];
        for (int i = 0; i < N; i++) {
            processors[i] = new Processor(i);
        }
        return processors;
    }

    private static void printResultsTable(SimulationMetrics m1, SimulationMetrics m2, SimulationMetrics m3) {
        double avg1 = m1.getAverageLoad();
        double avg2 = m2.getAverageLoad();
        double avg3 = m3.getAverageLoad();



        System.out.println("strategia A. srednie obciążenie B. odchylenie avg C1. zapytania C2. migracje");

        System.out.printf("%-15s | %-21.2f%% | %-21.2f%% | %-12d | %-12d\n",
                "Strategia 1", avg1, m1.getAverageDeviation(avg1), m1.totalQueries, m1.totalMigrations);

        System.out.printf("%-15s | %-21.2f%% | %-21.2f%% | %-12d | %-12d\n",
                "Strategia 2", avg2, m2.getAverageDeviation(avg2), m2.totalQueries, m2.totalMigrations);

        System.out.printf("%-15s | %-21.2f%% | %-21.2f%% | %-12d | %-12d\n",
                "Strategia 3", avg3, m3.getAverageDeviation(avg3), m3.totalQueries, m3.totalMigrations);

    }
}