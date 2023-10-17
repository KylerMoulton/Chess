package dataAccess;

import model.AuthTokenModel;

/**
 *
 */
public class AuthDAO {
    /**
     * Creates an AuthToken that states if the user is authorized
     * @param token takes in an AuthTokenModel
     */
    void createToken(AuthTokenModel token) {
    }

    /**
     * Gets an AuthToken
     * @param token takes in a String of the requested token
     * @return Returns the AuthTokenModel of the request token
     */
    AuthTokenModel getToken(String token) {
        return null;
    }

    /**
     * Updates the requested token
     * @param token takes in a String of the requested token
     */
    void updateToken(String token) {

    }

    /**
     * Updates the players username
     * @param username Takes in a String of the new username
     */
    void updateUsername(String username) {

    }

    /**
     * Deletes the requested AuthToken
     * @param token takes in a String of the requested token
     */
    void deleteToken(AuthTokenModel token) {

    }
}
