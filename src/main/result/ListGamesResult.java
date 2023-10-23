package result;


import java.util.Set;

/**
 * Class that creates a List Games Result that JoinGameService returns
 */

public class ListGamesResult {
    /**
     * Set of all games in the Database
     */
    private Set<Games> gamesList;
    /**
     * Game ID of the game
     */
    private int gameID;
    /**
     * White players username (Can be null)
     */
    private String whiteUsername;
    /**
     * Black players username (Can be null)
     */
    private String blackUsername;
    /**
     * Name of the game
     */
    private String gameName;
    /**
     * Takes in an authToken to check if the User is authorized
     */
    private String authToken;
    /**
     * Message returned after HTTP request
     */
    private String message;

    /**
     * Constructor for ListGamesResult
     * @param gamesList Set of all games in the Database
     * @param gameID Game ID of the game
     * @param whiteUsername White players username (Can be null)
     * @param blackUsername Black players username (Can be null)
     * @param gameName Name of the game
     * @param authToken Takes in an authToken to check if the User is authorized
     * @param message Message returned if HTTP request fails or is successful
     */

    public ListGamesResult(Set<Games> gamesList, int gameID, String whiteUsername, String blackUsername, String gameName, String authToken, String message) {
        this.gamesList = gamesList;
        this.gameID = gameID;
        this.whiteUsername = whiteUsername;
        this.blackUsername = blackUsername;
        this.gameName = gameName;
        this.authToken = authToken;
        this.message = message;
    }

    /**
     * Class that creates a gameObject to store in gameList
     */
    private class Games {
        /**
         * Game ID of the game
         */
        private int gameID;
        /**
         * White players username (Can be null)
         */
        private String whiteUsername;
        /**
         * Black players username (Can be null)
         */
        private String blackUsername;
        /**
         * Name of the game
         */
        private String gameName;

        /**
         * Constructor for Games
         * @param gameID Takes in the gameID of the game
         * @param whiteUsername Takes in the White players username
         * @param blackUsername Takes in the Black players username
         * @param gameName Takes in the name of the Game
         */
        public Games(int gameID, String whiteUsername, String blackUsername, String gameName) {
            StringBuilder game = new StringBuilder();
            game.append("gameID:").append(gameID).append(", whiteUsername:").append(whiteUsername).append(", blackUsername:").append(blackUsername).append("gameName:").append(gameName);
        }

    }
    public Set<Games> getGamesList() {
        return gamesList;
    }

    public void setGamesList(Set<Games> gamesList) {
        this.gamesList = gamesList;
    }

    /**
     * Adds to gamesList
     * @param gameObject Takes in a gameObject that contains game information
     */
    public void addToSetGamesList(Games gameObject) {
        gamesList.add(gameObject);
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