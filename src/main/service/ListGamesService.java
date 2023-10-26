package service;

import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.GameDAO;
import exeptions.UnauthorizedException;
import result.ListGamesResult;

import java.util.Objects;

/**
 * Gives a list of all games.
 */
public class ListGamesService {
    /**
     * Method that lists all games in the Database
     * @return Returns a ListGamesResult
     * @throws Exception Throws an exception
     */
    public ListGamesResult listGames(String token) throws Exception{
        AuthDAO tokens = new AuthDAO();
        GameDAO games = new GameDAO();
        checkAuthorization(token,tokens);
        return new ListGamesResult(games.getAllGames(),null);
    }
    public void checkAuthorization(String token, AuthDAO tokens) throws DataAccessException, UnauthorizedException {
        if (tokens.getCreatedAuthTokens().isEmpty()) {
            throw new UnauthorizedException("Error: unauthorized");
        }
        if (token!=null){
            if (!Objects.equals(tokens.getToken(token), token)) {
                throw new UnauthorizedException("Error: unauthorized");
            }
        }
    }
}
