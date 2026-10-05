package movie;

/**
 * ActionMovie represents an action‑genre movie in the Cinema System.
 *
 * This class extends the base Movie class and adds the extra attribute
 * required for Action movies: the stunt coordinator.
 *
 * It is used by:
 * - MovieManager (loading and saving movie data)
 * - TicketSeller (viewing details, selling tickets)
 * - Manager (adding/updating/deleting movies)
 * - UI panels (displaying movie information)
 *
 * The constructor parameters MUST match the order used in MovieManager:
 * (id, title, director, duration, price, showTime, stuntCoordinator, tickets)
 */
public class ActionMovie extends Movie {

    // Extra attribute specific to Action movies
    private final String stuntCoordinator;

    /**
     * Creates a new ActionMovie object.
     *
     * @param movieID          Unique movie identifier (e.g., "A001")
     * @param title            Movie title
     * @param director         Movie director
     * @param duration         Duration in minutes
     * @param price            Ticket price
     * @param showTime         Showtime (e.g., "18:30")
     * @param stuntCoordinator Name of the stunt coordinator (extra attribute)
     * @param availableTickets Number of tickets available
     */
    public ActionMovie(String movieID, String title, String director, int duration,
                       double price, String showTime, String stuntCoordinator,
                       int availableTickets) {

        // Call the base Movie constructor for shared attributes
        super(movieID, title, director, duration, price, showTime, availableTickets);

        // Store the extra attribute
        this.stuntCoordinator = stuntCoordinator;
    }

    /**
     * Returns the extra attribute for Action movies.
     * Required by MovieManager and UI when displaying movie details.
     */
    @Override
    public String getExtraAttribute() {
        return stuntCoordinator;
    }

    /**
     * Converts this movie into a single line of text for saving into movies.txt.
     * The format MUST match the structure used in MovieManager.loadMovies().
     */
    @Override
    public String toFileString() {
        return "Action, " + id + ", " + title + ", " + director + ", " +
                duration + ", " + price + ", " + showtime + ", " +
                stuntCoordinator + ", " + availableTickets;
    }
}
