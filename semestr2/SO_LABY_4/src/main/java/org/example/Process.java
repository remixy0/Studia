package org.example;

import java.util.List;

public class Process {
    List<Integer> ciagOdwolan;
    int id;

    public Process(int id, List<Integer> ciagOdwolan) {
        this.id = id;
        this.ciagOdwolan = ciagOdwolan;
    }

    public int getId() {
        return id;
    }
    public List<Integer> getCiagOdwolan() {
        return ciagOdwolan;
    }

    @Override
    public String toString() {
        return ciagOdwolan.toString() + "\n";
    }
}
