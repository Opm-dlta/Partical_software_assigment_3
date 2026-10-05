import javax.swing.*;
import java.awt.*;

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
