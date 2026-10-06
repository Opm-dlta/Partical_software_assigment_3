import javax.swing.SwingUtilities;
import javax.swing.JFrame;

import manager.MovieManager;
import manager.StaffManager;
import ui.LoginPanel;

// test push 1111111
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // 窗口和登录面板放在这里 login here！！
            MovieManager movieManager = new MovieManager();
            StaffManager staffManager = new StaffManager(movieManager);

            JFrame frame = new JFrame("Cinema Ticket System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new LoginPanel(staffManager, movieManager, frame));
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}