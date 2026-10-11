package org.example.Zad1;

public class Main1 {
    public static void main(String[] args) {
        if(Calculator.divide(4,2) == 2) System.out.println("Test passed");
        else System.out.println("Test failed");

        try{
            Calculator.divide(4,0);
        }catch(ArithmeticException e){
            System.out.println("Exception test passed");
        }
    }
}
