package Zad7;

import java.util.*;

public class Main7 {
    static List<Transaction> history = new ArrayList<>();
    static HashMap<String, ArrayList<Transaction>> groupedByUser =  new HashMap<>();
    public static void main(String[] args) {


        history.add(new Transaction("Marek",12.22, "euro"));
        history.add(new Transaction("Krzysztof",17.89, "$"));
        history.add(new Transaction("Szymon",44.21, "zl"));
        history.add(new Transaction("Szymon",22.76, "zl"));

        history.stream().forEach(x-> {
            if(groupedByUser.containsKey(x.user)){
                groupedByUser.get(x.user).add(x);
            }else{
                groupedByUser.put(x.user,new ArrayList<>());
                groupedByUser.get(x.user).add(x);
            }
        });

        System.out.println(groupedByUser.values().stream().max(Comparator.comparing(List::size)).stream().findFirst());

        groupedByUser.keySet().stream().forEach(x->{

            double totalAmount = groupedByUser.get(x).stream()
                    .mapToDouble(transaction -> transaction.amount)
                    .sum();
            System.out.println(x + " " + totalAmount);
        });


        biggestTransaction("euro");

        System.out.println("Sorted: ");
        history.stream().sorted(Comparator.comparing(x->x.timestamp)).forEach(System.out::println);

    }

    public static void biggestTransaction(String currency){
        System.out.println(history.stream()
                .filter(x-> x.currency == currency)
                .max(Comparator.comparing(x-> x.amount)));
    }

}
