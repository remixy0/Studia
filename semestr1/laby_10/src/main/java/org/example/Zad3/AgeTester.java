package org.example.Zad3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AgeTester {
    @Test
    public void testAge() {
        assertEquals(false, isAdult(17));
        assertEquals(true, isAdult(20));

    }

    boolean isAdult(int age) {
        return age >= 18;

    }
}
