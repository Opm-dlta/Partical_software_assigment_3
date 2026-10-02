public class RomanceMovie extends Movie {
    public String loveTheme;

    public RomanceMovie(String movieID, String title, String director, int duration,
                        double price, String showTime, int availableTickets,
                        String loveTheme) {
        super(movieID, title, director, duration, price, showTime, availableTickets);
        this.loveTheme = loveTheme;
    }

    @Override
    public String getExtraAttribute() {
        return loveTheme;
    }
}
