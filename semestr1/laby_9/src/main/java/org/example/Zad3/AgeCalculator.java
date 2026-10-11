package org.example.Zad3;

public class AgeCalculator {
    static int calculateAge(int birthYear) throws IllegalArgumentException {
        if(birthYear < 1900 || birthYear > 2026) throw new IllegalArgumentException();
        return  2026 - birthYear;
    }
}
