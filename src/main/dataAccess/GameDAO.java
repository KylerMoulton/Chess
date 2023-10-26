package dataAccess;

import chess.ChessGame;
import model.GameModel;

import java.util.Collection;
import java.util.HashMap;

/**
 * Class that creates, updates, and deletes GameModels in the Database
 */
public class GameDAO {
    public static HashMap<Integer,GameModel> createdGames = new HashMap<>();
    public static int gameID = 1;
    /**
     * Inserts a game into the Database
     * @param game Takes in a game to add
     * @throws DataAccessException Throws a DataAccessException
     */
    public void insertGame(GameModel game) throws DataAccessException{
        createdGames.put(game.getGameID(),game);
    }

    /**
     * Gets a game from the Database
     * @param game Takes in a game to get from the Database
     * @throws DataAccessException DataAccessException Throws a DataAccessException
     */
    public void getGame(GameModel game) throws DataAccessException{

    }
    /**
     * Gets all games from the Database
     * @throws DataAccessException Throws a DataAccessException
     */
    public Collection<GameModel> getAllGames() throws DataAccessException{
        return createdGames.values();
    }

    /**
     * Claims a players spot in the requested game
     * @param game Takes in a game to get from the Database
     * @param username Takes in a String of the players username
     * @param teamColor Takes in a String of what team the player would like to join
     * @throws DataAccessException Throws a DataAccessException
     */
    public void claimSpot(GameModel game, String username, ChessGame.TeamColor teamColor)  throws DataAccessException{

    }

    /**
     * Updates the details of the game provided
     * @param game Takes in a game to get from the Database
     * @throws DataAccessException Throws a DataAccessException
     */
    public void updateGame(GameModel game)  throws DataAccessException{

    }

    /**
     * Removes a game from the Database
     * @param game Takes in a game to delete from the Database
     * @throws DataAccessException Throws a DataAccessException
     */
    public void removeGame(GameModel game)  throws DataAccessException{

    }

    /**
     * Clears all games from the Database
     * @throws DataAccessException Throws a Data Access Exception
     */
    public void clearGames()  throws DataAccessException{
        createdGames.clear();
    }
    public void storeToken(GameModel gameModel) throws DataAccessException{
        createdGames.put(gameModel.getGameID(),gameModel);
    }
    public void IncreaseGameID(){
        gameID++;
    }

    public Integer getGameID() {
        return gameID;
    }
}
