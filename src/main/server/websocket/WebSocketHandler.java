package server.websocket;

import chess.ChessGame;
import chess.ChessMove;
import chess.ChessPosition;
import chess.InvalidMoveException;
import com.google.gson.Gson;
import dataAccess.*;
import model.GameModel;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import serverMessages.errorMessage;
import serverMessages.loadGame;
import serverMessages.notificationMessage;
import webSocketMessages.userCommands.UserGameCommand;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;


@WebSocket
public class WebSocketHandler {

    private final ConnectionManager connections = new ConnectionManager();

    @OnWebSocketMessage
    public void onMessage(Session session, String message) throws IOException, SQLException, DataAccessException, InvalidMoveException {
        UserGameCommand action = new Gson().fromJson(message, UserGameCommand.class);
        switch (action.getCommandType()) {
            case JOIN_PLAYER -> joinPlayer(action.getGameID(), action.getAuthString(), action.getColor(), session);
            case JOIN_OBSERVER -> observeGame(action.getGameID(), action.getAuthString(), session);
            case MAKE_MOVE -> makeMove(action.getGameID(), action.getAuthString(), action.getMove(), session);
            case RESIGN -> resign(action.getGameID(), action.getAuthString(), session);
        }
    }

    private void joinPlayer(Integer gameID, String authToken, ChessGame.TeamColor color, Session session) throws IOException, SQLException, DataAccessException {
        GameDAO game = new GameDAO();
        AuthDAO auth = new AuthDAO();
        connections.add(gameID, authToken, session);
        GameModel curGame = null;
        for (GameModel games : game.getAllGames()) {
            if (games.getGameID() == gameID) {
                curGame = games;
            }
        }
        try {
            if (auth.getToken(authToken) == null) {
                var errorNotification = new errorMessage("Error: You are not authorized");
                session.getRemote().sendString(new Gson().toJson(errorNotification));
                return;
            }
        } catch (SQLException e) {
            var errorNotification = new errorMessage("Error: You are not authorized");
            session.getRemote().sendString(new Gson().toJson(errorNotification));
            return;
        }
        if (curGame == null) {
            var errorNotification = new errorMessage("Error: Game doesn't exist");
            session.getRemote().sendString(new Gson().toJson(errorNotification));
        }

        assert curGame != null;
        if ((Objects.equals(auth.getToken(authToken).getUsername(), curGame.getWhiteUsername()) && color.equals(ChessGame.TeamColor.WHITE)) || (Objects.equals(auth.getToken(authToken).getUsername(), curGame.getBlackUsername()) && color.equals(ChessGame.TeamColor.BLACK))) {
            var loadGameNotification = new loadGame(curGame.getGame());
            session.getRemote().sendString(new Gson().toJson(loadGameNotification));
            List<Connection> users = connections.connections.get(gameID);
            var playerNotification = new notificationMessage("Player " + auth.getToken(authToken).getUsername() + " has joined the game");
            for (Connection user : users) {
                if (!user.getAuth().equals(authToken)) {
                    user.session.getRemote().sendString(new Gson().toJson(playerNotification));
                }
            }
            System.out.print("Joined Game");
        } else {
            var errorNotification = new errorMessage("Error: Player color is already taken");
            session.getRemote().sendString(new Gson().toJson(errorNotification));
        }

    }

    private void observeGame(Integer gameID, String authToken, Session session) throws IOException, SQLException, DataAccessException {
        GameDAO game = new GameDAO();
        AuthDAO auth = new AuthDAO();
        connections.add(gameID, authToken, session);
        GameModel curGame = null;
        for (GameModel games : game.getAllGames()) {
            if (games.getGameID() == gameID) {
                curGame = games;
            }
        }
        try {
            if (auth.getToken(authToken) == null) {
                var errorNotification = new errorMessage("Error: You are not authorized");
                session.getRemote().sendString(new Gson().toJson(errorNotification));
            }
        } catch (SQLException e) {
            var errorNotification = new errorMessage("Error: You are not authorized");
            session.getRemote().sendString(new Gson().toJson(errorNotification));
            return;
        }
        if (curGame == null) {
            var errorNotification = new errorMessage("Error: Game doesn't exist");
            session.getRemote().sendString(new Gson().toJson(errorNotification));
        }

        assert curGame != null;

        var loadGameNotification = new loadGame(curGame.getGame());
        session.getRemote().sendString(new Gson().toJson(loadGameNotification));
        List<Connection> users = connections.connections.get(gameID);
        var playerNotification = new notificationMessage("Player " + auth.getToken(authToken).getUsername() + " is watching the game");
        for (Connection user : users) {
            if (!user.getAuth().equals(authToken)) {
                user.session.getRemote().sendString(new Gson().toJson(playerNotification));
            }
        }
    }

    private void makeMove(Integer gameID, String authToken, ChessMove move, Session session) throws DataAccessException, InvalidMoveException, IOException, SQLException {
        GameDAO game = new GameDAO();
        AuthDAO auth = new AuthDAO();
        GameModel curGame = null;
        for (GameModel games : game.getAllGames()) {
            if (games.getGameID() == gameID) {
                curGame = games;
            }
        }
        assert curGame != null;
        if (curGame.getGame().isGameOver() || curGame.getGame().isInCheckmate(ChessGame.TeamColor.WHITE) || curGame.getGame().isInCheckmate(ChessGame.TeamColor.BLACK) || curGame.getGame().isInStalemate(ChessGame.TeamColor.WHITE) || curGame.getGame().isInStalemate(ChessGame.TeamColor.BLACK)) {
            var errorNotification = new errorMessage("Error: The game is over");
            session.getRemote().sendString(new Gson().toJson(errorNotification));
            return;
        }
        List<ChessMove> validMovesList = curGame.getGame().validMoves(move.getStartPosition()).stream().toList();
        if (validMovesList.isEmpty()) {
            var errorNotification = new errorMessage("Error: That is not a valid move");
            session.getRemote().sendString(new Gson().toJson(errorNotification));
            return;
        } else {
            for (ChessMove chessMove : validMovesList) {
                if (chessMove.getEndPosition().getRow() == move.getEndPosition().getRow() && chessMove.getEndPosition().getColumn() == move.getEndPosition().getColumn()) {
                    if (curGame.getGame().getTeamTurn() == ChessGame.TeamColor.WHITE && Objects.equals(auth.getToken(authToken).getUsername(), curGame.getWhiteUsername())) {
                        curGame.getGame().makeMove(move);
                        game.updateGameInfo(curGame);
                    } else if (curGame.getGame().getTeamTurn() == ChessGame.TeamColor.BLACK && Objects.equals(auth.getToken(authToken).getUsername(), curGame.getBlackUsername())) {
                        curGame.getGame().makeMove(move);
                        game.updateGameInfo(curGame);
                    } else {
                        var errorNotification = new errorMessage("Error: It is not your turn");
                        session.getRemote().sendString(new Gson().toJson(errorNotification));
                        return;
                    }
                }
            }
        }
        List<Connection> users = connections.connections.get(gameID);
        for (Connection user : users) {
            var loadGameNotification = new loadGame(curGame.getGame());
            user.session.getRemote().sendString(new Gson().toJson(loadGameNotification));
        }
        var moveNotification = new notificationMessage("Player " + auth.getToken(authToken).getUsername() + " moved their " + move.getPromotionPiece() + " to " + move.getEndPosition());
        for (Connection user : users) {
            if (!user.getAuth().equals(authToken)) {
                user.session.getRemote().sendString(new Gson().toJson(moveNotification));
            }
        }
    }

    private void resign(Integer gameID, String authToken, Session session) throws DataAccessException, SQLException, IOException {
        GameDAO game = new GameDAO();
        AuthDAO auth = new AuthDAO();
        GameModel curGame = null;
        for (GameModel games : game.getAllGames()) {
            if (games.getGameID() == gameID) {
                curGame = games;
            }
        }
        assert curGame != null;
        curGame.getGame().setIsGameOver();
        game.updateGameInfo(curGame);
        String username = auth.getToken(authToken).getUsername();
        List<Connection> users = connections.connections.get(gameID);
        for (Connection user : users) {
            var resignNotification = new notificationMessage("Player " + username + " has resigned");
            user.session.getRemote().sendString(new Gson().toJson(resignNotification));
        }
    }
}