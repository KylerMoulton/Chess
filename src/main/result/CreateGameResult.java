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
    private int gameID;
    /**
     * Message to return after HTTP request
     */
    private String message;

    /**
     * Constructor for CreateGameResult
     * @param authToken Takes in an authToken to check if the User is authorized
     * @param gameID Takes in the gameID of the created game
     * @param message Message returned if HTTP request fails or is successful
     */
    public CreateGameResult(String authToken, Integer gameID, String message) {
        this.authToken = authToken;
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

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
