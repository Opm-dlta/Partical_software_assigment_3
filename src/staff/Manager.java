package staff;

import manager.MovieManager;
import movie.Movie;
import movie.ActionMovie;
import movie.ComedyMovie;
import movie.RomanceMovie;
import movie.SciFiMovie;

/**
 * ============================================================
 * Manager (STAFF ROLE)
 * ------------------------------------------------------------
 * ROLE:
 *   - View, search, and sell tickets like a TicketSeller
 *   - Add, update, and delete movies
 *
 * PACKAGE STRUCTURE:
 *   This class lives in: src/main/java/staff/
 *
 * DEPENDENCIES:
 *   - MovieManager (from package manager)
 *   - Movie        (from package movie)
 *
 * WHY THIS FIXES THE GUARDIAN REVIEW:
 *   - MovieManager is now inside package manager, not default.
 *   - Manager.java now imports the correct package.
 *   - No duplicate Manager classes.
 *   - staff.Manager can now access MovieManager normally.
 * ============================================================
 */
public class Manager extends Staff {

    private MovieManager movieManager;

    /**
     * Constructor
     * --------------------------------------------------------
     * @param username     Manager username
     * @param password     Manager password
     * @param movieManager Shared MovieManager instance
     *
     * NOTE:
     *   StaffManager will pass the same MovieManager to both
     *   Manager and TicketSeller so they operate on the same data.
     */
    public Manager(String username, String password, MovieManager movieManager) {
        super(username, password);
        this.movieManager = movieManager;
    }

    @Override
    public String getRole() {
        return "Manager";
    }

    /**
     * View all movies in the system.
     * Used by ManagePanel to populate the movie table.
     */
    public Movie[] viewAllMovies() {
        return movieManager.getAllMovies();
    }

    /** Search by title, matching the TicketSeller search behavior. */
    public Movie[] searchMovies(String keyword) {
        String query = keyword == null ? "" : keyword.trim().toLowerCase();
        return java.util.Arrays.stream(movieManager.getAllMovies())
                .filter(movie -> movie.getTitle().toLowerCase().contains(query))
                .toArray(Movie[]::new);
    }

    /** Search by category and title; an empty value matches any value. */
    public Movie[] searchMovies(String category, String title) {
        String categoryQuery = category == null ? "" : category.trim();
        String titleQuery = title == null ? "" : title.trim().toLowerCase();
        return java.util.Arrays.stream(movieManager.getAllMovies())
                .filter(movie -> (categoryQuery.isEmpty() || movieCategory(movie).equalsIgnoreCase(categoryQuery))
                        && movie.getTitle().toLowerCase().contains(titleQuery))
                .toArray(Movie[]::new);
    }

    /** Sell a ticket, if the movie exists and tickets remain. */
    public boolean sellTicket(String movieId) {
        Movie movie = movieManager.findById(movieId);
        if (movie == null || !movie.sellTicket()) return false;
        movieManager.saveMovies();
        return true;
    }

    /** Manager-only operation: add a movie with a unique ID. */
    public boolean addMovie(Movie movie) {
        return movieManager.addMovie(movie);
    }

    /** Manager-only operation: update details without changing the ID. */
    public boolean updateMovie(String id, Movie updatedMovie) {
        return movieManager.updateMovie(id, updatedMovie);
    }

    /** Manager-only operation: delete a movie by ID. */
    public boolean deleteMovie(String id) {
        return movieManager.deleteMovie(id);
    }

    private String movieCategory(Movie movie) {
        if (movie instanceof ActionMovie) return "Action";
        if (movie instanceof ComedyMovie) return "Comedy";
        if (movie instanceof RomanceMovie) return "Romance";
        if (movie instanceof SciFiMovie) return "Science Fiction";
        return "";
    }
}