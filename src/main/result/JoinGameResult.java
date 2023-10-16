package result;

public class JoinGameResult {
    private String authToken;
    private String message;

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
