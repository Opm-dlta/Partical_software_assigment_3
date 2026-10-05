import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        StaffManager staffManager = new StaffManager();
        MovieManager movieManager = new MovieManager();

        JFrame frame = new JFrame("Cinema System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        frame.setContentPane(new LoginPanel(staffManager, movieManager));
        frame.setVisible(true);
    }
}
