package service;

import request.JoinGameRequest;
import result.JoinGameResult;

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
    public JoinGameResult joinGame(JoinGameRequest j) throws Exception{
        return null;
    }
}
