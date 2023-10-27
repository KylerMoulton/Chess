package result;


import model.GameModel;

import java.util.Collection;

/**
 * Class that creates a List Games Result that JoinGameService returns
 */

public class ListGamesResult {
    /**
     * Set of all games in the Database
     */
    private Collection<GameModel> games;
    //    private int gameID;
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
     * @param gameModels Set of all games in the Database
     * @param message Message returned if HTTP request fails or is successful
     */

    public ListGamesResult(Collection<GameModel> gameModels, String message) {
        this.games = gameModels;
        this.message = message;
    }

    public ListGamesResult() {

    }

    public Collection<GameModel> getGamesList() {
        return games;
    }

    public void setGamesList(Collection<GameModel> gamesList) {
        this.games = gamesList;
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