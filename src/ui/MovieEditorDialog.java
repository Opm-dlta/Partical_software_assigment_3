package ui;

import movie.*;
import javax.swing.*;
import java.awt.*;

/** Small reusable form for creating or editing a movie record. */
final class MovieEditorDialog {

    private MovieEditorDialog() { }

    static Movie showDialog(Component parent, Movie existing) {
        JComboBox<String> category = new JComboBox<>(new String[]{
                "Action", "Comedy", "Romance", "Science Fiction"
        });
        JTextField id = new JTextField(existing == null ? "" : existing.getId());
        JTextField title = new JTextField(existing == null ? "" : existing.getTitle());
        JTextField director = new JTextField(existing == null ? "" : existing.getDirector());
        JTextField duration = new JTextField(existing == null ? "" : String.valueOf(existing.getDuration()));
        JTextField price = new JTextField(existing == null ? "" : String.valueOf(existing.getPrice()));
        JTextField showtime = new JTextField(existing == null ? "" : existing.getShowtime());

        // by Johnson Oct 7
        // change input to choose an option
        // like action movie, manager just need to choose a level on it, no need to type it
        // previous code: (check it no problem then delete this comment.)
//        JTextField extra = new JTextField(existing == null ? "" : existing.getExtraAttribute());
        JComboBox<String> extra = new JComboBox<>();

        JTextField tickets = new JTextField(existing == null ? "50" : String.valueOf(existing.getAvailableTickets()));


        // basic on the movie kind to add extra attribute option.
        // 根据当前类别填入额外属性选项
        updateExtraOptions(category, extra);

        // change movie, choose the original extra attribute.
        // 修改电影时，选中它原来的额外属性
        if (existing != null) {
            category.setSelectedItem(categoryOf(existing));
            id.setEditable(false);
        }
        // 编辑已有电影时，恢复它原来的额外属性
        if (existing != null) {
            extra.setSelectedItem(existing.getExtraAttribute());
        }

        // when change kind, renew extra attribute option.
        // 更改类别时，重新更新额外属性选项
        category.addActionListener(event -> updateExtraOptions(category, extra));

        JPanel fields = new JPanel(new GridLayout(9, 2, 6, 6));
        fields.add(new JLabel("Category:")); fields.add(category);
        fields.add(new JLabel("Movie ID:")); fields.add(id);
        fields.add(new JLabel("Title:")); fields.add(title);
        fields.add(new JLabel("Director:")); fields.add(director);
        fields.add(new JLabel("Duration (minutes):")); fields.add(duration);
        fields.add(new JLabel("Price:")); fields.add(price);
        fields.add(new JLabel("Showtime:")); fields.add(showtime);
        fields.add(new JLabel("Category detail:")); fields.add(extra);
        fields.add(new JLabel("Available tickets:")); fields.add(tickets);

        int result = JOptionPane.showConfirmDialog(parent, fields,
                existing == null ? "Add Movie" : "Update Movie",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result != JOptionPane.OK_OPTION) return null;

        try {
            String movieId = id.getText().trim();
            String movieTitle = title.getText().trim();
            String movieDirector = director.getText().trim();
            String movieShowtime = showtime.getText().trim();
            // not Text, so fix
            // previous code
//            String categoryDetail = extra.getText().trim();
            String categoryDetail = (String) extra.getSelectedItem();
            if (movieId.isEmpty() || movieTitle.isEmpty() || movieDirector.isEmpty()
                    || movieShowtime.isEmpty() || categoryDetail.isEmpty()) {
                throw new IllegalArgumentException("Fill in all text fields.");
            }

            int movieDuration = Integer.parseInt(duration.getText().trim());
            double moviePrice = Double.parseDouble(price.getText().trim());
            int ticketCount = Integer.parseInt(tickets.getText().trim());
            if (movieDuration <= 0 || moviePrice < 0 || ticketCount < 0) {
                throw new IllegalArgumentException("Duration must be positive; price and tickets cannot be negative.");
            }

            String selectedCategory = (String) category.getSelectedItem();
            switch (selectedCategory) {
                case "Action":
                    return new ActionMovie(movieId, movieTitle, movieDirector, movieDuration,
                            moviePrice, movieShowtime, categoryDetail, ticketCount);
                case "Comedy":
                    return new ComedyMovie(movieId, movieTitle, movieDirector, movieDuration,
                            moviePrice, movieShowtime, categoryDetail, ticketCount);
                case "Romance":
                    return new RomanceMovie(movieId, movieTitle, movieDirector, movieDuration,
                            moviePrice, movieShowtime, categoryDetail, ticketCount);
                case "Science Fiction":
                    return new SciFiMovie(movieId, movieTitle, movieDirector, movieDuration,
                            moviePrice, movieShowtime, categoryDetail, ticketCount);
                default:
                    throw new IllegalArgumentException("Choose a movie category.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(parent, "Duration, price, and tickets must be valid numbers.",
                    "Invalid Movie Details", JOptionPane.ERROR_MESSAGE);
            return null;
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(parent, ex.getMessage(),
                    "Invalid Movie Details", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    private static String categoryOf(Movie movie) {
        if (movie instanceof ActionMovie) return "Action";
        if (movie instanceof ComedyMovie) return "Comedy";
        if (movie instanceof RomanceMovie) return "Romance";
        if (movie instanceof SciFiMovie) return "Science Fiction";
        return "Action";
    }

    // by Johnson Oct 7
    // basic on movie kind, setting extra attributes
    // like if this action movie, manager can set diff age level
    // 根据所选电影类别，设置允许的额外属性，也就是给电影分级
    private static void updateExtraOptions(
            JComboBox<String> categoryBox,
            JComboBox<String> extraBox) {

        // read value and switch to String
        String selectedCategory = (String) categoryBox.getSelectedItem();
        String[] options;

        if ("Action".equals(selectedCategory)) {
            options = new String[]{"High", "Medium", "Extreme"};
        } else if ("Romance".equals(selectedCategory)) {
            options = new String[]{"PG", "R13", "R16"};
        } else if ("Science Fiction".equals(selectedCategory)) {
            options = new String[]{"IMAX", "3D"};
        } else {
            // comedy extra attribute follows Ass 3 request, "-"
            // Comedy 的额外属性按作业数据使用 "-"
            options = new String[]{"-"};
        }

        extraBox.setModel(new DefaultComboBoxModel<>(options));
    }
}
