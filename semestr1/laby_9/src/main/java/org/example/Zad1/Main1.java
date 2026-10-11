package org.example.Zad1;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
       MathSolver mathsolver = new MathSolver();
       try {
           mathsolver.enteringNumbers();
       }catch (ArithmeticException e) {
           System.out.println("You are trying to divide by zero");
       }
       catch (NumberFormatException e) {
           System.out.println("You are entering an invalid number");
       }
       catch (Exception e) {
           System.out.println("Something went wrong");
       }

    }
}
