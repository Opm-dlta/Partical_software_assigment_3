package staff;

import manager.MovieManager;   // Correct package for MovieManager
import movie.Movie;            // Correct package for Movie class

/**
 * ============================================================
 * Manager (STAFF ROLE)
 * ------------------------------------------------------------
 * ROLE:
 *   - View all movies
 *   - (Future) Add / Update / Delete movies
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
}
