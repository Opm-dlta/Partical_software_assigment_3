package manager;

import org.junit.jupiter.api.Test;
import staff.Staff;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class StaffManagerTest {

    // test 4
    // check correct Seller & Manager account can log in and return correct role
    // 测试确认正确的 Seller 和 Manager 账号会登录并返回对应角色
    @Test
    void acceptsValidSellerAndManagerAccounts() {
        StaffManager staffManager = new StaffManager(new MovieManager());

        Staff seller = staffManager.login("s1", "s1");
        Staff manager = staffManager.login("m1", "m1");

        assertEquals("Seller", seller.getRole());
        assertEquals("Manager", manager.getRole());
    }

    // test 5
    // confirm when password is wrong, log in fail.
    //测试确认密码错误时登录失败
    @Test
    void rejectsWrongPassword() {
        StaffManager staffManager = new StaffManager(new MovieManager());

        Staff result = staffManager.login("s1", "wrong");

        assertNull(result);
    }
}