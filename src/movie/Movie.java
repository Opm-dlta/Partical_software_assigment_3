//The Movie superclass   is the parent class that defines all the common attributes and behaviors shared by every movie category.
package movie;

/**
 * Movie is the base class for all movie types in the Cinema System.
 *
 * It stores all common attributes shared by Action, Comedy, Romance,
 * and ScienceFiction movies. Subclasses add their own extra attributes.
 *
 * This class is used by:
 * - MovieManager (loading, saving, searching)
 * - TicketSeller (viewing details, selling tickets)
 * - Manager (adding, updating, deleting movies)
 * - UI panels (displaying movie information)
 */
public abstract class Movie {

    protected String id;
    protected String title;
    protected String director;
    protected int duration;          // in minutes
    protected double price;          // ticket price
    protected String showtime;       // e.g., "18:30"
    protected int availableTickets;  // starts at 50

    /**
     * Constructor for a movie with common attributes.
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
    // Getters
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

    // -----------------------------
    // Seller-only action
    // -----------------------------

    /**
     * Reduces available ticket count by 1.
     * Only called by TicketSeller.sellTicket().
     */
    public void reduceTicketCount() {
        if (availableTickets > 0) {
            availableTickets--;
        }
    }

    // -----------------------------
    // File saving support
    // -----------------------------

    /**
     * Converts the movie into a line format for movies.txt.
     * Subclasses override this to include their extra attribute.
     */
    public abstract String toFileString();

    // -----------------------------
    // Display support
    // -----------------------------

    /**
     * Returns a readable description of the movie.
     * UI uses this for displaying movie details.
     */
    @Override
    public String toString() {
        return "ID: " + id +
                "\nTitle: " + title +
                "\nDirector: " + director +
                "\nDuration: " + duration + " mins" +
                "\nPrice: $" + price +
                "\nShowtime: " + showtime +
                "\nAvailable Tickets: " + availableTickets;
    }
}
