package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import movie.Movie;
import staff.MovieBrowser;

/** Shared movie browsing and ticket-sale screen for sellers and managers. */
public class BrowsePanel extends JPanel {

    private final MovieBrowser browser;
    private final JTable table = new JTable();
    private final JComboBox<String> categoryBox = new JComboBox<>(new String[]{
            "All", "Action", "Comedy", "Romance", "Science Fiction"
    });
    private final JTextField titleField = new JTextField(18);

    public BrowsePanel(MovieBrowser browser) {
        this.browser = browser;
        setLayout(new BorderLayout(8, 8));

        JPanel searchPanel = new JPanel();
        JButton searchButton = new JButton("Search");
        JButton showAllButton = new JButton("Show All");
        searchPanel.add(new JLabel("Category:"));
        searchPanel.add(categoryBox);
        searchPanel.add(new JLabel("Title:"));
        searchPanel.add(titleField);
        searchPanel.add(searchButton);
        searchPanel.add(showAllButton);
        add(searchPanel, BorderLayout.NORTH);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        refreshTable(browser.viewAllMovies());
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel actionPanel = new JPanel();
        JButton detailsButton = new JButton("View Details");
        JButton sellButton = new JButton("Sell Ticket");
        actionPanel.add(detailsButton);
        actionPanel.add(sellButton);
        add(actionPanel, BorderLayout.SOUTH);

        searchButton.addActionListener(e -> applySearch());
        showAllButton.addActionListener(e -> {
            categoryBox.setSelectedItem("All");
            titleField.setText("");
            refreshTable(browser.viewAllMovies());
        });
        detailsButton.addActionListener(e -> showSelectedDetails());
        sellButton.addActionListener(e -> sellSelectedTicket());
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) showSelectedDetails();
            }
        });
    }

    private void applySearch() {
        String category = (String) categoryBox.getSelectedItem();
        if ("All".equals(category)) category = "";
        refreshTable(browser.searchMovies(category, titleField.getText()));
    }

    private void refreshTable(Movie[] movies) {
        String[] columns = {"ID", "Title", "Director", "Duration", "Price", "Showtime", "Extra", "Tickets"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        for (Movie movie : movies) {
            model.addRow(new Object[]{movie.getId(), movie.getTitle(), movie.getDirector(),
                    movie.getDuration(), movie.getPrice(), movie.getShowtime(),
                    movie.getExtraAttribute(), movie.getAvailableTickets()});
        }
        table.setModel(model);
    }

    private String selectedMovieId() {
        int row = table.getSelectedRow();
        return row < 0 ? null : (String) table.getValueAt(row, 0);
    }

    private void showSelectedDetails() {
        String id = selectedMovieId();
        if (id == null) {
            JOptionPane.showMessageDialog(this, "Select a movie first.");
            return;
        }
        Movie movie = browser.findById(id);
        String details = "ID: " + movie.getId()
                + "\nTitle: " + movie.getTitle()
                + "\nDirector: " + movie.getDirector()
                + "\nDuration: " + movie.getDuration() + " minutes"
                + "\nPrice: $" + movie.getPrice()
                + "\nShowtime: " + movie.getShowtime()
                + "\nCategory detail: " + movie.getExtraAttribute()
                + "\nAvailable tickets: " + movie.getAvailableTickets();
        JOptionPane.showMessageDialog(this, details, "Movie Details", JOptionPane.INFORMATION_MESSAGE);
    }

    private void sellSelectedTicket() {
        String id = selectedMovieId();
        if (id == null) {
            JOptionPane.showMessageDialog(this, "Select a movie first.");
            return;
        }
        if (browser.sellTicket(id)) {
            JOptionPane.showMessageDialog(this, "Ticket sold.");
            applySearch();
        } else {
            JOptionPane.showMessageDialog(this, "Ticket sale failed. No tickets may remain.");
        }
    }
}
