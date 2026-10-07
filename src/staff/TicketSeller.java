//icket Seller CAN
//These are the seller’s allowed actions:
//
//Log in to the system
//
//View all movies
//
//Search movies
//
//Sell tickets
//
//Reduce available  ticket count
//
//View movie details (title, director, duration, price, showtime, extra attribute)
//
//Use the BrowsePanel
//
//Export ticket sales indirectly (through booking updates)

//Ticket Seller CANNOT
//These are actions the seller is not allowed to perform:
//
//Add new movies
//
//Update existing movies
//
//Delete movies
//
//Access the ManagePanel
//
//Change movie prices
//
//Change movie showtimes
//
//Change available ticket count manually
//
//Modify movie categories
//
//Export the movie list
//
//Manage staff accounts
//
//Change password
//
//Create new roles
//
//Access manager-only features

package staff;

import manager.MovieManager;
import movie.Movie;

/**
 * ============================================================
 * TicketSeller
 * ------------------------------------------------------------
 * ROLE:
 *   - View all movies
 *   - Search movies
 *   - Sell tickets
 *
 * DEPENDENCIES:
 *   - MovieManager (backend movie operations)
 *
 * NOTES:
 *   - Ticket sales update the shared in-memory movie list.
 *   - Export the current data separately when required.
 * ============================================================
 */
public class TicketSeller extends Staff implements MovieBrowser {

    private MovieManager movieManager;

    /**
     * Constructor
     * --------------------------------------------------------
     * @param username     Seller username
     * @param password     Seller password
     * @param movieManager Shared movie manager instance
     */
    public TicketSeller(String username, String password, MovieManager movieManager) {
        super(username, password);
        this.movieManager = movieManager;
    }

    @Override
    public String getRole() {
        return "Seller";
    }

    /**
     * View all movies.
     * Used by BrowsePanel to populate the movie table.
     */
    @Override
    public Movie[] viewAllMovies() {
        return movieManager.getAllMovies();
    }

    /**
     * Search movies by keyword (case-insensitive).
     */
    public Movie[] searchMovies(String keyword) {
        return searchMovies("", keyword);
    }

    @Override
    public Movie[] searchMovies(String category, String title) {
        return movieManager.searchMovies(category, title);
    }

    @Override
    public Movie findById(String movieId) {
        return movieManager.findById(movieId);
    }

    /**
     * Sell a ticket for a movie.
     * --------------------------------------------------------
     * PROCESS:
     *   1. Find movie by ID
     *   2. Attempt to sell ticket
     *   3. Return whether the sale succeeded.
     */
    public boolean sellTicket(String movieId) {

        // Step 1: Find movie
        Movie movie = movieManager.findById(movieId);
        if (movie == null) return false;

        // Step 2: Attempt sale
        boolean sold = movie.sellTicket();

        return sold;
    }
}
