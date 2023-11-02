package memoryDataAccess;

import model.GameModel;

import java.util.Collection;
import java.util.HashMap;

/**
 * Class that creates, updates, and deletes GameModels in the Database
 */
public class GameDAOmemory {
    public static HashMap<Integer, GameModel> createdGames = new HashMap<>();
    public static int gameID = 1;

    /**
     * Inserts a game into the Database
     *
     * @param game Takes in a game to add
     */
    public void insertGame(GameModel game) {
        createdGames.put(game.getGameID(), game);
    }

    /**
     * Gets all games from the Database
     */
    public Collection<GameModel> getAllGames() {
        return createdGames.values();
    }

    /**
     * Clears all games from the Database
     */
    public void clearGames() {
        createdGames.clear();
    }

    public void IncreaseGameID() {
        gameID++;
    }

    public Integer getGameID() {
        return gameID;
    }
}
