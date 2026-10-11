package org.example.Zad5;

import org.example.Zad4.UnderageException;

public class Zad5Class {
    static void methodA(int age) throws UnderageException {
        if (age < 18) methodB();
        else{
            System.out.println("Congratulations you are an adult!");
        }


    }

    static void methodB() throws UnderageException {
        System.out.println("You are a child!");
        throw new UnderageException();
    }
}
