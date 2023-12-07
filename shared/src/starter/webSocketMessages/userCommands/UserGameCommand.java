package webSocketMessages.userCommands;

import chess.ChessGame;

import java.util.Objects;

/**
 * Represents a command a user can send the server over a websocket
 * <p>
 * Note: You can add to this class, but you should not alter the existing
 * methods.
 */
public class UserGameCommand {

    public UserGameCommand(String authToken, Integer gameID) {
        this.authToken = authToken;
        this.gameID = gameID;
        this.color = null;
    }

    public UserGameCommand(String authToken, Integer gameID, ChessGame.TeamColor color, CommandType commandType) {
        this.authToken = authToken;
        this.gameID = gameID;
        this.color = color;
        this.commandType = commandType;
    }

    public enum CommandType {
        JOIN_PLAYER,
        JOIN_OBSERVER,
        MAKE_MOVE,
        LEAVE,
        RESIGN
    }

    protected CommandType commandType;

    private final String authToken;

    private final Integer gameID;

    private final ChessGame.TeamColor color;

    public Integer getGameID() {
        return gameID;
    }

    public String getAuthString() {
        return authToken;
    }

    public ChessGame.TeamColor getColor() {
        return color;
    }

    public CommandType getCommandType() {
        return this.commandType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof UserGameCommand))
            return false;
        UserGameCommand that = (UserGameCommand) o;
        return getCommandType() == that.getCommandType() && Objects.equals(getAuthString(), that.getAuthString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCommandType(), getAuthString());
    }
}
