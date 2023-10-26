package service;

import dataAccess.AuthDAO;
import dataAccess.DataAccessException;
import dataAccess.UserDAO;
import exeptions.AlreadyTakenException;
import exeptions.BadReqException;
import request.RegisterRequest;
import result.RegisterResult;
/**
 * Register a new user.
 */
public class RegisterService {
    /**
     * Method that registers a new user
     * @param r Takes in a RegisterRequest
     * @return Returns a RegisterResult
     * @throws Exception Throws an exception
     */
    public RegisterResult register(RegisterRequest r) throws Exception{
        validCredentials(r.getUsername(),r.getPassword(),r.getEmail());
        UserDAO users = new UserDAO();
        AuthDAO tokens = new AuthDAO();
        alreadyTaken(r.getUsername(),users);
        users.CreateUser(r.getUsername(),r.getPassword(),r.getEmail());
        String authToken = tokens.createToken(r.getUsername());
        return new RegisterResult(null, authToken,r.getUsername());
    }
    private void validCredentials(String username, String password, String email) throws BadReqException {
        if (username == null || password == null || email ==null) {
            throw new BadReqException("Error: bad request");
        }
    }
    private void alreadyTaken(String username,UserDAO users) throws AlreadyTakenException, DataAccessException {
        if (users.GetUser(username)!=null) {
            throw new AlreadyTakenException("Error: already taken");
        }
    }
}
