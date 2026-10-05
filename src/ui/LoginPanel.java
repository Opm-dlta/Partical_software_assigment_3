package ui;

import javax.swing.*;
import java.awt.*;
import manager.StaffManager;
import manager.MovieManager;
import staff.Staff;

/**
 * ============================================================
 * LoginPanel
 * ------------------------------------------------------------
 * PURPOSE:
 *   First screen of the application.
 *   Allows user to enter username + password.
 *
 * PACKAGE:
 *   src/main/java/ui/
 *
 * DEPENDENCIES:
 *   - StaffManager (authentication)
 *   - MovieManager (passed to MainGUI)
 *
 * FLOW:
 *   1. User enters credentials
 *   2. StaffManager.login() checks them
 *   3. If valid → switch to MainGUI
 * ============================================================
 */
public class LoginPanel extends JPanel {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    private StaffManager staffManager;
    private MovieManager movieManager;

    public LoginPanel(StaffManager staffManager, MovieManager movieManager) {
        this.staffManager = staffManager;
        this.movieManager = movieManager;

        setLayout(new GridLayout(3, 2));

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        loginButton = new JButton("Login");
        add(loginButton);

        loginButton.addActionListener(e -> handleLogin());
    }

    /**
     * Attempt login and switch to MainGUI if successful.
     */
    private void handleLogin() {
        String u = usernameField.getText();
        String p = new String(passwordField.getPassword());

        Staff staff = staffManager.login(u, p);

        if (staff != null) {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.setContentPane(new MainGUI(staff, movieManager));
            frame.revalidate();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid login");
        }
    }
}
