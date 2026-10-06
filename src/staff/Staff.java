/**
 * ============================================================
 * Staff (ABSTRACT CLASS)
 * ------------------------------------------------------------
 * PURPOSE:
 *   This class represents a generic staff member in the cinema
 *   system. It provides shared attributes and behaviors for all
 *   staff roles (TicketSeller, Manager).
 *
 * SHARED FIELDS:
 *   - username : staff login name
 *   - password : staff login password
 *
 * SUBCLASSES MUST IMPLEMENT:
 *   - getRole() : returns the role name ("Seller", "Manager")
 *
 * USED BY:
 *   - StaffManager (for login authentication)
 *   - MainGUI (to determine which UI tabs to show)
 * ============================================================
 */
package staff;

/**
 * ============================================================
 * Staff (ABSTRACT CLASS)
 * ------------------------------------------------------------
 * PURPOSE:
 *   Base class for all staff roles in the cinema system.
 *
 * SHARED FIELDS:
 *   - username : login username
 *   - password : login password
 *
 * USED BY:
 *   - StaffManager (authentication)
 *   - MainGUI (determines which UI tabs to show)
 *
 * SUBCLASSES MUST IMPLEMENT:
 *   - getRole() : returns "Seller" or "Manager"
 * ============================================================
 */
public abstract class Staff {

    protected String username;
    protected String password;

    /**
     * Constructor
     * --------------------------------------------------------
     * Stores login credentials for authentication.
     */
    public Staff(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Login check
     * --------------------------------------------------------
     * @return true if username + password match
     */
    public boolean login(String u, String p) {
        return username.equals(u) && password.equals(p);
    }

    /**
     * Abstract method: implemented by subclasses.
     * --------------------------------------------------------
     * @return Role name ("Seller" or "Manager")
     */
    public abstract String getRole();
}