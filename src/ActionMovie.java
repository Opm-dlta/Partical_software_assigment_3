public class ActionMovie extends Movie {
    public String stuntCoordinator;
  //all comedy here
    public ActionMovie(String movieID, String title, String director, int duration,
                       double price, String showTime, int availableTickets,
                       String stuntCoordinator) {
        super(movieID, title, director, duration, price, showTime, availableTickets);
        this.stuntCoordinator = stuntCoordinator;
    }

    @Override
    public String getExtraAttribute() {
        return stuntCoordinator;
    }
}
