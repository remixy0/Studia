package Zad1;

import java.util.Random;

public class Symulacja {
    static Random random = new Random();
    public static int rzut(int liczba){
        int sum = 0;
        int licznik = 0;
        if(liczba > 12 || liczba < 2){
            System.out.println("Liczba niepoprawna");
        }else{
            while(sum != liczba){
                licznik++;
                sum = random.nextInt(1,7) + random.nextInt(1,7);
            }
            return licznik;
        }
        return -1;
    }

    public static void rzut2(){
        int[] tablicaWynikow = new int[13];
        int sum = 0;
        int licznik = 0;
        for(int i = 2; i <= 12; i++) {
            int maxLicznik = 0;
            for (int j = 0; j < 10000; j++) {
                licznik++;
                sum = random.nextInt(1, 7) + random.nextInt(1, 7);
                if(sum == i){
                    if(licznik > maxLicznik){
                        maxLicznik = licznik;
                    }
                    licznik = 0;
                }
            }
            tablicaWynikow[i] = maxLicznik;
        }

        System.out.println("Suma wyników   maksymalna ilosc rzutów ");
        for(int i = 2; i <= 12; i++){
            System.out.println(i + "            " +tablicaWynikow[i]);
        }
    }

}
