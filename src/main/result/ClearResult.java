package result;

/**
 * Class that creates a Clear Result that ClearService returns
 */
public class ClearResult {
    /**
     * Message to return after HTTP request
     */
    private String message;

    /**
     * Constructor for Clear Result
     * @param message Message returned if HTTP request fails
     */
    public ClearResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
