package org.example.Kolokwium.Zad2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Main2 {
    static List<Osoba> listaOsob = new ArrayList<Osoba>();
    public static void main(String[] args) {
        listaOsob.add(new Nauczyciel("Marek","Muzyka"));
        listaOsob.add(new Student("Damian","Markiewicz"));
        listaOsob.add(new Nauczyciel("Kacper","Berk"));
        Student student = new Student("Marian","Sikora");
        student.dodajZaliczenie(new Zaliczenie("Analiza", 3));
        listaOsob.add(student);
        listaOsob.add(new Nauczyciel("Krystian","Sperling"));

        System.out.println("Studenci:");
        for(Osoba osoba : listaOsob){
            if(osoba instanceof Student){
                System.out.println(osoba);
            }
        }
        System.out.println("");
        System.out.println("Nauczyciele:");
        listaOsob.stream().filter(o -> o instanceof Nauczyciel).forEach(System.out::println);

        System.out.println("");
        System.out.println("Srednia ocen alfabetycznie:");
        listaOsob.stream().filter(x-> x instanceof Student).sorted(Comparator.comparing(Osoba::getNazwisko)).forEach(x->{
            System.out.println(x.toString() + " " + ((Student) x).policzSredniaOcen());
        });
        System.out.println("");
        System.out.println("Srednia ocen:");
        Iterator<Osoba> iterator = listaOsob.iterator();
        double suma = 0;
        int iloscStudentow = 0;
        while (iterator.hasNext()){
            Osoba osoba = iterator.next();
            if(osoba instanceof Student){
                iloscStudentow++;
                suma += ((Student) osoba).policzSredniaOcen();
            }
        }
        System.out.println(suma/iloscStudentow);



    }
}
