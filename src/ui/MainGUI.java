package ui;

import javax.swing.*;
import java.awt.BorderLayout;
import manager.MovieManager;
import staff.Manager;
import staff.Staff;
import staff.TicketSeller;

/** Main application tabs selected for the logged-in staff role. */
public class MainGUI extends JPanel {

    public MainGUI(Staff staff, MovieManager movieManager) {
        JTabbedPane tabs = new JTabbedPane();

        if (staff instanceof TicketSeller) {
            tabs.addTab("Browse", new BrowsePanel((TicketSeller) staff));
        } else if (staff instanceof Manager) {
            Manager manager = (Manager) staff;
            // Managers can perform seller tasks as well as manage movie records.
            tabs.addTab("Browse", new BrowsePanel(manager));
            tabs.addTab("Manage", new ManagePanel(manager));
        }

        setLayout(new BorderLayout());
        add(tabs, BorderLayout.CENTER);
    }
}
