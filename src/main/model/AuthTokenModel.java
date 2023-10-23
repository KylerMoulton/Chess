package model;

import java.util.UUID;

/**
 * Creates an AuthToken
 */
public class AuthTokenModel {
    /**
     * Variable for the Universally Unique Identifier that acts as authToken
     */
    private UUID authToken;
    /**
     * Variable for the users username
     */
    private String username;

    /**
     * Constructor for the AuthTokenModel
     * @param authToken Takes in what to set the authToken as
     * @param username Takes in the username of the user associated with the authToken
     */

    public AuthTokenModel(UUID authToken, String username) {
        this.authToken = authToken;
        this.username = username;
    }

    public UUID getAuthToken() {
        return authToken;
    }

    public void setAuthToken(UUID authToken) {
        this.authToken = authToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
