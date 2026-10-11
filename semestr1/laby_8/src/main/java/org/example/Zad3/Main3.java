package org.example.Zad3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Main3 {
    public static void main(String[] args) throws IOException {
        String path = "src/main/java/org/example/raport.txt";
        List<Product> productList = new ArrayList();
        List<String> categories = new ArrayList();
        try {
            productList = ProductReader.readFile("src/main/java/org/example/products.csv");
        } catch (Exception e) {
            e.printStackTrace();
        }

        productList.stream().forEach(System.out::println);

        double totalAmount = productList.stream()
                    .mapToDouble(item -> item.getPrice())
                    .sum();

        String line = "Total amount: " + totalAmount + "\n";
        System.out.println(line);

        Files.write(Paths.get(path), line.getBytes(), StandardOpenOption.APPEND);

        productList.stream().forEach(x ->{
            if(!categories.contains(x.getCategory()))categories.add(x.getCategory());
        });
        System.out.println("Categories: " + categories);
        List<Product> finalProductList = productList;
        categories.stream().forEach(category -> {
            double priceSum  = finalProductList.stream()
                    .filter(x-> x.getCategory()
                    .equals(category))
                    .mapToDouble(x -> x.getPrice())
                    .sum();

            System.out.println("Total price for "+ category+ "  -> " + priceSum);
            String line2 = "total price for "+ category+ "  -> " + priceSum + "\n";
            try {
                Files.write(Paths.get(path), line2.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }
}