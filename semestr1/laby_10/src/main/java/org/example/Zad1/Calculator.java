package org.example.Zad1;

public class Calculator {
    public static double divide(double dividend, double divisor) throws DivisionByZeroException{
        if(divisor == 0) throw new DivisionByZeroException();
        else{
            return dividend/divisor;
        }


    }
}
