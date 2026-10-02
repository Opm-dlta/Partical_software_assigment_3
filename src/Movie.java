//The Movie superclass   is the parent class that defines all the common attributes and behaviors shared by every movie category.
public abstract class Movie {
    public String movieID;
    public String title;
    public String director;
    public int duration;
    public double price;
    public String showTime;
    public int availableTickets;

    public Movie(String movieID, String title, String director, int duration,
                 double price, String showTime, int availableTickets) {
        this.movieID = movieID;
        this.title = title;
        this.director = director;
        this.duration = duration;
        this.price = price;
        this.showTime = showTime;
        this.availableTickets = availableTickets;
    }

    public void bookTicket() {
        if (availableTickets > 0) {
            availableTickets--;
        }
    }

    public abstract String getExtraAttribute();
}
