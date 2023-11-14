package model;

import chess.ChessGame;

import java.util.Objects;

/**
 * Creates a Game
 */
public class GameModel implements Comparable{
    /**
     * Game ID of the game
     */
    private int gameID;
    /**
     * Username of White user
     */
    private String whiteUsername;
    /**
     * Username of Black user
     */
    private String blackUsername;
    /**
     * Name of the game
     */
    private String gameName;
    /**
     * New game implementation
     */
    private ChessGame game;

    /**
     * Constructor for the GameModel
     * @param gameID Game ID of the game
     * @param whiteUsername Username of the White user
     * @param blackUsername Username of the Black user
     * @param gameName Name of the game
     * @param game New game imple
     */

    public GameModel(int gameID, String whiteUsername, String blackUsername, String gameName, ChessGame game) {
        this.gameID = gameID;
        this.whiteUsername = whiteUsername;
        this.blackUsername = blackUsername;
        this.gameName = gameName;
        this.game = game;
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

    public ChessGame getGame() {
        return game;
    }

    public void setGame(ChessGame game) {
        this.game = game;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameModel gameModel = (GameModel) o;
        return gameID == gameModel.gameID && Objects.equals(whiteUsername, gameModel.whiteUsername) && Objects.equals(blackUsername, gameModel.blackUsername) && Objects.equals(gameName, gameModel.gameName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameID, whiteUsername, blackUsername, gameName);
    }

    @Override
    public int compareTo(Object o) {
        return o.hashCode()-this.hashCode();
    }
}

