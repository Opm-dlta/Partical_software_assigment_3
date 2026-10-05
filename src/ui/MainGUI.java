package ui;

import javax.swing.*;
import staff.Staff;
import staff.TicketSeller;
import staff.Manager;
import manager.MovieManager;

/**
 * ============================================================
 * MainGUI
 * ------------------------------------------------------------
 * PURPOSE:
 *   Main application screen after login.
 *   Shows different tabs depending on staff role.
 *
 * PACKAGE:
 *   src/main/java/ui/
 *
 * TABS:
 *   - Seller → BrowsePanel
 *   - Manager → ManagePanel
 *
 * DEPENDENCIES:
 *   - Staff (role detection)
 *   - MovieManager (passed to panels)
 * ============================================================
 */
public class MainGUI extends JPanel {

    public MainGUI(Staff staff, MovieManager movieManager) {

        JTabbedPane tabs = new JTabbedPane();

        // Seller gets BrowsePanel
        if (staff instanceof TicketSeller) {
            tabs.add("Browse", new BrowsePanel((TicketSeller) staff));
        }

        // Manager gets ManagePanel
        if (staff instanceof Manager) {
            tabs.add("Manage", new ManagePanel((Manager) staff));
        }

        setLayout(new java.awt.BorderLayout());
        add(tabs, java.awt.BorderLayout.CENTER);
    }
}
