package service;

import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.GameDAO;
import exeptions.AlreadyTakenException;
import exeptions.BadReqException;
import exeptions.UnauthorizedException;
import model.AuthTokenModel;
import model.GameModel;
import request.JoinGameRequest;
import result.JoinGameResult;

import java.util.Objects;

/**
 * Verifies that the specified game exists, and, if a color is specified,
 * adds the caller as the requested color to the game.
 * If no color is specified the user is joined as an observer.
 * This request is idempotent.
 */
public class JoinGameService {
    /**
     * Method that joins a game
     * @param j Takes in a JoinGameRequest
     * @return Returns a JoinGameResult
     * @throws Exception Throws an exception
     */
    public JoinGameResult joinGame(JoinGameRequest j, String token) throws Exception{
        AuthDAO tokens = new AuthDAO();
        GameDAO games = new GameDAO();
        checkAuthorization(token,tokens);
        GameModel curGame = validGameReq(j.getGameID(),games);
        assert curGame != null;
        AuthTokenModel curUser = getCurUser(token,tokens);
        assert curUser != null;
        setPlayerColor(j.getPlayerColor(),curGame,curUser);
        return new JoinGameResult(token,null);
    }

    private AuthTokenModel getCurUser(String token, AuthDAO authTokens) throws DataAccessException {
        for (AuthTokenModel tokens : authTokens.getCreatedAuthTokens().values()) {
            if (Objects.equals(tokens.getAuthToken(), token)) {
                return tokens;
            }
        }
        return null;
    }

    private void setPlayerColor(String playerColor, GameModel curGame,AuthTokenModel curUser) throws AlreadyTakenException {
        if (curGame.getWhiteUsername() != null && Objects.equals(playerColor, "WHITE")) {
            throw new AlreadyTakenException("Error: already taken");
        } else if (Objects.equals(playerColor, "WHITE")) {
            curGame.setWhiteUsername(curUser.getUsername());
        }
        if (curGame.getBlackUsername() != null && Objects.equals(playerColor, "BLACK")) {
            throw new AlreadyTakenException("Error: already taken");
        } else if (Objects.equals(playerColor,"BLACK")) {
            curGame.setBlackUsername(curUser.getUsername());
        }
    }

    private GameModel validGameReq(int gameID, GameDAO games) throws DataAccessException, BadReqException {
        for (GameModel game : games.getAllGames()) {
            if (game.getGameID() == gameID) {
                return game;
            } else throw new BadReqException("Error: bad request");
        }
        return null;
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
