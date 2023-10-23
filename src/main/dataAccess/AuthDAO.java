package dataAccess;

import model.AuthTokenModel;

/**
 * Class that creates, updates, and deletes AuthTokenModels in the Database
 */
public class AuthDAO {
    /**
     * Creates an AuthToken that states if the user is authorized
     * @param token takes in an AuthTokenModel
     * @throws DataAccessException Throws a DataAccessException
     */
    void createToken(AuthTokenModel token) throws DataAccessException{
    }

    /**
     * Gets an AuthToken
     * @param token takes in a String of the requested token
     * @return Returns the AuthTokenModel of the request token
     * @throws DataAccessException Throws a DataAccessException
     */
    AuthTokenModel getToken(String token) throws DataAccessException{
        return null;
    }

    /**
     * Updates the requested token
     * @param token takes in a String of the requested token
     * @throws DataAccessException Throws a DataAccessException
     */
    void updateToken(String token) throws DataAccessException{

    }

    /**
     * Updates the players username
     * @param username Takes in a String of the new username
     * @throws DataAccessException Throws a DataAccessException
     */
    void updateUsername(String username) throws DataAccessException{

    }

    /**
     * Deletes the requested AuthToken
     * @param token takes in a String of the requested token
     * @throws DataAccessException Throws a DataAccessException
     */
    void deleteToken(AuthTokenModel token) throws DataAccessException{

    }
}
