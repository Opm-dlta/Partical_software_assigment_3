import javax.swing.*;
import java.awt.*;

public class BrowsePanel extends JPanel {

    private TicketSeller seller;
    private JTable table;
    private JTextField searchField;

    public BrowsePanel(TicketSeller seller) {
        this.seller = seller;

        setLayout(new BorderLayout());

        // Top search bar
        JPanel top = new JPanel();
        searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");
        top.add(new JLabel("Search:"));
        top.add(searchField);
        top.add(searchButton);
        add(top, BorderLayout.NORTH);

        // Table of movies
        table = new JTable();
        refreshTable(seller.viewAllMovies());
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Sell ticket button
        JButton sellButton = new JButton("Sell Ticket");
        add(sellButton, BorderLayout.SOUTH);

        searchButton.addActionListener(e -> {
            String keyword = searchField.getText();
            refreshTable(seller.searchMovies(keyword));
        });

        sellButton.addActionListener(e -> handleSell());
    }

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
