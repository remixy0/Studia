package org.example;

import java.util.*;

public class PageGenerator {
    private final int maxVirtualMemorySize;
    private final int maxSequenceLength;




    public PageGenerator(int maxVirtualMemorySize, int maxSequenceLength) {
        this.maxVirtualMemorySize = maxVirtualMemorySize;
        this.maxSequenceLength = maxSequenceLength;
    }

    public List<Process> generateProcesses(int amount) {
        List<Process> processes = new LinkedList<>();
        for (int i = 1; i <= amount; i++) {
            processes.add(new Process(i,generatePages(i)));
        }
        return processes;
    }

    private List<Integer> generatePages(int id) {
        List<Integer> pageReferences = new LinkedList<>();
        Random random = new Random();
        int memorySize = generateMemorySize();

        int lastPage = random.nextInt(maxVirtualMemorySize);

        for (int i = 0; i < generateLenght(); i++) {
            int offset = random.nextInt(9) - 4;
                int nextPage = lastPage + offset;

                if (nextPage < 0) nextPage = 0;
                if (nextPage >= memorySize) nextPage = memorySize - 1;

                pageReferences.add(nextPage);
                lastPage = nextPage;
        }
        return pageReferences;
    }



    private int generateLenght(){
        Random random = new Random();
        return random.nextInt(4,maxSequenceLength);
    }

    private int generateMemorySize(){
        Random random = new Random();
        return random.nextInt(1,maxVirtualMemorySize);
    }



    public List<PageReference> mixSequences(List<Process> processes) {
        List<PageReference> globalSequence = new ArrayList<>();
        Random random = new Random();

        int[] progressMap = new int[processes.size()];
        boolean hasMorePages = true;

        while (hasMorePages) {
            hasMorePages = false;

            for (int i = 0; i < processes.size(); i++) {
                Process process = processes.get(i);
                List<Integer> processPages = process.getCiagOdwolan();

                int quantum = random.nextInt(4) + 1;

                for (int q = 0; q < quantum; q++) {
                    if (progressMap[i] < processPages.size()) {
                        int pageNumber = processPages.get(progressMap[i]);

                        globalSequence.add(new PageReference(process.getId(), pageNumber));

                        progressMap[i]++;
                        hasMorePages = true;
                    }
                }
            }
        }

        return globalSequence;
    }







}