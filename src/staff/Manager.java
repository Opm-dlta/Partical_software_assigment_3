package staff;

import manager.MovieManager;
import movie.Movie;
import java.io.File;
import java.io.IOException;

/** Manager account with seller actions and movie-management actions. */
public class Manager extends Staff implements MovieBrowser {

    private final MovieManager movieManager;

    public Manager(String username, String password, MovieManager movieManager) {
        super(username, password);
        this.movieManager = movieManager;
    }

    @Override
    public String getRole() {
        return "Manager";
    }

    @Override
    public Movie[] viewAllMovies() {
        return movieManager.getAllMovies();
    }

    @Override
    public Movie[] searchMovies(String category, String title) {
        return movieManager.searchMovies(category, title);
    }

    public Movie[] searchMovies(String title) {
        return movieManager.searchMovies("", title);
    }

    @Override
    public Movie findById(String movieId) {
        return movieManager.findById(movieId);
    }

    @Override
    public boolean sellTicket(String movieId) {
        Movie movie = movieManager.findById(movieId);
        return movie != null && movie.sellTicket();
    }

    public void exportMovies(File destination) throws IOException {
        movieManager.exportMovies(destination);
    }

    public boolean addMovie(Movie movie) {
        return movieManager.addMovie(movie);
    }

    public boolean updateMovie(String id, Movie updatedMovie) {
        return movieManager.updateMovie(id, updatedMovie);
    }

    public boolean deleteMovie(String id) {
        return movieManager.deleteMovie(id);
    }
}
