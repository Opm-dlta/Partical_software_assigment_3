package ui;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import manager.MovieManager;
import manager.StaffManager;
import staff.Staff;

public class LoginPanel extends JPanel {

    private boolean managerLogin = false;
    private Image backgroundImage;

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    private StaffManager staffManager;
    private MovieManager movieManager;

    public LoginPanel(StaffManager staffManager, MovieManager movieManager) {
        this.staffManager = staffManager;
        this.movieManager = movieManager;
//ang
        setLayout(new BorderLayout());

        // ===== LOAD BACKGROUND IMAGE =====
        try {
            URL imageUrl = getClass().getResource("/background_image/login-background.jpg");
            if (imageUrl != null) {
                backgroundImage = new ImageIcon(imageUrl).getImage();
            }
        } catch (Exception e) {
            System.out.println("Background image not found.");
        }
//end
        // ===== TITLE =====
        JLabel title = new JLabel("Cinema Staff Login", JLabel.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 32));
        title.setForeground(new Color(245, 196, 90));
        add(title, BorderLayout.NORTH);

        // ===== FORM FIELDS =====
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 8, 8));
        formPanel.setOpaque(false);

        usernameField = new JTextField(18);
        passwordField = new JPasswordField(18);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE);
//ang
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);

        formPanel.add(usernameLabel);
        formPanel.add(usernameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);

        usernameField.setBackground(Color.white);
        usernameField.setForeground(new Color(35, 31, 27));

        passwordField.setBackground(Color.white);
        passwordField.setForeground(new Color(35, 31, 27));
//end
        // ===== ROLE SWITCH =====
        JLabel loginModeLabel = new JLabel("Seller Login", JLabel.CENTER);
        loginModeLabel.setForeground(Color.WHITE);

        JButton switchRoleButton = new JButton("Switch to Manager Login");
        switchRoleButton.addActionListener(event -> {
            managerLogin = !managerLogin;

            if (managerLogin) {
                loginModeLabel.setText("Manager Login");
                switchRoleButton.setText("Switch to Seller Login");
            } else {
                loginModeLabel.setText("Seller Login");
                switchRoleButton.setText("Switch to Manager Login");
            }
        });

        // ===== CENTER PANEL =====
        JPanel loginContentPanel = new JPanel(new BorderLayout(0, 10));
        loginContentPanel.setOpaque(false);
        loginContentPanel.add(loginModeLabel, BorderLayout.NORTH);
        loginContentPanel.add(formPanel, BorderLayout.CENTER);
        loginContentPanel.add(switchRoleButton, BorderLayout.SOUTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        centerPanel.add(loginContentPanel);

        add(centerPanel, BorderLayout.CENTER);

        // ===== LOGIN BUTTON =====
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));
        add(loginButton, BorderLayout.SOUTH);

        // ===== LOGIN ACTION =====
        loginButton.addActionListener(e -> handleLogin());

        // ===== ENTER KEY IN PASSWORD FIELD =====
        passwordField.addActionListener(e -> handleLogin());
    }

    private void handleLogin() {
        String u = usernameField.getText().trim();
        String p = new String(passwordField.getPassword());

        Staff staff = staffManager.login(u, p);

        if (staff == null) {
            JOptionPane.showMessageDialog(this, "Invalid username or password.");
            return;
        }

        // Validate role selection ang
        String selectedRole = managerLogin ? "Manager" : "Seller";
        if (!staff.getRole().equals(selectedRole)) {
            JOptionPane.showMessageDialog(this,
                    "You selected " + selectedRole + " but logged in as " + staff.getRole() + ".");
            return;
        }

        // Switch to MainGUI ang
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        frame.setContentPane(new MainGUI(staff, movieManager));
        frame.revalidate();
        frame.repaint();
    }
//Ang
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
//end