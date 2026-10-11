package org.example.Zad1;

import java.util.Scanner;

public class MathSolver{

    public void enteringNumbers() throws NumberFormatException{
        int firstNumber;
        int secondNumber;
        Scanner sc = new Scanner(System.in);
        System.out.println("enter first number:");
        if(sc.hasNextInt()){
            firstNumber = sc.nextInt();
        }else throw new NumberFormatException();

        System.out.println("enter second number:");
        if(sc.hasNextInt()){
            secondNumber = sc.nextInt();
        }else throw new NumberFormatException();

        System.out.println(divide(firstNumber, secondNumber));
    }


    private double divide(double dividend, double divisor) throws ArithmeticException{
        if(divisor == 0)  throw new ArithmeticException("Division by zero");
        return dividend/divisor;
    }
}
