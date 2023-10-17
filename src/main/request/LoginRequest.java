package request;

/**
 * Class that creates a Login request that CreateGameService calls
 */
public class LoginRequest {
    private String username;
    private String password;

    /**
     * Constructor for LoginRequest
     * @param username Username of the user attempting to log in
     * @param password Password of the user attempting to log in
     */
    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
