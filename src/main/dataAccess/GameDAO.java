package dataAccess;

import chess.ChessGame;
import model.GameModel;

/**
 * Class that creates, updates, and deletes GameModels in the Database
 */
public class GameDAO {
    /**
     * Inserts a game into the Database
     * @param game Takes in a game to add
     * @throws DataAccessException Throws a DataAccessException
     */
    void insertGame(GameModel game) throws DataAccessException{

    }

    /**
     * Gets a game from the Database
     * @param game Takes in a game to get from the Database
     * @throws DataAccessException DataAccessException Throws a DataAccessException
     */
    void getGame(GameModel game) throws DataAccessException{

    }
    /**
     * Gets all games from the Database
     * @throws DataAccessException Throws a DataAccessException
     */
    void getAllGames() throws DataAccessException{

    }

    /**
     * Claims a players spot in the requested game
     * @param game Takes in a game to get from the Database
     * @param username Takes in a String of the players username
     * @param teamColor Takes in a String of what team the player would like to join
     * @throws DataAccessException Throws a DataAccessException
     */
    void claimSpot(GameModel game, String username, ChessGame.TeamColor teamColor)  throws DataAccessException{

    }

    /**
     * Updates the details of the game provided
     * @param game Takes in a game to get from the Database
     * @throws DataAccessException Throws a DataAccessException
     */
    void updateGame(GameModel game)  throws DataAccessException{

    }

    /**
     * Removes a game from the Database
     * @param game Takes in a game to delete from the Database
     * @throws DataAccessException Throws a DataAccessException
     */
    void removeGame(GameModel game)  throws DataAccessException{

    }

    /**
     * Clears all games from the Database
     * @throws DataAccessException Throws a Data Access Exception
     */
    void clearGames()  throws DataAccessException{

    }
}
