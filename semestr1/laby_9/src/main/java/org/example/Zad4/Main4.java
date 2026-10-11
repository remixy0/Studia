package org.example.Zad4;

public class Main4 {
    public static void main(String[] args) {
        try {
            AgeChecker.checkAge(17);
        } catch (UnderageException e){
            System.out.println(e.getMessage());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
