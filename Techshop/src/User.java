public class User {
    private String username;
    private String email;
    private String password;
    private boolean isAdmin;

    public User(String email, String password, String name, boolean isAdmin) {
        this.username = name;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
    }

    public String getUsername(){
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public boolean isAdmin() {
        return this.isAdmin;
    }

    public boolean isEmpty() {
        return false;
    }

    
}
