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
public class TicketSeller extends Staff {

    private MovieManager movieManager;

    public TicketSeller(String username, String password, MovieManager movieManager) {
        super(username, password);
        this.movieManager = movieManager;
    }

    @Override
    public String getRole() {
        return "Seller";
    }

    /**
     * Seller can view all movies.
     */
    public Movie[] viewAllMovies() {
        return movieManager.getAllMovies();
    }

    /**
     * Seller can search movies by keyword.
     */
    public Movie[] searchMovies(String keyword) {
        return movieManager.searchMovies(keyword);
    }

    /**
     * Seller can view detailed movie information.
     */
    public Movie viewMovieDetails(String movieId) {
        return movieManager.getMovieById(movieId);
    }

    /**
     * Seller can sell a ticket.
     * This reduces the available ticket count by 1.
     * UI will handle all messages and errors.
     */
    public boolean sellTicket(String movieId) {
        Movie movie = movieManager.getMovieById(movieId);

        if (movie == null) {
            return false; // movie not found
        }

        if (movie.getAvailableTickets() <= 0) {
            return false; // no tickets left
        }

        // Allowed: reduce ticket count through selling
        movie.reduceTicketCount();

        // Save updated movie list to file (indirect export)
        movieManager.saveMovies();

        return true;
    }
}
