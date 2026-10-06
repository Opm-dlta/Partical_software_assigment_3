import javax.swing.JFrame;
import manager.MovieManager;
import manager.StaffManager;
import ui.LoginPanel;

/**
 * Main (APPLICATION ENTRY POINT)
 * ------------------------------------------------------------
 * This class launches the entire cinema system.
 *
 * LOCATION:
 *   src/main/java/Main.java
 *
 * PACKAGE:
 *   No package — this is the root caller.
 *
 * RESPONSIBILITIES:
 *   - Create MovieManager (loads movies.txt)
 *   - Create StaffManager (creates seller/manager accounts)
 *   - Create main JFrame window
 *   - Display LoginPanel as the first screen
 */
public class Main {

    public static void main(String[] args) {

        // ===== Backend managers =====
        MovieManager movieManager = new MovieManager();
        StaffManager staffManager = new StaffManager(movieManager);

        // ===== Main window =====
        JFrame frame = new JFrame("Cinema System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);

        // ===== First screen: Login =====
        frame.setContentPane(new LoginPanel(staffManager, movieManager));

        // ===== Show window =====
        frame.setVisible(true);
    }
}
