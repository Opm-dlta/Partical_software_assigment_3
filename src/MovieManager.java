//this is the central  place where your program stores, loads, searches, updates, and deletes movies.
import movie.Movie;

import java.io.*;
import java.util.ArrayList;

/**
 * MovieManager is responsible for storing, loading, saving, and managing
 * all movie data in the Cinema Management System.

 * This class acts as the central "database" of the system.
 * - It loads movies from movies.txt at startup.
 * - It stores all Movie objects in an ArrayList.
 * - It provides search and lookup functions for Seller and Manager.
 * - It saves updated movie information back to movies.txt.
 
 * TicketSeller and Manager NEVER store movies themselves.
 * They always call MovieManager for movie-related operations.
 */
public class MovieManager {

    // Stores all movies in memory
    private final ArrayList<Movie> movies = new ArrayList<>();

    // Path to the movie data file
    private final String FILE_PATH = "movies.txt";

    /**
     * Constructor: loads all movies when the system starts.
     */
    public MovieManager() {
        loadMovies();
    }

    /**
     * Loads all movies from movies.txt into memory.
     * Each line in the file represents one movie.
     * The line is split into parts, and the correct Movie subclass is created.
     */
    private void loadMovies() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");

                // Extract movie attributes
                String category = parts[0];
                String id = parts[1];
                String title = parts[2];
                String director = parts[3];
                int duration = Integer.parseInt(parts[4]);
                double price = Double.parseDouble(parts[5]);
                String showtime = parts[6];
                String extra = parts[7];
                int tickets = Integer.parseInt(parts[8]);

                // Create the correct movie type (Action, Comedy, Romance, SciFi)
                Movie movie = createMovie(category, id, title, director, duration, price, showtime, extra, tickets);

                // Add movie to the list
                movies.add(movie);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the correct Movie subclass based on the category.
     * This supports polymorphism and keeps MovieManager clean.
     */
    private Movie createMovie(String category, String id, String title, String director,
                              int duration, double price, String showtime, String extra, int tickets) {

        return switch (category) {
            case "Action" -> new ActionMovie(id, title, director, duration, price, showtime, extra, tickets);
            case "Comedy" ->
                // Comedy movies do not use the "extra" attribute
                    new ComedyMovie(id, title, director, duration, price, showtime, tickets);
            case "Romance" -> new RomanceMovie(id, title, director, duration, price, showtime, extra, tickets);
            case "ScienceFiction" -> new SciFiMovie(id, title, director, duration, price, showtime, extra, tickets);
            default -> null;
        };
    }

    /**
     * Saves all movies back to movies.txt.
     * This is used when:
     * - Seller sells a ticket (ticket count changes)
     * - Manager adds, updates, or deletes a movie
     *
     * Each Movie class must implement toFileString() to format its data correctly.
     */
    public void saveMovies() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Movie m : movies) {
                pw.println(m.toFileString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns all movies.
     * Used by:
     * - TicketSeller (BrowsePanel)
     * - Manager (ManagePanel)
     */
    public Movie[] getAllMovies() {
        return movies.toArray(new Movie[0]);
    }

    /**
     * Searches movies by keyword (title match).
     * Used by TicketSeller in BrowsePanel.
     */
    public Movie[] searchMovies(String keyword) {
        ArrayList<Movie> result = new ArrayList<>();

        for (Movie m : movies) {
            if (m.getTitle().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(m);
            }
        }

        return result.toArray(new Movie[0]);
    }

    /**
     * Returns a movie by its ID.
     * Used by:
     * - TicketSeller (sellTicket, viewMovieDetails)
     * - Manager (update/delete movie)
     */
    public Movie getMovieById(String id) {
        for (Movie m : movies) {
            if (m.getId().equals(id)) {
                return m;
            }
        }
        return null;
    }
}
