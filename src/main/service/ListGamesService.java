package service;

import dataAccess.AuthDAO;
import dataAccess.GameDAO;
import exeptions.UnauthorizedException;
import result.ListGamesResult;

import java.sql.SQLException;

/**
 * Gives a list of all games.
 */
public class ListGamesService {
    /**
     * Method that lists all games in the Database
     *
     * @return Returns a ListGamesResult
     * @throws Exception Throws an exception
     */
    public ListGamesResult listGames(String token) throws Exception {
        AuthDAO tokens = new AuthDAO();
        GameDAO games = new GameDAO();
        checkAuthorization(token, tokens);
        ListGamesResult listGames = new ListGamesResult();
        listGames.setGamesList(games.getAllGames());
        return listGames;
    }

    public void checkAuthorization(String token, AuthDAO tokens) throws UnauthorizedException, SQLException {
        if (tokens.getCreatedAuthTokens().isEmpty()) {
            throw new UnauthorizedException("Error: unauthorized");
        }
        if (token != null) {
            if (tokens.getToken(token) == null) {
                throw new UnauthorizedException("Error: unauthorized");
            }
        }
    }
}
