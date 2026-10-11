package Zad8;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class User {
    String name;
    ArrayList<Movie> watchedGenres = new ArrayList<>();
    Queue<Movie> queue = new LinkedList<Movie>();
    public User(String name) {
        this.name = name;
    }

    public void addGenre(Movie m){
        watchedGenres.add(m);
    }

    public ArrayList<Movie> getWatchedGenres(){
        return watchedGenres;
    }

    public void addToQueue(Movie m){
        queue.add(m);
    }

    public Queue<Movie> getQueue(){
        return queue;
    }
}
