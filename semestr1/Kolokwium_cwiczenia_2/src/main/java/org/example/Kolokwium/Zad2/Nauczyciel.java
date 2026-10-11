package org.example.Kolokwium.Zad2;

import java.util.ArrayList;
import java.util.List;

public class Nauczyciel extends Osoba {
    List<Zaliczenie> listaKursow;
    public Nauczyciel(String imie, String nazwa) {
        super(imie, nazwa);
        listaKursow = new ArrayList<Zaliczenie>();
    }

    public void dodajKurs(Zaliczenie z){
        listaKursow.add(z);
    }

    public void usunKurs(Zaliczenie z){
        listaKursow.remove(z);
    }

    @Override
    public String toString(){
        return nazwisko + " " + imie;
    }
}
