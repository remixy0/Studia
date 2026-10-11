package org.example;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       wczytywaniePlikow();
    }

    static void kopia(String src, String dest) {

        try(BufferedReader br = new BufferedReader(new FileReader(src));
            BufferedWriter bw = new BufferedWriter(new FileWriter(dest))){

            String line;
            int wiersze = 0; int wyrazy = 0;


            while ((line = br.readLine()) != null) {
                bw.write(line);
                wiersze++;
                String [] line_wyrazy = line.split("\\s+");
                wyrazy += line_wyrazy.length;
            }
            System.out.println(wiersze);
            System.out.println(wyrazy);

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    static void kopia2(String src, String dest) {



        try(Scanner scanner = new Scanner(new FileReader(src));
            PrintWriter pw = new PrintWriter(new FileWriter(dest))){

            String line;
            String token;
            int wiersze = 0; int wyrazy = 0;


            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                pw.println(line);
                Scanner scanner2 = new Scanner(line);
                while (scanner2.hasNext()) {
                    token = scanner2.next();
                    wyrazy ++;
                }
                wiersze ++;
            }

            System.out.println(wiersze);
            System.out.println(wyrazy);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    static void wczytywaniePlikow(){
        try(BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/example/marek.txt"))){
            while(br.ready()){
                System.out.println(br.readLine());
            }

            try (Scanner scanner = new Scanner(new File("src/main/java/org/example/marek.txt"))) {
                while (scanner.hasNext()) {
                    if (scanner.hasNextInt()) {
                        int number = scanner.nextInt();
                        System.out.println("Liczba: " + number);
                    } else {
                        scanner.next(); // Pomiń to, co nie jest liczbą
                    }
                }
            }



        }catch (Exception e){
            e.printStackTrace();
        }
    }
}