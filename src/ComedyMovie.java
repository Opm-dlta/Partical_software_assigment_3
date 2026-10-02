public class ComedyMovie extends Movie {
    public String comedian;
//comedy here
    public ComedyMovie(String movieID, String title, String director, int duration,
                       double price, String showTime, int availableTickets,
                       String comedian) {
        super(movieID, title, director, duration, price, showTime, availableTickets);
        this.comedian = comedian;
    }

    @Override
    public String getExtraAttribute() {
        return comedian;
    }
}
