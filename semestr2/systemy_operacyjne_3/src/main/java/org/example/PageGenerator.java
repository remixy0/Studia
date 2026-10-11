package org.example;

import java.util.*;

public class PageGenerator {
    private final int virtualMemorySize;
    private final int sequenceLength;
    private final List<Integer> localityWeights;

    private final List<Integer> pageReferences = new LinkedList<>();

    public PageGenerator(int virtualMemorySize, int sequenceLength, List<Integer> localityWeights) {
        this.virtualMemorySize = virtualMemorySize;
        this.sequenceLength = sequenceLength;
        this.localityWeights = localityWeights;
    }

    public List<Integer> generatePages() {
        Random random = new Random();
        int lastPage = -1;

        for (int i = 0; i < sequenceLength; i++) {
            if (lastPage != -1 && random.nextDouble() < 0.75) {

                int offset = random.nextInt(4) - 2;
                int nextPage = lastPage + offset;

                if (nextPage < 0) nextPage = 0;
                if (nextPage >= virtualMemorySize) nextPage = virtualMemorySize - 1;

                pageReferences.add(nextPage);
                lastPage = nextPage;
            } else {
                int newPage = calculatePageNumberFromSectors(random);
                pageReferences.add(newPage);
                lastPage = newPage;
            }
        }
        return pageReferences;
    }


    private int calculatePageNumberFromSectors(Random random) {
        int sumOfWeights = this.localityWeights.stream()
                .mapToInt(Integer::intValue)
                .sum();

        int sectorSize = virtualMemorySize / this.localityWeights.size();
        int los = random.nextInt(sumOfWeights);

        for (int i = 0; i < this.localityWeights.size(); i++) {
            los -= this.localityWeights.get(i);
            if (los < 0) {
                return random.nextInt(i * sectorSize, (i + 1) * sectorSize);
            }
        }
        return random.nextInt(virtualMemorySize);
    }
}