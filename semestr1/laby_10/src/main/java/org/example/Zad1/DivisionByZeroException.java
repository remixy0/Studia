package org.example.Zad1;

public class DivisionByZeroException extends RuntimeException {
    public DivisionByZeroException() {
        super("You are trying to divide by zero");
    }
}
