package service;

import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import dataAccess.UserDAO;
import result.ClearResult;

/**
 * Clears the database. Removes all users, games, and authTokens
 */
public class ClearService {
    /**
     * Method that clears the database
     *
     * @return Returns a ClearResult
     * @throws Exception Throws an exception
     */
    public ClearResult clear() throws Exception {
        GameDAO games = new GameDAO();
        UserDAO users = new UserDAO();
        AuthDAO tokens = new AuthDAO();
        games.clearGames();
        users.DeleteUsers();
        tokens.clearTokens();
        return new ClearResult(null);
    }
}
