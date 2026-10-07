package movie;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MovieCreationTest {

    // test 8
    // This test checks whether all the data can be correctly retrieved
    // basic on "getter" after a movie is created
    //这个测试检查创建电影后，各项 data 可否用 getter 取回
    @Test
    void actionMovieStoresItsDetails() {
        ActionMovie movie = new ActionMovie(
                "CREATE001",
                "Test Movie",
                "Test Director",
                120,
                15.0,
                "18:00",
                "High",
                50
        );

        assertEquals("CREATE001", movie.getId());
        assertEquals("Test Movie", movie.getTitle());
        assertEquals("Test Director", movie.getDirector());
        assertEquals(120, movie.getDuration());
        assertEquals("18:00", movie.getShowtime());
        assertEquals("High", movie.getExtraAttribute());
        assertEquals(50, movie.getAvailableTickets());
    }
}