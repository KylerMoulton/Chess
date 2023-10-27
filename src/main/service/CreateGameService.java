package service;

import chess.gameImple;
import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.GameDAO;
import exeptions.BadReqException;
import exeptions.UnauthorizedException;
import model.GameModel;
import request.CreateGameRequest;
import result.CreateGameResult;

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
        AuthDAO tokens = new AuthDAO();
        GameDAO games = new GameDAO();
        checkAuthorization(token,tokens);
        validGameName(g.getGameName());
        GameModel newgame = new GameModel(games.getGameID(),null,null, g.getGameName(), new gameImple());
        games.insertGame(newgame);
        games.IncreaseGameID();
        return new CreateGameResult(newgame.getGameID(), null);
    }
    private void validGameName(String gameName) throws BadReqException {
        if (gameName == null ) {
            throw new BadReqException("Error: bad request");
        }
    }
    public void checkAuthorization(String token,AuthDAO tokens) throws DataAccessException, UnauthorizedException {
        if (tokens.getCreatedAuthTokens().isEmpty()) {
            throw new UnauthorizedException("Error: unauthorized");
        }
        if (token!=null){
            if (tokens.getToken(token)==null) {
                throw new UnauthorizedException("Error: unauthorized");
            }
        }
    }
}
