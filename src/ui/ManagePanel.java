package ui;

import javax.swing.*;
import java.awt.*;
import staff.Manager;
import movie.Movie;

/**
 * ============================================================
 * ManagePanel
 * ------------------------------------------------------------
 * PURPOSE:
 *   Manager's movie management interface.
 *
 * PACKAGE:
 *   src/main/java/ui/
 *
 * FEATURES:
 *   - View all movies
 *   - Add movie (future)
 *   - Update movie (future)
 *   - Delete movie (future)
 *
 * DEPENDENCIES:
 *   - Manager (backend operations)
 * ============================================================
 */
public class ManagePanel extends JPanel {

    private Manager manager;
    private JTable table;

    public ManagePanel(Manager manager) {
        this.manager = manager;

        setLayout(new BorderLayout());

        // ===== Movie table =====
        table = new JTable();
        refreshTable(manager.viewAllMovies());
        add(new JScrollPane(table), BorderLayout.CENTER);

        // ===== Buttons =====
        JPanel buttons = new JPanel();
        JButton addButton = new JButton("Add Movie");
        JButton updateButton = new JButton("Update Movie");
        JButton deleteButton = new JButton("Delete Movie");

        buttons.add(addButton);
        buttons.add(updateButton);
        buttons.add(deleteButton);

        add(buttons, BorderLayout.SOUTH);

        // ===== Event handlers =====
        addButton.addActionListener(e -> handleAdd());
        updateButton.addActionListener(e -> handleUpdate());
        deleteButton.addActionListener(e -> handleDelete());
    }

    /**
     * Refresh movie table.
     */
    private void refreshTable(Movie[] movies) {
        String[] columns = {"ID", "Title", "Director", "Showtime", "Tickets"};
        String[][] data = new String[movies.length][5];

        for (int i = 0; i < movies.length; i++) {
            data[i][0] = movies[i].getId();
            data[i][1] = movies[i].getTitle();
            data[i][2] = movies[i].getDirector();
            data[i][3] = movies[i].getShowtime();
            data[i][4] = String.valueOf(movies[i].getAvailableTickets());
        }

        table.setModel(new javax.swing.table.DefaultTableModel(data, columns));
    }

    // ===== Future manager actions =====

    private void handleAdd() {
        JOptionPane.showMessageDialog(this, "Add movie not implemented yet");
    }

    private void handleUpdate() {
        JOptionPane.showMessageDialog(this, "Update movie not implemented yet");
    }

    private void handleDelete() {
        JOptionPane.showMessageDialog(this, "Delete movie not implemented yet");
    }
}
