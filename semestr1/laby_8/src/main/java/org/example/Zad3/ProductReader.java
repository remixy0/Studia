package org.example.Zad3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductReader {
    static File file = null;
    static Scanner scanner = null;

    public static List readFile(String fileName) throws IOException {
        List<Product> productList = new ArrayList();
        file = new File(fileName);
        scanner = new Scanner(file);
        String line = scanner.nextLine();
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            String[] lineSplit =  line.split(";");
            productList.add(new Product(lineSplit[0],lineSplit[1],lineSplit[2],Double.valueOf(lineSplit[3])));

        }
        return productList;
    }

}
