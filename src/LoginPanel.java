import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    public LoginPanel(StaffManager staffManager, MovieManager movieManager, JFrame frame) {

        setLayout(new GridLayout(3, 2));

        JLabel userLabel = new JLabel("Username:");
        JTextField userField = new JTextField();

        JLabel passLabel = new JLabel("Password:");
        JPasswordField passField = new JPasswordField();

        JButton loginBtn = new JButton("Login");

        loginBtn.addActionListener(e -> {
            String u = userField.getText();
            String p = new String(passField.getPassword());

            Staff staff = staffManager.login(u, p);

            if (staff != null) {
                JOptionPane.showMessageDialog(this,
                        "Login successful as: " + staff.getRole());

            } else {
                JOptionPane.showMessageDialog(this,
                        "Invalid username or password");
            }
        });

        add(userLabel);
        add(userField);
        add(passLabel);
        add(passField);
        add(loginBtn);
    }
}
