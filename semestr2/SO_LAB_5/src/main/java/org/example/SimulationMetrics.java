package org.example;

import java.util.ArrayList;
import java.util.List;

public class SimulationMetrics {
    public long totalQueries = 0;
    public long totalMigrations = 0;

    public List<double[]> loadHistory = new ArrayList<>();

    public void recordSnapshot(Processor[] processors) {
        double[] snapshot = new double[processors.length];
        for (int i = 0; i < processors.length; i++) {
            snapshot[i] = processors[i].getCurrentLoad();
        }
        loadHistory.add(snapshot);
    }

    public double getAverageLoad() {
        double sum = 0;
        long count = 0;
        for (double[] snapshot : loadHistory) {
            for (double load : snapshot) {
                sum += load;
                count++;
            }
        }
        return count == 0 ? 0 : sum / count;
    }

    public double getAverageDeviation(double globalAverage) {
        double sumOfDeviations = 0;
        long count = 0;
        for (double[] snapshot : loadHistory) {
            for (double load : snapshot) {
                sumOfDeviations += Math.abs(load - globalAverage);
                count++;
            }
        }
        return count == 0 ? 0 : sumOfDeviations / count;
    }
}