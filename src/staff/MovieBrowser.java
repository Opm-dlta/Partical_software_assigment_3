package staff;

import movie.Movie;

/** Movie actions shared by the seller and manager Browse screens. */
public interface MovieBrowser {
    Movie[] viewAllMovies();
    Movie[] searchMovies(String category, String title);
    Movie findById(String movieId);
    boolean sellTicket(String movieId);
}
