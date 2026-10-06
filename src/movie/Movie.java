package movie;

/**
 * Movie is the abstract base class for all movie types in the Cinema System.
 *
 * It stores the common attributes shared by all movies:
 * - ID
 * - Title
 * - Director
 * - Duration
 * - Price
 * - Showtime
 * - Available tickets
 *
 * Subclasses (ActionMovie, ComedyMovie, RomanceMovie, SciFiMovie)
 * must implement:
 * - getExtraAttribute()   → returns the genre-specific detail
 * - toFileString()        → returns a formatted line for movies.txt
 */
public abstract class Movie {

    protected String id;
    protected String title;
    protected String director;
    protected int duration;
    protected double price;
    protected String showtime;
    protected int availableTickets;

    /**
     * Creates a Movie object with shared attributes.
     *
     * @param id               Unique movie ID (e.g., "A001")
     * @param title            Movie title
     * @param director         Movie director
     * @param duration         Duration in minutes
     * @param price            Ticket price
     * @param showtime         Showtime (e.g., "18:30")
     * @param availableTickets Number of tickets available
     */
    public Movie(String id, String title, String director, int duration,
                 double price, String showtime, int availableTickets) {

        this.id = id;
        this.title = title;
        this.director = director;
        this.duration = duration;
        this.price = price;
        this.showtime = showtime;
        this.availableTickets = availableTickets;
    }

    // -----------------------------
    // Getters for shared attributes
    // -----------------------------

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public int getDuration() {
        return duration;
    }

    public double getPrice() {
        return price;
    }

    public String getShowtime() {
        return showtime;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    /**
     * Reduces available tickets by 1 when a ticket is sold.
     * Returns true if successful, false if no tickets remain.
     */
    public boolean sellTicket() {
        if (availableTickets > 0) {
            availableTickets--;
            return true;
        }
        return false;
    }

    // ---------------------------------------------------------
    // Abstract methods that subclasses MUST implement
    // ---------------------------------------------------------

    /**
     * Returns the extra attribute specific to the movie genre.
     * Example:
     * - ActionMovie → stunt coordinator
     * - ComedyMovie → humor style
     * - RomanceMovie → rating
     * - SciFiMovie → format
     */
    public abstract String getExtraAttribute();

    /**
     * Converts the movie into a formatted line for saving into movies.txt.
     * Subclasses must include their genre name and extra attribute.
     */
    public abstract String toFileString();
}