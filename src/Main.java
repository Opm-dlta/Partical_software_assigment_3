import javax.swing.SwingUtilities;
import javax.swing.JFrame;

// test push
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // 窗口和登录面板放在这里
            StaffManager staffManager = new StaffManager();
            staffManager.loadDefaultStaff();

            MovieManager movieManager = new MovieManager();

            JFrame frame = new JFrame("Cinema Ticket System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setContentPane(new LoginPanel(staffManager, movieManager, frame));
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}