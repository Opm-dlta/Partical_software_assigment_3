//this is the central  place where your program stores, loads, searches, updates, and deletes movies.
import java.util.ArrayList;
import java.util.List;

public class MovieManager {

    private List<Movie> movies = new ArrayList<>();

    public void loadFromFile(String filename) {
        // bare-bone placeholder
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void updateMovie(Movie movie) {
        // bare-bone placeholder
    }

    public void deleteMovie(String movieID) {
        // bare-bone placeholder
    }
}
