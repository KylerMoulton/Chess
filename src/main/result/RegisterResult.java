package result;

/**
 * Class that creates a Register Result that LoginService returns
 */
public class RegisterResult {
    private String message;
    private String authToken;
    private String username;

    /**
     * Constructor for RegisterResult
     * @param authToken Takes in an authToken to check if the User is authorized
     * @param message Message returned if HTTP request fails or is successful
     * @param username Takes in the username of the User who logged in
     */
    public RegisterResult(String message, String authToken, String username) {
        this.message = message;
        this.authToken = authToken;
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
