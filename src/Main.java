// Main.java
public class Main {
// not sure what to do yetk
    public static void main(String[] args) {

        // 1. Load movie data
        MovieManager movieManager = new MovieManager();
        movieManager.loadFromFile("movies.txt");

        // 2. Load staff accounts
        StaffManager staffManager = new StaffManager();
        staffManager.loadDefaultStaff();

        // 3. Start GUI
        javax.swing.SwingUtilities.invokeLater(() -> {
            MainGUI gui = new MainGUI(movieManager, staffManager);
            gui.setVisible(true);
        });
    }
}
