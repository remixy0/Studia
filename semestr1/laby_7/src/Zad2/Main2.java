package Zad2;

import java.util.*;

public class Main2 {
    public static void main(String[] args) {
    List<Book> books = new ArrayList<>();
    HashMap<String,ArrayList<Book>> bookByAuthor = new HashMap<>();

    books.add(new Book("Numbers Don't Lie", "Vaclav Smil", 2020, true));
    books.add(new Book("How the World Really Works", "Vaclav Smil", 2022,false));
    books.add(new Book("Energy and Civilization", "Vaclav Smil", 2017,false));
    books.add(new Book("Clean Code", "Robert C. Martin", 2008,true));
    books.add(new Book("Sapiens", "Yuval Noah Harari", 2011, false));
    books.add(new Book("The Pragmatic Programmer", "Andrew Hunt", 1999, true));
    books.add(new Book("Atomic Habits", "James Clear", 2018,false));



        books.forEach(book -> bookByAuthor.computeIfAbsent(book.author, k -> new ArrayList<>()).add(book));

        bookByAuthor.forEach((author, booksList) -> {
            System.out.println("Author: " + author);
            System.out.println("number of books: " + booksList.size());

        });

        bookByAuthor.forEach((author, booksList) -> {});


//        bookByAuthor.forEach((author, booksList) -> {
//            System.out.println("Author: " + author);
//            System.out.println("Books: ");
//            booksList.stream().forEach(System.out::println);
//        });


        HashMap.Entry<String, ArrayList<Book>> topAuthorEntry = bookByAuthor.entrySet().stream()
                .max(Comparator.comparingInt(entry -> entry.getValue().size()))
                .orElse(null);

        System.out.println("");

        if (topAuthorEntry != null) {
            System.out.println("Top Author: " + topAuthorEntry.getKey());
            System.out.println("Count: " + topAuthorEntry.getValue().size());
        }


        System.out.println("");
        System.out.println("Year filter:");
        int lata = 10;
        books.stream().filter(book -> book.year < 2025 - lata).forEach(System.out::println);

        System.out.println("");
        System.out.println("Borrowed books:");
        books.stream().filter(book -> book.borrowed == true).forEach(System.out::println);


        System.out.println("");
        System.out.println("Sorted books:");
    Collections.sort(books, Comparator.comparingInt(x -> x.year));
    System.out.println(books);


    }
}
