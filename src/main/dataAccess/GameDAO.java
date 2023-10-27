package dataAccess;

import model.GameModel;

import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;

/**
 * Class that creates, updates, and deletes GameModels in the Database
 */
public class GameDAO {
    public static HashMap<Integer,GameModel> createdGames = new HashMap<>();
    public static int gameID = 1;
    /**
     * Inserts a game into the Database
     * @param game Takes in a game to add
     */
    public void insertGame(GameModel game) {
        createdGames.put(game.getGameID(),game);
    }

    /**
     * Gets all games from the Database
     */
    public Collection<GameModel> getAllGames() {
        return createdGames.values();
    }

    /**
     * Claims a players spot in the requested game
     * @param game Takes in a game to get from the Database
     * @param username Takes in a String of the players username
     * @param teamColor Takes in a String of what team the player would like to join
     * @throws DataAccessException Throws a DataAccessException
     */
    public void claimSpot(GameModel game, String username, String teamColor)  throws DataAccessException{
        GameModel g = createdGames.get(game.getGameID());

        if (Objects.equals(teamColor, "WHITE")) {
            g.setWhiteUsername(username);
        }
        if (Objects.equals(teamColor, "BLACK")) {
            g.setBlackUsername(username);
        }
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
