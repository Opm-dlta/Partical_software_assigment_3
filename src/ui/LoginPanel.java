package ui;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

import manager.MovieManager;
import manager.StaffManager;
import staff.Staff;

//login still need ui
//test pus
//by johnson, LoginPanel is for displaying UI and inputting the password.

public class LoginPanel extends JPanel {

    // 记录当前是否选择了 Manager 登录
    private boolean managerLogin = false;
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

        // 显示当前选择的登录角色 display current login role
        JLabel loginModeLabel = new JLabel("Seller Login", JLabel.CENTER);

        // 点击后会切换角色；切换行为下一步再添加 switch the role
        JButton switchRoleButton = new JButton("Switch to Manager Login");

        switchRoleButton.addActionListener(event -> {
            // 每点击一次，就把当前模式反过来 click then switch
            managerLogin = !managerLogin;

            if (managerLogin) {
                loginModeLabel.setText("Manager Login");
                switchRoleButton.setText("Switch to Seller Login");
            } else {
                loginModeLabel.setText("Seller Login");
                switchRoleButton.setText("Switch to Manager Login");
            }
        });

        // 把模式提示、账号表单和切换按钮放在同一个区域 put the 'switch' function to login page
        JPanel loginContentPanel = new JPanel(new BorderLayout(0, 10));
        loginContentPanel.add(loginModeLabel, BorderLayout.NORTH);
        loginContentPanel.add(formPanel, BorderLayout.CENTER);
        loginContentPanel.add(switchRoleButton, BorderLayout.SOUTH);
        loginContentPanel.setPreferredSize(new Dimension(300, 130));
        loginContentPanel.setOpaque(false);

        formPanel.setPreferredSize(new Dimension(300, 50));  //input size

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.add(loginContentPanel);

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