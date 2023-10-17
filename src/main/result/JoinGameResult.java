package result;

/**
 * Class that creates a Join Game Result that JoinGameService returns
 */

public class JoinGameResult {
    private String authToken;
    private String message;

    /**
     * Constructor for JoinGameResult
     * @param authToken Takes in an authToken to check if the User is authorized
     * @param message Message returned if HTTP request fails or is successful
     */
    public JoinGameResult(String authToken, String message) {
        this.authToken = authToken;
        this.message = message;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
