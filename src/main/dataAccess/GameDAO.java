package dataAccess;

import chess.ChessGame;
import chess.gameImple;
import com.google.gson.Gson;
import model.GameModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;

/**
 * Class that creates, updates, and deletes GameModels in the Database
 */
public class GameDAO extends Database {
    public static int gameID = 1;

    public GameDAO() {
    }

    public void insertGame(GameModel game) throws SQLException, DataAccessException {
        Connection con = getConnection();
        try (var preparedStatement = con.prepareStatement("Insert INTO game(gameID,whiteUsername,blackUsername,gameName,game) values (?,?,?,?,?)")) {
            preparedStatement.setString(1, Integer.toString(game.getGameID()));
            preparedStatement.setString(2, game.getWhiteUsername());
            preparedStatement.setString(3, game.getBlackUsername());
            preparedStatement.setString(4, game.getGameName());
            preparedStatement.setString(5, gameImple.serialization().toJson(game.getGame()));
            preparedStatement.executeUpdate();
            closeConnection(con);
        } catch (SQLException e) {
            String message = e.getMessage();
            System.out.printf(message);
            throw new SQLException(message);
        }
    }

    public void updateGame(GameModel game) throws SQLException, DataAccessException {
        Connection con = getConnection();
        try (var preparedStatement = con.prepareStatement("UPDATE game SET whiteUsername=?,blackUsername=? WHERE gameID=?")) {
            preparedStatement.setString(1, game.getWhiteUsername());
            preparedStatement.setString(2, game.getBlackUsername());
            preparedStatement.setString(3, Integer.toString(game.getGameID()));
            preparedStatement.executeUpdate();
            closeConnection(con);
        }
    }

    public void updateGameInfo(GameModel game) throws DataAccessException, SQLException {
        Connection con = getConnection();
        try (var preparedStatement = con.prepareStatement("UPDATE game SET game=? WHERE gameID=?")) {
            preparedStatement.setString(1, new Gson().toJson(game.getGame()));
            preparedStatement.setInt(2, game.getGameID());
            preparedStatement.executeUpdate();
            closeConnection(con);
        }
    }

    public Collection<GameModel> getAllGames() throws DataAccessException {
        Connection con = getConnection();
        HashMap<Integer, GameModel> createdGames = new HashMap<>();
        try (var preparedStatement = con.prepareStatement("SELECT gameID, whiteUsername,blackUsername,gameName,game FROM game")) {
            try (var user = preparedStatement.executeQuery()) {
                while (user.next()) {
                    GameModel createdGame = new GameModel(0, null, null, null, null);
                    var gameID = user.getInt("gameID");
                    var whiteUsername = user.getString("whiteUsername");
                    var blackUsername = user.getString("blackUsername");
                    var gameName = user.getString("gameName");
                    var game = user.getString("game");
                    createdGame.setGameID(gameID);
                    createdGame.setWhiteUsername(whiteUsername);
                    createdGame.setBlackUsername(blackUsername);
                    createdGame.setGameName(gameName);
                    //createdGame.setGame(null);
                    createdGame.setGame(gameImple.serialization().fromJson(game, ChessGame.class));
                    createdGames.put(createdGame.getGameID(), createdGame);
                }
                closeConnection(con);
            } catch (SQLException e) {
                String message = e.getMessage();
                System.out.printf(message);
            }
        } catch (SQLException e) {
            String message = e.getMessage();
            System.out.printf(message);
        }
        return createdGames.values();
    }

    public void clearGames() throws DataAccessException {
        Connection con = getConnection();
        try (var preparedStatement = con.prepareStatement("DELETE FROM game")) {
            preparedStatement.executeUpdate();
            closeConnection(con);
        } catch (SQLException e) {
            String message = e.getMessage();
            System.out.printf(message);
        }
    }

    public void IncreaseGameID() {
        gameID++;
    }

    public Integer getGameID() {
        return gameID;
    }
}
