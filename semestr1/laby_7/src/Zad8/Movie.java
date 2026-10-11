package Zad8;

public class Movie {
    String title;
    String genre;
    double rating;
    public Movie(String title, String genre, double rating) {
        this.title = title;
        this.genre = genre;
        this.rating = rating;
    }
    @Override
    public String toString() {
        return "Movie " + title + ", genre " + genre ;
    }
}
