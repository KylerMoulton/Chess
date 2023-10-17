package result;


import java.util.Map;

/**
 * Class that creates a List Games Result that JoinGameService returns
 */

public class ListGamesResult {
    private Map<String,String> gamesList;
    private int gameID;
    private String whiteUsername;
    private String blackUsername;
    private String gameName;
    private String authToken;
    private String message;

    /**
     * Constructor for ListGamesResult
     * @param gamesList Map of all games in the Database
     * @param gameID Game ID of the game
     * @param whiteUsername White players username (Can be null)
     * @param blackUsername Black players username (Can be null)
     * @param gameName Name of the game
     * @param authToken Takes in an authToken to check if the User is authorized
     * @param message Message returned if HTTP request fails or is successful
     */

    public ListGamesResult(Map<String, String> gamesList, int gameID, String whiteUsername, String blackUsername, String gameName, String authToken, String message) {
        this.gamesList = gamesList;
        this.gameID = gameID;
        this.whiteUsername = whiteUsername;
        this.blackUsername = blackUsername;
        this.gameName = gameName;
        this.authToken = authToken;
        this.message = message;
    }

    public Map<String, String> getGamesList() {
        return gamesList;
    }

    public void setGamesList(Map<String, String> gamesList) {
        this.gamesList = gamesList;
    }
    public void addToSetGamesList(Map<String,String> gamesList,String key,String value) {
        gamesList.put(key,value);
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public String getWhiteUsername() {
        return whiteUsername;
    }

    public void setWhiteUsername(String whiteUsername) {
        this.whiteUsername = whiteUsername;
    }

    public String getBlackUsername() {
        return blackUsername;
    }

    public void setBlackUsername(String blackUsername) {
        this.blackUsername = blackUsername;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}