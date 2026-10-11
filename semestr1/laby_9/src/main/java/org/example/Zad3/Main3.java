package org.example.Zad3;

public class Main3 {
    public static void main(String[] args) {
        try {
            System.out.println(AgeCalculator.calculateAge(2025));
        }catch(IllegalArgumentException e){
            System.out.println("Invalid input");
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
