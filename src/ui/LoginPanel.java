package ui;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

import manager.MovieManager;
import manager.StaffManager;
import staff.Staff;

//login still need ui

//by johnson, LoginPanel is for displaying UI and inputting the password.

public class LoginPanel extends JPanel {

    private Image backgroundImage;

    //    StaffManager staffManager for store password, focus on sign in
//    StaffManager staffManager 把现有的账号管理对象传进来，之后用它验证登录。
//    Frame frame 是队友原来调用这个面板时传入的参数 -Johnson - 6 Oct
    public LoginPanel(StaffManager staffManager, MovieManager movieManager, JFrame frame) {

        setLayout(new BorderLayout());      //setLayout is for setting the layout

        URL imageUrl = getClass().getResource("/images/login-background.jpg");  //background image

        if (imageUrl != null) {
            backgroundImage = new ImageIcon(imageUrl).getImage();
        }

        JLabel title = new JLabel("Cinema Staff Login", JLabel.CENTER);     //JLabel-display word, Center
        title.setFont(new Font("Arial", Font.BOLD, 26));        //word font
        add(title, BorderLayout.NORTH);     //title on the top

        JPanel formPanel = new JPanel(new GridLayout(2, 2, 8, 8));     // formPanel is a broad for input
        JTextField usernameField = new JTextField(18);
        JPasswordField passwordField = new JPasswordField(18);  //JPasswordField can hide password

        formPanel.add(new JLabel("Username:"));
        formPanel.add(usernameField);
        formPanel.add(new JLabel("Password:"));
        formPanel.add(passwordField);

        formPanel.setPreferredSize(new Dimension(300, 50));  //input size

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(formPanel);

        add(centerPanel, BorderLayout.CENTER);  //add input Context

        formPanel.setOpaque(false);
        centerPanel.setOpaque(false); // no background color in formPanel & centerPanel

        JButton loginButton = new JButton("Login");  // create btn
        add(loginButton, BorderLayout.SOUTH);  //SOUTH = bottom

//        被点击时要执行里面的代码 Run it when click btn
        loginButton.addActionListener(event -> {
            String username = usernameField.getText().trim();  //get account, trim()--remove 2 useless space from input
            String password = new String(passwordField.getPassword());  //get password, switch to 'string'

            Staff staff = staffManager.login(username, password);  //check, T return staff, F return null

            if (staff == null) {
                JOptionPane.showMessageDialog(this, "Invalid username or password.");
            } else {
                JOptionPane.showMessageDialog(this, "Logged in as: " + staff.getRole()); // get userRole
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}