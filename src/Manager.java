//Manager CAN
//Add new movies
//
//Update existing movies
//
//Delete movies
//
//View all movies
//
//Search movies
//
//Sell tickets
//
//Export movie data to a file
//
//Access the ManagePanel (special manager-only GUI tab) will be add

//Manager CANNOT
//Add staff
//
//Delete staff
//
//Change staff passwords
//
//Create new roles
//
//Modify login system
//
//Access any hidden admin features (none exist in this assignment)
//
//Change system settings
//
//Manage customers (there are no customers in this assignment)
public class Manager extends Staff {

    public Manager(String username, String password) {
        super(username, password);
    }

    @Override
    public String getRole() {
        return "Manager";
    }
}
