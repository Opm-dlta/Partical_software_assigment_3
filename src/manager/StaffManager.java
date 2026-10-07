package manager;

import staff.Staff;
import staff.TicketSeller;
import staff.Manager;

/**
 * ============================================================
 * StaffManager
 * ------------------------------------------------------------
 * PURPOSE:
 *   Handles login authentication and stores staff accounts.
 *
 * PACKAGE:
 *   src/main/java/manager/
 *
 * USED BY:
 *   - LoginPanel (for login)
 *   - Main (creates StaffManager)
 *
 * NOTES:
 *   - Uses the SAME MovieManager instance for all staff.
 * ============================================================
 */
public class StaffManager {

    private java.util.List<Staff> staffList = new java.util.ArrayList<>();
    private MovieManager movieManager;

    /**
     * Constructor
     * @param movieManager Shared MovieManager instance
     * StaffManager must receive MovieManager so it can pass it
     * to TicketSeller and Manager.
     */
    public StaffManager(MovieManager movieManager) {
        this.movieManager = movieManager;
        loadDefaultStaff();
    }

    /**
     * Create default accounts.
     * --------------------------------------------------------
     * README must match these accounts.
     */
    //login info of mangers and seller
    private void loadDefaultStaff() {
        // fix by Johnson  7/Oct
        // fix the password & account to professor request version
        // that ur original code, no problem u can delete it
        // staffList.add(new TicketSeller("seller", "123", movieManager));
        // staffList.add(new Manager("manager", "1234", movieManager));
        staffList.add(new TicketSeller("s1", "s1", movieManager));
        staffList.add(new TicketSeller("s2", "s2", movieManager));
        staffList.add(new TicketSeller("s3", "s3", movieManager));
        staffList.add(new Manager("m1", "m1", movieManager));
        staffList.add(new Manager("m2", "m2", movieManager));
    }

    /**
     * Attempt login.
     * @return Staff object if successful, null otherwise.
     */
    public Staff login(String u, String p) {
        for (Staff s : staffList) {
            if (s.login(u, p)) return s;
        }
        return null;
    }
}
