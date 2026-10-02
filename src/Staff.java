//superclass for organisting and match username and pass for childclass manger and sell

public abstract class Staff {
    protected String username;
    protected String password;

    public Staff(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean login(String u, String p) {
        return username.equals(u) && password.equals(p);
    }

    public abstract String getRole();
}
