//this is the central  place where your program stores, loads, searches, updates, and deletes movies.
package manager;

import movie.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * MovieManager
 * ------------------------------------------------------------
 * PURPOSE:
 *   Handles all movie-related operations:
 *     - Load movies from movies.txt
 *     - Save movies back to movies.txt
 *     - Search movies by ID
 *     - Provide movie list to UI panels
 *
 * PACKAGE:
 *   src/main/java/manager/
 *
 * USED BY:
 *   - TicketSeller (sell tickets)
 *   - Manager (view/manage movies)
 *   - StaffManager (shared instance)
 * ============================================================
 */
public class MovieManager {

    private List<Movie> movies = new ArrayList<>();

    // Path to movies.txt (relative to project root)
    private static final String FILE_PATH = "movies.txt";
    /**
     * Constructor
     * --------------------------------------------------------
     * Loads movies immediately when MovieManager is created.
     */
    public MovieManager() {
        loadMovies();
    }

    /**
     * Return all movies as an array.
     */
    public Movie[] getAllMovies() {
        return movies.toArray(new Movie[0]);
    }

    /**
     * Find movie by ID.
     * @return Movie or null if not found.
     */
    public Movie findById(String id) {
        for (Movie m : movies) {
            if (m.getId().equals(id)) return m;
        }
        return null;
    }

    /** Add a movie if its ID is not already in use. */
    public boolean addMovie(Movie movie) {
        if (movie == null || findById(movie.getId()) != null) return false;
        movies.add(movie);
        return true;
    }

    /** Replace a movie's details while keeping its ID unchanged. */
    public boolean updateMovie(String id, Movie updatedMovie) {
        if (id == null || updatedMovie == null || !id.equals(updatedMovie.getId())) return false;
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).getId().equals(id)) {
                movies.set(i, updatedMovie);
                return true;
            }
        }
        return false;
    }

    /** Remove a movie by ID. */
    public boolean deleteMovie(String id) {
        if (id == null) return false;
        return movies.removeIf(movie -> movie.getId().equals(id));
    }

    /**
     * Load movies from movies.txt.
     * --------------------------------------------------------
     * Expected format:
     *   Category, ID, Title, Director, Duration, Price, Showtime, Extra, Tickets
     *
     * Category must match:
     *   - Action
     *   - Comedy
     *   - Romance
     *   - SciFi
     */
    private void loadMovies() {
        movies.clear();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length < 9) continue;

                String category = parts[0].trim();
                String id = parts[1].trim();
                String title = parts[2].trim();
                String director = parts[3].trim();
                int duration = Integer.parseInt(parts[4].trim());
                double price = Double.parseDouble(parts[5].trim());
                String showtime = parts[6].trim();
                String extra = parts[7].trim();
                int tickets = Integer.parseInt(parts[8].trim());

                Movie m = null;

                switch (category) {
                    case "Action":
                        m = new ActionMovie(id, title, director, duration, price, showtime, extra, tickets);
                        break;

                    case "Comedy":
                        m = new ComedyMovie(id, title, director, duration, price, showtime, extra, tickets);
                        break;

                    case "Romance":
                        m = new RomanceMovie(id, title, director, duration, price, showtime, extra, tickets);
                        break;

                    case "Science Fiction":
                    case "SciFi":
                        m = new SciFiMovie(id, title, director, duration, price, showtime, extra, tickets);
                        break;
                }

                if (m != null) movies.add(m);
            }

        } catch (Exception e) {
            System.out.println("Error loading movies: " + e.getMessage());
        }
    }

    /**
     * Save movies back to movies.txt.
     * Called after selling tickets or manager updates.
     */
    public void saveMovies() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))) {

            for (Movie m : movies) {
                pw.println(m.toFileString());
            }

        } catch (Exception e) {
            System.out.println("Error saving movies: " + e.getMessage());
        }
    }
}