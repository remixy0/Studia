package org.example;

import java.math.BigInteger;
import java.util.Iterator;

public class NowyIterator implements Iterator<Integer> {
    private Iterator<Integer> iterator;
    public NowyIterator(Iterator<Integer> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Integer next() {
        return 0;
    }

}
