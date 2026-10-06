package movie;

/**
 * ============================================================
 * ComedyMovie
 * ------------------------------------------------------------
 * Extra attribute: comedian (may be "-")
 * ============================================================
 */
public class ComedyMovie extends Movie {

    private final String comedian;

    public ComedyMovie(String movieID, String title, String director, int duration,
                       double price, String showTime, String comedian,
                       int availableTickets) {
        super(movieID, title, director, duration, price, showTime, availableTickets);
        this.comedian = comedian;
    }

    @Override
    public String getExtraAttribute() {
        return comedian;
    }

    @Override
    public String toFileString() {
        return "Comedy, " + id + ", " + title + ", " + director + ", " +
                duration + ", " + price + ", " + showtime + ", " +
                comedian + ", " + availableTickets;
    }
}
