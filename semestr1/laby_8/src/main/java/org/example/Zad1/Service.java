package org.example.Zad1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Service {
    static File file = null;
    static Scanner scanner = null;

    Repository repository;
    public Service(Repository repository) {
        this.repository = repository;
    }

    public void readFile(String fileName) throws FileNotFoundException {
        file = new File(fileName);
        scanner = new Scanner(file);
        int lenght = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(!line.isEmpty()){
                lenght  += 1;
                System.out.println(lenght + ": " + line);
            }
        }
        System.out.println(lenght);
    }





}
