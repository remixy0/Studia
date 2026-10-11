package org.example.Zad1;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class UI {
    Service service;
    Scanner scanner = new Scanner(System.in);
    public UI(Service service) {
        this.service = service;
    }

    public void readFileFromInput() throws FileNotFoundException {
        System.out.println("Select input / leave blank to open deafult:");
        String input = scanner.nextLine();
        if (!input.equals("")) {
            service.readFile(input);
        }else{
            service.readFile("src/main/java/org/example/test.txt");
        }

    }


//    public boolean checkInput(Scanner scanner){
//        return scanner.hasNextLine();
//
//    }




}
