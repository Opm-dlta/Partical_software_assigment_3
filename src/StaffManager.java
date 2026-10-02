// StaffManager handles all login and role checking.
// It stores the list of staff (sellers + managers),
// verifies username/password, and returns the correct
// Staff object so the GUI knows which features to enable.
// It does NOT manage movies or change staff accounts.
//
import java.util.ArrayList;
import java.util.List;

public class StaffManager {

    private List<Staff> staffList = new ArrayList<>();

    public void loadDefaultStaff() {
        staffList.add(new TicketSeller("s1", "s1"));
        staffList.add(new TicketSeller("s2", "s2"));
        staffList.add(new TicketSeller("s3", "s3"));
        staffList.add(new Manager("m1", "m1"));
        staffList.add(new Manager("m2", "m2"));
    }

    public Staff login(String username, String password) {
        for (Staff s : staffList) {
            if (s.login(username, password)) {
                return s;
            }
        }
        return null;
    }
}
