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
 *   - After selling a ticket, saveMovies() is called so the
 *     updated ticket count persists in movies.txt.
 * ============================================================
 */
public class TicketSeller extends Staff {

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
    public Movie[] viewAllMovies() {
        return movieManager.getAllMovies();
    }

    /**
     * Search movies by keyword (case-insensitive).
     */
    public Movie[] searchMovies(String keyword) {
        return java.util.Arrays.stream(movieManager.getAllMovies())
                .filter(m -> m.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .toArray(Movie[]::new);
    }

    /**
     * Sell a ticket for a movie.
     * --------------------------------------------------------
     * PROCESS:
     *   1. Find movie by ID
     *   2. Attempt to sell ticket
     *   3. If successful → saveMovies()
     */
    public boolean sellTicket(String movieId) {

        // Step 1: Find movie
        Movie movie = movieManager.findById(movieId);
        if (movie == null) return false;

        // Step 2: Attempt sale
        boolean sold = movie.sellTicket();

        // Step 3: Persist change
        if (sold) {
            movieManager.saveMovies();
        }

        return sold;
    }
}
