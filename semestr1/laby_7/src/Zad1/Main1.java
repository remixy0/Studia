package Zad1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main1 {
    public static void main(String[] args) {
        List<Product> products = new ArrayList<>();
        products.add(new Product("Milk", 4.99, category.DIARY, false));
        products.add(new Product("Bread", 2.99, category.PASTRY, false));
        products.add(new Product("Butter", 8.50, category.DIARY, true));
        products.add(new Product("Ham", 40.99, category.MEAT, false));
        products.add(new Product("Orange Juice", 6.20, category.DRINKS, false));
        products.add(new Product("Chocolate", 5.50, category.SWEETS, true));
        products.add(new Product("Croissant", 3.99, category.PASTRY, true));
        products.add(new Product("Cheese", 14.99, category.DIARY, false));
        products.add(new Product("Sausage", 18.50, category.MEAT, false));
        products.add(new Product("Water", 1.99, category.DRINKS, false));
        products.add(new Product("Donut", 2.50, category.PASTRY, false));
        products.add(new Product("Lollipop", 1.20, category.SWEETS, true));


        products.stream().forEach(System.out::println);

        System.out.println("--------------");
        products.stream().filter(x -> x.category == category.DIARY).filter(x -> x.price < 5).forEach(System.out::println);

        System.out.println("--------------");
        products.stream().filter(x-> x.price > 40).forEach(x -> x.setDiscount(true));

        System.out.println("--------------");
        products.removeIf(x -> x.name.equals("Bread"));

        products.stream().sorted(Comparator.comparing(p -> p.price)).forEach(System.out::println);







    }
}


enum category{
    MEAT, DIARY, PASTRY, DRINKS, SWEETS
}
