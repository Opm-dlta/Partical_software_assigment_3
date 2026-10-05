import javax.swing.*;
// to run everything
public class MainGUI extends JFrame {
    public MainGUI(MovieManager movieManager, StaffManager staffManager) {
        setTitle("Cinema Ticket System");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JTabbedPane tabs = new JTabbedPane();

        tabs.add("Login", new LoginPanel(staffManager, movieManager, this));
        tabs.add("Browse", new BrowsePanel(movieManager));
        tabs.add("Manage", new ManagePanel(movieManager));

        add(tabs);
    }
}
