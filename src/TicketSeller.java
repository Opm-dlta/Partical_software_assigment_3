//icket Seller CAN
//These are the seller’s allowed actions:
//
//Log in to the system
//
//View all movies
//
//Search movies
//
//Sell tickets
//
//Reduce available ticket count
//
//View movie details (title, director, duration, price, showtime, extra attribute)
//
//Use the BrowsePanel
//
//Export ticket sales indirectly (through booking updates)

//Ticket Seller CANNOT
//These are actions the seller is not allowed to perform:
//
//Add new movies
//
//Update existing movies
//
//Delete movies
//
//Access the ManagePanel
//
//Change movie prices
//
//Change movie showtimes
//
//Change available ticket count manually
//
//Modify movie categories
//
//Export the movie list
//
//Manage staff accounts
//
//Change passwords
//
//Create new roles
//
//Access manager-only features
public class TicketSeller extends Staff {

    public TicketSeller(String username, String password) {
        super(username, password);
    }

    @Override
    public String getRole() {
        return "Seller";
    }
}
