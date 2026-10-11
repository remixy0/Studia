package Zad9;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Main9 {
    static ArrayList<Integer> numbers = new ArrayList<>();
    static Random rand = new Random();
    public static void main(String[] args) {
        for(int i = 0; i < 15; i++){
            numbers.add(rand.nextInt(101));
        }

        System.out.println("Wsyztskie numery ");
        numbers.stream().forEach(System.out::println);

        System.out.println("Parzyste numery ");
        numbers.stream().filter(x -> x%2 == 0).forEach(System.out::println);
        System.out.println("wieksze niz 50");
        System.out.println(numbers.stream().filter(x->x > 50).count());
        System.out.println("Srednia ");
        System.out.println(numbers.stream().mapToDouble(x->x).sum() / numbers.stream().count());

        System.out.println(numbers.stream().max(Comparator.comparing(Integer::intValue)));

        ArrayList<Integer> numbers2 = new ArrayList<>();
        numbers.stream().forEach(x->numbers2.add(x*2));
        System.out.println(numbers2);


    }
}
