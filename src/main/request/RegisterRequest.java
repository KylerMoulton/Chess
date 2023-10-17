package request;

/**
 * Class that creates a Register request that RegisterService calls
 */
public class RegisterRequest {
    private String username;
    private String password;
    private String email;

    /**
     * Constructor for RegisterRequest
     * @param username Username that the user is requesting to use
     * @param password Password that the user is requesting to use
     * @param email Email that the user is requesting to use
     */

    public RegisterRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
