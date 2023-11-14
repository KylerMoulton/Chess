package result;

/**
 * Class that creates a Create Game Result that CreateGameService returns
 */

public class CreateGameResult {
    /**
     * Auth token of authorized user
     */
    private String authToken;
    /**
     * GameID of the created game
     */
    private Integer gameID;
    /**
     * Message to return after HTTP request
     */
    private String message;

    /**
     * Constructor for CreateGameResult
     * @param gameID Takes in the gameID of the created game
     * @param message Message returned if HTTP request fails or is successful
     */
    public CreateGameResult(Integer gameID, String message) {
        this.gameID = gameID;
        this.message = message;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(Integer gameID) {
        this.gameID = gameID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
