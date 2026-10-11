package org.example.Kolokwium.Zad3;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Czytanie {
    static LinkedList<String> wyrazy = new LinkedList<>();
    static LinkedList<String> wyrazy2 = new LinkedList<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int iloscWyrazow = 0;
        int iloscZnakow = 0;
        while(true) {
            String wyraz = sc.next();

            if(wyraz.equals("end")) {
                break;
            }
            iloscWyrazow++;
            iloscZnakow += wyraz.length();
            wyrazy.addFirst(wyraz);
            String wyrazOdwrocony = "";
            for(int i = wyraz.length() - 1; i > -1 ; i--) {
                wyrazOdwrocony += wyraz.charAt(i);
            }
            wyrazy2.addFirst(wyrazOdwrocony);


        }

        System.out.println("Odwrocona kolejnosc wyrazow:");
        wyrazy.stream().forEach(x->{
            System.out.print(x + " ");
        });

        System.out.println("");
        System.out.println("Odwrocona kolejnosc znakow:");
        wyrazy2.stream().forEach(x->{
            System.out.print(x + " ");
        });

        System.out.println("");
        System.out.println("ilosc wyrazow: " + iloscWyrazow);
        System.out.println("ilosc znakow: " + iloscZnakow);



    }
}
