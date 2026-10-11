package org.example.Kolokwium.Zad1;

public class Tenisista {
    String imie;
    String nazwisko;
    String kraj;
    int wiek;
    int liczba_turniejów;
    int punkty;

    public Tenisista(String imie, String nazwisko, String kraj, int wiek, int liczba_turniejów, int punkty) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.kraj = kraj;
        this.wiek = wiek;
        this.liczba_turniejów = liczba_turniejów;
        this.punkty = punkty;
    }

    public int getPunkty() {
        return punkty;
    }

    public int getTurnieje() {
        return  liczba_turniejów;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getNazwiskoSort() {
        return nazwisko.toUpperCase();
    }

    @Override
    public String toString() {
        return imie +" "+ nazwisko +" "+ kraj +" "+ wiek +" "+ liczba_turniejów+ " " +punkty;
    }
}
