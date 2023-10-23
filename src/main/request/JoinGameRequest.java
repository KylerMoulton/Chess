package request;

/**
 * Class that creates a Join Game request that JoinGameService calls
 */

public class JoinGameRequest {
    /**
     * Takes in an authToken to see if the user is authorized
     */
    private String authToken;
    /**
     * Takes in what color the player wants to be
     */
    private String playerColor;
    /**
     * Takes in the gameID of the game the player wants to join
     */
    private int gameID;

    /**
     * Constructor for CreateGameRequest
     * @param authToken Takes in an authToken to verify if the User is authorized
     * @param playerColor Takes in the color of what team the User wants to join
     * @param gameID Takes in the gameID of the game to join
     */

    public JoinGameRequest(String authToken, String playerColor, int gameID) {
        this.authToken = authToken;
        this.playerColor = playerColor;
        this.gameID = gameID;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(String playerColor) {
        this.playerColor = playerColor;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }
}
