import javax.swing.*;

public class MainGUI extends JPanel {

    public MainGUI(Staff staff, MovieManager movieManager) {

        JTabbedPane tabs = new JTabbedPane();

        if (staff instanceof TicketSeller) {
            tabs.add("Browse", new BrowsePanel((TicketSeller) staff));
        }

        if (staff instanceof Manager) {
            tabs.add("Manage", new ManagePanel((Manager) staff));
        }

        setLayout(new java.awt.BorderLayout());
        add(tabs, java.awt.BorderLayout.CENTER);
    }
}
