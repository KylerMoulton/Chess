package result;

/**
 * Class that creates a Login Result that LoginService returns
 */

public class LogoutResult {
    /**
     * Message returned after HTTP request
     */
    private String message;
    /**
     * Takes in an authToken to check if the User is authorized
     */
    private String authToken;

    /**
     * Constructor for LogoutResult
     * @param authToken Takes in an authToken to check if the User is authorized
     * @param message Message returned if HTTP request fails or is successful
     */
    public LogoutResult(String message, String authToken) {
        this.message = message;
        this.authToken = authToken;
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
}
