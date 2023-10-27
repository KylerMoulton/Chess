package service;

import dataAccess.AuthDAO;
import dataAccess.UserDAO;
import exeptions.UnauthorizedException;
import request.LoginRequest;
import result.LoginResult;

import java.util.Objects;

/**
 * Logs in an existing user (returns a new authToken).
 */
public class LoginService {
    /**
     * Method for logging in a user
     * @param r Takes a LoginRequest
     * @return Returns a LoginResult
     * @throws Exception Throws an exception
     */
    public LoginResult login (LoginRequest r) throws Exception{
        UserDAO users = new UserDAO();
        AuthDAO tokens = new AuthDAO();
        existingUser(r.getUsername(),users);
        rightPassword(r.getPassword(),r.getUsername(),users);
        String curUser = users.GetUser(r.getUsername());
        String authToken = tokens.createToken(r.getUsername());
        return new LoginResult(null, authToken, curUser);
    }
    private void existingUser(String username, UserDAO users) throws UnauthorizedException {
        if (users.GetUser(username)==null) {
            throw new UnauthorizedException("Error: unauthorized");
        }
    }
    private void rightPassword(String password, String username, UserDAO users) throws UnauthorizedException {
        if (!Objects.equals(users.GetPassword(username), password)) {
            throw new UnauthorizedException("Error: unauthorized");
        }
    }
}
