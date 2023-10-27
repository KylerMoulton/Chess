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
     */
    public String createToken(String username) {
        AuthTokenModel newAuthToken = new AuthTokenModel(UUID.randomUUID().toString(),username);
        storeToken(newAuthToken);
        return newAuthToken.getAuthToken();
    }

    /**
     * Gets an AuthToken
     * @param authToken takes in a String of username associated with the requested token
     * @return Returns the AuthToken of the requested token
     */
    public AuthTokenModel getToken(String authToken) {
        return createdAuthTokens.get(authToken);
    }

    /**
     * Deletes the requested AuthToken
     * @param token takes in a String of the requested token
     */
    public void deleteToken(String token) {
        createdAuthTokens.remove(token);
    }
    public void clearTokens() {
        createdAuthTokens.clear();
    }
    public void storeToken(AuthTokenModel tokenModel) {
        createdAuthTokens.put(tokenModel.getAuthToken(),tokenModel);
    }
}
