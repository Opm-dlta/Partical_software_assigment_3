package movie;

/**
 * ============================================================
 * SciFiMovie
 * ------------------------------------------------------------
 * Extra attribute: format (e.g., 3D, IMAX)
 * ============================================================
 */
public class SciFiMovie extends Movie {

    private String format;

    public SciFiMovie(String movieID, String title, String director, int duration,
                      double price, String showTime, String format,
                      int availableTickets) {
        super(movieID, title, director, duration, price, showTime, availableTickets);
        this.format = format;
    }

    @Override
    public String getExtraAttribute() {
        return format;
    }

    @Override
    public String toFileString() {
        return "SciFi, " + id + ", " + title + ", " + director + ", " +
                duration + ", " + price + ", " + showtime + ", " +
                format + ", " + availableTickets;
    }
}
