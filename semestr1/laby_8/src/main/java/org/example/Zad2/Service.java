package org.example.Zad2;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Service {
    static File file = null;
    static Scanner scanner = null;
    Repository repository;
    public Service(Repository repository) {
        this.repository = repository;
    }

    public int readFile(String fileName) throws IOException {
        file = new File(fileName);
        scanner = new Scanner(file);
        String path = "src/main/java/org/example/errors.txt";
        int errorCount = 0;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(line.contains("ERROR")) {
                errorCount++;
                line += "\n";
                Files.write(Paths.get(path), line.getBytes(), StandardOpenOption.APPEND);
            }
        }
        System.out.println("Errors were written to: " + path);
        return errorCount;
    }
}
