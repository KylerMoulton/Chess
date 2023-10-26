package result;


import model.GameModel;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Class that creates a List Games Result that JoinGameService returns
 */

public class ListGamesResult {
    /**
     * Set of all games in the Database
     */
    private Set<Set<String>> games;
    /**
     * Game ID of the game
     */
//    private int gameID;
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
     * @param gameModels Set of all games in the Database
     * @param message Message returned if HTTP request fails or is successful
     */

    public ListGamesResult(Collection<GameModel> gameModels, String message) {
        Set<Set<String>> gameObjects = new HashSet<>();
        for (GameModel game : gameModels) {
            Games newGame = new Games(game.getGameID(), game.getWhiteUsername(), game.getBlackUsername(), game.getGameName());
            Set<String> newGameSet = newGame.gameInfo();
            gameObjects.add(newGameSet);
        }
        this.games = gameObjects;
        this.message = message;
    }

    /**
     * Class that creates a gameObject to store in gameList
     */
        public static class Games {
            private final int gameID2;
            private final String whiteUsername2;
            private final String blackUsername2;
            private final String gameName2;
        /**
         * Constructor for Games
         *
         * @param gameID2        Takes in the gameID of the game
         * @param whiteUsername2 Takes in the White players username
         * @param blackUsername2 Takes in the Black players username
         * @param gameName2      Takes in the name of the Game
         */
            public Games(int gameID2, String whiteUsername2, String blackUsername2, String gameName2) {
                this.gameID2 = gameID2;
                this.whiteUsername2 = whiteUsername2;
                this.blackUsername2 = blackUsername2;
                this.gameName2 = gameName2;
            }

            public Set<String> gameInfo() {
                    Set<String> gameInfoSet = new HashSet<>();
                    gameInfoSet.add(Integer.toString(gameID2));
                    gameInfoSet.add(whiteUsername2);
                    gameInfoSet.add(blackUsername2);
                    gameInfoSet.add(gameName2);
                    return gameInfoSet;
                }
        }
    public Set<Set<String>> getGamesList() {
        return games;
    }

    public void setGamesList(Set<Set<String>> gamesList) {
        this.games = gamesList;
    }

//    public int getGameID() {
//        return gameID;
//    }
//
//    public void setGameID(int gameID) {
//        this.gameID = gameID;
//    }

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