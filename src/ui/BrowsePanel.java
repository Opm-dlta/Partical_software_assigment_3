package ui;
// put all ui and background here
import javax.swing.*;
import java.awt.*;
import staff.TicketSeller;
import movie.Movie;

/**
 * ============================================================
 * BrowsePanel
 * ------------------------------------------------------------
 * PURPOSE:
 *   Seller's movie browsing + ticket selling interface.
 *
 * PACKAGE:
 *   src/main/java/ui/
 *
 * FEATURES:
 *   - Search movies
 *   - View all movies
 *   - Sell tickets
 *
 * DEPENDENCIES:
 *   - TicketSeller (backend operations)
 * ============================================================
 */
public class BrowsePanel extends JPanel {

    private TicketSeller seller;
    private JTable table;
    private JTextField searchField;

    public BrowsePanel(TicketSeller seller) {
        this.seller = seller;

        setLayout(new BorderLayout());

        // ===== Top search bar =====
        JPanel top = new JPanel();
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        top.add(new JLabel("Search:"));
        top.add(searchField);
        top.add(searchButton);
        add(top, BorderLayout.NORTH);

        // ===== Movie table =====
        table = new JTable();
        refreshTable(seller.viewAllMovies());
        add(new JScrollPane(table), BorderLayout.CENTER);

        // ===== Sell button =====
        JButton sellButton = new JButton("Sell Ticket");
        add(sellButton, BorderLayout.SOUTH);

        // ===== Event handlers =====
        searchButton.addActionListener(e -> {
            String keyword = searchField.getText();
            refreshTable(seller.searchMovies(keyword));
        });

        sellButton.addActionListener(e -> handleSell());
    }

    /**
     * Refresh table with given movie list.
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

    /**
     * Sell ticket for selected movie.
     */
    private void handleSell() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select a movie first");
            return;
        }

        String movieId = (String) table.getValueAt(row, 0);

        if (seller.sellTicket(movieId)) {
            JOptionPane.showMessageDialog(this, "Ticket sold!");
            refreshTable(seller.viewAllMovies());
        } else {
            JOptionPane.showMessageDialog(this, "Cannot sell ticket");
        }
    }
}
