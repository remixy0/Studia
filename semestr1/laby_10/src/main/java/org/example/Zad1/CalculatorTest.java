package org.example.Zad1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class CalculatorTest {

    @Test
    public void dodawaniePowinnoZwrocicSume() {
        int a = 4;
        int b = 2;

        double wynik = Calculator.divide(a,b);

        Throwable exception = assertThrows(DivisionByZeroException.class, () -> {
            Calculator.divide(10, 0);
        });

        System.out.println("Exception message: " + exception.getMessage());


        assertEquals(2, wynik, 2);

    }

}
