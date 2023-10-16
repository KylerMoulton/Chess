package result;

public class LogoutResult {
    private String message;
    private String authtoken;

    public LogoutResult(String message, String authtoken) {
        this.message = message;
        this.authtoken = authtoken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }
}
