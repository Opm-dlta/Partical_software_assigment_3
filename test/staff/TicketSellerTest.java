package staff;

import manager.MovieManager;
import movie.ActionMovie;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TicketSellerTest {

    // test 7
    // this test simulates the situation where there is only one ticket left
    // the first sale is successful, and the ticket count becomes 0
    // the second sale fails, and the ticket count remains 0
    // it verifies the ticket-selling logic and the rule that "no more than the available stock can be sold"
    //这项测试模拟只剩 1 张票，比如第一次售票成功，票数变成 0；第二次售票失败，票数仍保持0，它验证售票逻辑和“不能卖超过库存”的规则
    @Test
    void sellingTicketReducesCountAndStopsAtZero() {
        MovieManager movieManager = new MovieManager();

        ActionMovie movie = new ActionMovie(
                "BOOK001", "Test Movie", "Test Director",
                120, 15.0, "18:00", "High", 1
        );

        assertTrue(movieManager.addMovie(movie));

        TicketSeller seller =
                new TicketSeller("test-seller", "password", movieManager);

        assertTrue(seller.sellTicket("BOOK001"));
        assertEquals(0, movie.getAvailableTickets());

        assertFalse(seller.sellTicket("BOOK001"));
        assertEquals(0, movie.getAvailableTickets());
    }
}