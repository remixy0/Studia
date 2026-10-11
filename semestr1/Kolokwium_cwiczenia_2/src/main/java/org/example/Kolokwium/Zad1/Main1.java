package org.example.Kolokwium.Zad1;

import java.io.FileNotFoundException;

public class Main1 {
    public static void main(String[] args) throws FileNotFoundException {
        ObslugaTenisa.wczytajDane();
        ObslugaTenisa.zapiszPosortowane();
        ObslugaTenisa.wyswietlPosortowaneNazwiskiem();
        ObslugaTenisa.wyswietlPosortowaneRankingiem();
        ObslugaTenisa.wyswietlSredniaLiczbeTurnieji();
    }
}
