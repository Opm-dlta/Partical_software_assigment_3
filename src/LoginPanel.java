import javax.swing.*;
import java.awt.*;
//login still need ui
public class LoginPanel extends JPanel {

    public LoginPanel(StaffManager staffManager, MovieManager movieManager, JFrame frame) {
        setLayout(new GridLayout(3, 2));

        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();
        JButton loginBtn = new JButton("Login");

        loginBtn.addActionListener(e -> {
            Staff staff = staffManager.login(
                    userField.getText(),
                    new String(passField.getPassword())
            );

            if (staff != null) {
                JOptionPane.showMessageDialog(this, "Logged in as: " + staff.getRole());
            } else {
                JOptionPane.showMessageDialog(this, "Invalid login");
            }
        });

        add(new JLabel("Username:"));
        add(userField);
        add(new JLabel("Password:"));
        add(passField);
        add(loginBtn);
    }
}
