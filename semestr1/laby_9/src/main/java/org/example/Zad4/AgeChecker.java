package org.example.Zad4;

public class AgeChecker {
    public static void checkAge(int age) throws UnderageException {
        if (age < 18) throw new UnderageException();
        else System.out.println("Old enough");
    }
}
