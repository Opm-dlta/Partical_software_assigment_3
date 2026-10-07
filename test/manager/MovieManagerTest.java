package manager;

import movie.ActionMovie;
import movie.Movie;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertNull;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertFalse;

class MovieManagerTest {

    // test 1
    // After adding a movie with a unique ID,
    // does MovieManager successfully save it and can locate it by its ID?
    @Test
    void addMovieWithUniqueId() {
        // 创建 MovieManager。它会读取项目根目录的 movies.txt
        MovieManager movieManager = new MovieManager();

        // 使用专门的测试 ID，避免和现有电影 ID 重复
        Movie movie = new ActionMovie(
                "TEST001",
                "Test Movie",
                "Test Director",
                120,
                15.0,
                "18:00",
                "High",
                5
        );

        // 检查新增是否成功
        assertTrue(movieManager.addMovie(movie));

        // 检查新增的电影是否能通过 ID 找回来
        assertSame(movie, movieManager.findById("TEST001"));
    }

    // test 2
    // Can add a new movie with a unique ID
    @Test
    void updateMovieChangesDetailsButKeepsId() {
        MovieManager movieManager = new MovieManager();

        Movie original = new ActionMovie(
                "TEST002", "Old Title", "Test Director",
                120, 15.0, "18:00", "High", 5
        );

        Movie updated = new ActionMovie(
                "TEST002", "New Title", "Test Director",
                120, 15.0, "18:00", "High", 5
        );

        assertTrue(movieManager.addMovie(original));
        assertTrue(movieManager.updateMovie("TEST002", updated));
        assertSame(updated, movieManager.findById("TEST002"));
    }

    // test 3
    // delete movie test
    @Test
    void deleteMovieRemovesIt() {
        MovieManager movieManager = new MovieManager();

        Movie movie = new ActionMovie(
                "TEST003", "Test Movie", "Test Director",
                120, 15.0, "18:00", "High", 5
        );

        assertTrue(movieManager.addMovie(movie));
        assertTrue(movieManager.deleteMovie("TEST003"));
        assertNull(movieManager.findById("TEST003"));
    }

    // test 6
    // First, it added a test movie.
    // Then, it conducted a search using the category "Action" & partial movie titles like "unique search".
    // Finally, it confirmed that only this movie was found.
    //它先加入一部测试电影，再用类别 Action 和部分片名 unique search 搜索，最后确认只找到这部电影。
    @Test
    void searchFindsMovieByCategoryAndPartialTitle() {
        MovieManager movieManager = new MovieManager();

        Movie movie = new ActionMovie(
                "SEARCH001", "ZZZ Unique Search Film", "Test Director",
                120, 15.0, "18:00", "High", 5
        );

        assertTrue(movieManager.addMovie(movie));

        Movie[] results = movieManager.searchMovies("Action", "unique search");

        assertEquals(1, results.length);
        assertSame(movie, results[0]);
    }


    // test 9
    // it confirms that duplicate IDs will be rejected,
    // and the original movie information has not been overwritten
    //它确认重复 ID 会被拒绝，而且原来的电影资料没有被覆盖。
    @Test
    void addMovieRejectsDuplicateId() {
        MovieManager movieManager = new MovieManager();

        Movie original = new ActionMovie(
                "DUP001", "Original Movie", "Test Director",
                120, 15.0, "18:00", "High", 5
        );

        Movie duplicate = new ActionMovie(
                "DUP001", "Duplicate Movie", "Another Director",
                100, 12.0, "20:00", "Medium", 3
        );

        assertTrue(movieManager.addMovie(original));
        assertFalse(movieManager.addMovie(duplicate));
        assertSame(original, movieManager.findById("DUP001"));
    }

    // test 10
    // test all movie from movie.txt file can be read
    //这个测试是看txt文件里面的电影能都全部读取
    @Test
    void loadsAllMoviesIncludingScienceFiction() {
        MovieManager movieManager = new MovieManager();

        assertEquals(32, movieManager.getAllMovies().length);
        assertEquals(8, movieManager.searchMovies("Science Fiction", "").length);
    }
}