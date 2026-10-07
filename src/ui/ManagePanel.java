package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import movie.*;
import staff.Manager;

/** Manager screen for adding, updating, deleting, and exporting movies. */
public class ManagePanel extends JPanel {

    private final Manager manager;
    private final JTable table = new JTable();

    public ManagePanel(Manager manager) {
        this.manager = manager;
        setLayout(new BorderLayout());

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        refreshTable();
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        JButton addButton = new JButton("Add Movie");
        JButton updateButton = new JButton("Update Movie");
        JButton deleteButton = new JButton("Delete Movie");
        JButton exportButton = new JButton("Export Movies");
        buttons.add(addButton);
        buttons.add(updateButton);
        buttons.add(deleteButton);
        buttons.add(exportButton);
        add(buttons, BorderLayout.SOUTH);

        addButton.addActionListener(e -> handleAdd());
        updateButton.addActionListener(e -> handleUpdate());
        deleteButton.addActionListener(e -> handleDelete());
        exportButton.addActionListener(e -> handleExport());
    }

    private void refreshTable() {
        String[] columns = {"ID", "Category", "Title", "Director", "Duration", "Price", "Showtime", "Extra", "Tickets"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        for (Movie movie : manager.viewAllMovies()) {
            model.addRow(new Object[]{movie.getId(), categoryOf(movie), movie.getTitle(),
                    movie.getDirector(), movie.getDuration(), movie.getPrice(), movie.getShowtime(),
                    movie.getExtraAttribute(), movie.getAvailableTickets()});
        }
        table.setModel(model);
    }

    private String selectedMovieId() {
        int row = table.getSelectedRow();
        return row < 0 ? null : (String) table.getValueAt(row, 0);
    }

    private void handleAdd() {
        Movie movie = MovieEditorDialog.showDialog(this, null);
        if (movie == null) return;
        if (manager.addMovie(movie)) {
            refreshTable();
            JOptionPane.showMessageDialog(this, "Movie added.");
        } else {
            JOptionPane.showMessageDialog(this, "Could not add movie. Check that its ID is unique.",
                    "Add Movie", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleUpdate() {
        String id = selectedMovieId();
        if (id == null) {
            JOptionPane.showMessageDialog(this, "Select a movie to update.");
            return;
        }
        Movie current = manager.findById(id);
        Movie updated = MovieEditorDialog.showDialog(this, current);
        if (updated == null) return;
        if (manager.updateMovie(id, updated)) {
            refreshTable();
            JOptionPane.showMessageDialog(this, "Movie updated.");
        } else {
            JOptionPane.showMessageDialog(this, "Could not update movie.",
                    "Update Movie", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleDelete() {
        String id = selectedMovieId();
        if (id == null) {
            JOptionPane.showMessageDialog(this, "Select a movie to delete.");
            return;
        }
        int answer = JOptionPane.showConfirmDialog(this,
                "Delete movie " + id + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            if (manager.deleteMovie(id)) {
                refreshTable();
                JOptionPane.showMessageDialog(this, "Movie deleted.");
            } else {
                JOptionPane.showMessageDialog(this, "Could not delete movie.",
                        "Delete Movie", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void handleExport() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Export movie data");
        if (chooser.showSaveDialog(this) != JFileChooser.APPROVE_OPTION) return;
        File destination = chooser.getSelectedFile();
        if (destination.exists() && JOptionPane.showConfirmDialog(this,
                "Replace the selected file?", "Confirm Export", JOptionPane.YES_NO_OPTION)
                != JOptionPane.YES_OPTION) return;
        try {
            manager.exportMovies(destination);
            JOptionPane.showMessageDialog(this, "Movie data exported successfully.");
        } catch (IOException | IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Could not export movies: " + ex.getMessage(),
                    "Export Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String categoryOf(Movie movie) {
        if (movie instanceof ActionMovie) return "Action";
        if (movie instanceof ComedyMovie) return "Comedy";
        if (movie instanceof RomanceMovie) return "Romance";
        if (movie instanceof SciFiMovie) return "Science Fiction";
        return "Unknown";
    }
}
