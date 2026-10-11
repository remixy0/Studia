package org.example.Kolokwium.Zad1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class ObslugaTenisa {
    static List<Tenisista> listaTenisistow = new ArrayList<Tenisista>();

    public static void wczytajDane() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("src/main/java/org/example/kolokwium/ATPranking.txt"));
        while (sc.hasNext()) {
            String id = sc.next();
            String imie = sc.next();
            String nazwisko = sc.next();
            String nazwisko2 = "";
            String kraj = sc.next();
            while(!sc.hasNextInt()){
                if(sc.hasNextInt()){
                    nazwisko += sc.next();
                }else{
                    kraj = sc.next();
                }
            }
            int wiek = sc.nextInt();
            int liczba_turniejow = sc.nextInt();
            int punkty = sc.nextInt();
            Tenisista tenisista = new Tenisista(imie,nazwisko + " " + nazwisko2,kraj,wiek,liczba_turniejow,punkty);
            listaTenisistow.add(tenisista);
        }
    }

    public static List<Tenisista> getTenisistow() {
        return listaTenisistow;
    }

    public static void zapiszPosortowane() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("src/main/java/org/example/kolokwium/Zad1/PosortowaneRankingiem.txt");
        AtomicInteger i = new AtomicInteger();
        listaTenisistow.stream().sorted(Comparator.comparingInt(Tenisista::getPunkty).reversed()).forEach(x-> {
            i.addAndGet(1);
            pw.println(i + " " + x.toString());
        });
        pw.close();
    }

    public static void wyswietlPosortowaneRankingiem() throws FileNotFoundException {
        AtomicInteger i = new AtomicInteger();
        listaTenisistow.stream().sorted(Comparator.comparingInt(Tenisista::getPunkty).reversed()).forEach(x-> {
            i.addAndGet(1);
            System.out.println(i + " " + x.toString());
        });
    }

    public static void wyswietlPosortowaneNazwiskiem(){
        listaTenisistow.stream().sorted(Comparator.comparing(Tenisista::getNazwiskoSort)).forEach(x->{
            System.out.print(x.getNazwisko());
            System.out.print(" ");
            System.out.print(x.imie);
            System.out.print(" ");
            System.out.print(x.wiek);
            System.out.print(" ");
            System.out.print(x.punkty);
            System.out.println(" ");


        });

    }

    public static void wyswietlSredniaLiczbeTurnieji(){
        double suma = listaTenisistow.stream().mapToDouble(Tenisista::getTurnieje).sum();
        System.out.println("Srednia ilosc turnieji " +suma/listaTenisistow.size());
    }

}
