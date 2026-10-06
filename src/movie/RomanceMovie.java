package movie;

/**
 * ============================================================
 * RomanceMovie
 * ------------------------------------------------------------
 * Extra attribute: rating (e.g., PG13)
 * ============================================================
 */
public class RomanceMovie extends Movie {

    private String rating;

    public RomanceMovie(String movieID, String title, String director, int duration,
                        double price, String showTime, String rating,
                        int availableTickets) {
        super(movieID, title, director, duration, price, showTime, availableTickets);
        this.rating = rating;
    }

    @Override
    public String getExtraAttribute() {
        return rating;
    }

    @Override
    public String toFileString() {
        return "Romance, " + id + ", " + title + ", " + director + ", " +
                duration + ", " + price + ", " + showtime + ", " +
                rating + ", " + availableTickets;
    }
}