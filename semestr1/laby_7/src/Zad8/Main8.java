package Zad8;

import java.util.*;

public class Main8 {
    public static void main(String[] args) {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        ArrayList<User>  users = new ArrayList<User>();
        HashMap<String, ArrayList<Movie>> moviesByGenre = new HashMap<String, ArrayList<Movie>>();
        HashMap<User, ArrayList<Movie>> moviesByUser = new HashMap<User, ArrayList<Movie>>();

        Random rand = new Random();

        movies.add(new Movie("movie 1","animated",6.8));
        movies.add(new Movie("movie 2","fairytale",9.2));
        movies.add(new Movie("movie 3","animated",3.4));
        movies.add(new Movie("movie 4","documentary",9.9));
        movies.add(new Movie("movie 5", "sci-fi", 8.5));
        movies.add(new Movie("movie 6", "action", 5.2));
        movies.add(new Movie("movie 7", "animated", 8.9));
        movies.add(new Movie("movie 8", "thriller", 7.4));
        movies.add(new Movie("movie 9", "sci-fi", 9.1));
        movies.add(new Movie("movie 10", "drama", 6.3));
        movies.add(new Movie("movie 11", "action", 9.5));
        movies.add(new Movie("movie 12", "documentary", 7.8));


        User user1 = new User("Marek");
        User user2 = new User("Stefan");
        User user3 = new User("Juan");

        users.add(user1);
        users.add(user2);
        users.add(user3);

        user1.addGenre(movies.get(0));
        user1.addGenre(movies.get(6));
        user1.addGenre(movies.get(4));
        user1.addGenre(movies.get(8));

        user2.addGenre(movies.get(1));
        user2.addGenre(movies.get(4));
        user2.addGenre(movies.get(9));
        user2.addGenre(movies.get(2));

        user3.addGenre(movies.get(5));
        user3.addGenre(movies.get(7));




        movies.stream().forEach(x->{
            if(moviesByGenre.containsKey(x.genre)){
                moviesByGenre.get(x.genre).add(x);
            }else{
                moviesByGenre.put(x.genre,new ArrayList<>());
                moviesByGenre.get(x.genre).add(x);
            }
        });
        System.out.println(moviesByGenre);


        for(User user:users){
            List<Movie> userMovies = user.getWatchedGenres();
            String genre = userMovies.get(rand.nextInt(userMovies.size())).genre;
            System.out.println("Recomendation for "+ user.name +" "+ genre);
            movies.stream().filter(x->x.genre.equals(genre)).forEach(x-> user.addToQueue(x));
            System.out.println("Added movies from that genre to your watchlist");
            System.out.println(user.getQueue());

        }




    }
}
