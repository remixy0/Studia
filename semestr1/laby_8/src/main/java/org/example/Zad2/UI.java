package org.example.Zad2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class UI {
    Service service;
    public UI(Service service) {
        this.service = service;
    }

    public void checkErrors() throws IOException {
        System.out.println("There are: " + service.readFile("src/main/java/org/example/app.log")+" errors");
    }



}
