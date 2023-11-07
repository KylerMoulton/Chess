package service;

import dataAccess.AuthDAO;
import exeptions.UnauthorizedException;
import result.LogoutResult;

import java.sql.SQLException;

/**
 * Logs out the user represented by the authToken.
 */
public class LogoutService {
    /**
     * Method that logs out a logged-in User
     *
     * @return returns a LogoutResult
     * @throws Exception Throws an exception
     */
    public LogoutResult logout(String token) throws Exception {
        AuthDAO tokens = new AuthDAO();
        checkAuthorization(token, tokens);
        tokens.deleteToken(token);
        return new LogoutResult(null, token);
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
