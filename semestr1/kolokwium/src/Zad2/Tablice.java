package Zad2;

import java.util.Random;

public class Tablice {
     float[][] tablica;
     Random random = new Random();
     public Tablice(int n, int m){
         tablica = new float[n][m];
     }

     public void uzupelnijTablice(int z){
         for(int i = 0; i < tablica.length; i++){
             for(int j = 0; j < tablica[i].length; j++){
                 tablica[i][j] = (float)(random.nextFloat(0,z));
             }
         }
     }

     public void wyswietlTablice(){
         for(int i = 0; i < tablica.length; i++){
             for(int j = 0; j < tablica[i].length; j++){
                 System.out.printf("%.2f", tablica[i][j]);
                 System.out.print(" ");
             }
             System.out.println("");
         }
     }

     public void normalizacjaTablicy(){
         for(int i = 0; i < tablica.length; i++){
             float sum = 0;
             for(int j = 0; j < tablica[i].length; j++){
                 sum += tablica[i][j];
             }
             float srednia = sum / tablica[i].length;
             for(int j = 0; j < tablica[i].length; j++){
                 Math.pow(tablica[i][j] - srednia,2);
             }
         }
     }



}
