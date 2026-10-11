package org.example.Kolokwium.Zad2;

import java.util.ArrayList;
import java.util.List;

public class Student extends Osoba {
    List<Zaliczenie> listaZaliczen;
    public Student(String imie, String nazwa) {
        super(imie, nazwa);
        listaZaliczen = new ArrayList<Zaliczenie>();
        listaZaliczen.add(new Zaliczenie("Algebra", 5));
    }

    public void dodajZaliczenie(Zaliczenie zaliczenie){
        listaZaliczen.add(zaliczenie);
    }

    public void usunZaliczenie(Zaliczenie zaliczenie){
        listaZaliczen.remove(zaliczenie);
    }

    public double policzSredniaOcen(){
        double suma = listaZaliczen.stream().mapToDouble(Zaliczenie::getOcena).sum();
        return suma/listaZaliczen.size();
    }

    @Override
    public String toString() {
        return nazwisko + " " + imie;
    }
}
