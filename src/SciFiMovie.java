public class SciFiMovie extends Movie {
    public String technology;
//all sifi here
    public SciFiMovie(String movieID, String title, String director, int duration,
                      double price, String showTime, int availableTickets,
                      String technology) {
        super(movieID, title, director, duration, price, showTime, availableTickets);
        this.technology = technology;
    }

    @Override
    public String getExtraAttribute() {
        return technology;
    }
}
