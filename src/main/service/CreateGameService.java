package service;

import chess.gameImple;
import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.GameDAO;
import dataAccess.UserDAO;
import exeptions.BadReqException;
import exeptions.UnauthorizedException;
import model.GameModel;
import request.CreateGameRequest;
import result.CreateGameResult;

import java.util.Objects;

/**
 * Creates a new game
 */
public class CreateGameService {
    /**
     * Method that create a new game
     * @param g Takes in a CreateGameRequest
     * @return Returns a CreateGameResult
     * @throws Exception Throws an exception
     */
    public CreateGameResult createGame(CreateGameRequest g,String token) throws Exception{
        UserDAO users = new UserDAO();
        AuthDAO tokens = new AuthDAO();
        GameDAO games = new GameDAO();
        checkAuthorization(token,tokens);
        validGameName(g.getGameName());
        games.IncreaseGameID();
        games.insertGame(new GameModel(games.getGameID(),null,null, g.getGameName(), new gameImple()));
        return new CreateGameResult(token, games.getGameID(), null);
    }
    private void validGameName(String gameName) throws BadReqException {
        if (gameName == null ) {
            throw new BadReqException("Error: bad request");
        }
    }
    public void checkAuthorization(String token,AuthDAO tokens) throws DataAccessException, UnauthorizedException {
        if (token!=null){
            if (!Objects.equals(tokens.getToken(token), token)) {
                throw new UnauthorizedException("Error: unauthorized");
            }
        }
    }
}
