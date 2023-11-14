package request;

/**
 * Class that creates a game request that CreateGameService calls
 */
public class CreateGameRequest {
    /**
     * Name of the game to create
     */
    private String gameName;
    /**
     * Takes in an authToken to see if the user is authorized
     */
    private String authToken;

    /**
     * Constructor for CreateGameRequest
     * @param gameName Takes in a String of the GameName
     * @param authToken Takes in an authToken to verify if the User is authorized
     */

    public CreateGameRequest(String gameName, String authToken) {
        this.gameName = gameName;
        this.authToken = authToken;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
