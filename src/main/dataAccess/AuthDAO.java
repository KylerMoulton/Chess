package dataAccess;

import model.AuthTokenModel;

import java.util.HashMap;
import java.util.UUID;

/**
 * Class that creates, updates, and deletes AuthTokenModels in the Database
 */
public class AuthDAO {
    public static HashMap<String,AuthTokenModel> createdAuthTokens = new HashMap<>();
    public HashMap<String,AuthTokenModel> getCreatedAuthTokens() {
        return createdAuthTokens;
    }
    /**
     * Creates an AuthToken that states if the user is authorized
     * @param username takes in an AuthTokenModel
     * @throws DataAccessException Throws a DataAccessException
     */
    public String createToken(String username) throws DataAccessException{
        AuthTokenModel newAuthToken = new AuthTokenModel(UUID.randomUUID().toString(),username);
        storeToken(newAuthToken);
        return newAuthToken.getAuthToken();
    }

    /**
     * Gets an AuthToken
     * @param authToken takes in a String of username associated with the requested token
     * @return Returns the AuthToken of the requested token
     * @throws DataAccessException Throws a DataAccessException
     */
    public String getToken(String authToken) throws DataAccessException{
        return createdAuthTokens.get(authToken).getAuthToken();
    }

    /**
     * Updates the requested token
     * @param token takes in a String of the requested token
     * @throws DataAccessException Throws a DataAccessException
     */
    public void updateToken(String token) throws DataAccessException{

    }

    /**
     * Updates the players username
     * @param username Takes in a String of the new username
     * @throws DataAccessException Throws a DataAccessException
     */
    public void updateUsername(String username) throws DataAccessException{
    }

    /**
     * Deletes the requested AuthToken
     * @param token takes in a String of the requested token
     * @throws DataAccessException Throws a DataAccessException
     */
    public void deleteToken(String token) throws DataAccessException{
        createdAuthTokens.remove(token);
    }
    public void clearTokens() throws DataAccessException {
        createdAuthTokens.clear();
    }
    public void storeToken(AuthTokenModel tokenModel) throws DataAccessException{
        createdAuthTokens.put(tokenModel.getAuthToken(),tokenModel);
    }
}
