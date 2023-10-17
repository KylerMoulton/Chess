package model;

/**
 * Creates an AuthToken
 */
public class AuthTokenModel {
    private String authToken;
    private String username;

    /**
     * Constructor for the AuthTokenModel
     * @param authToken Takes in what to set the authToken as
     * @param username Takes in the username of the user associated with the authToken
     */

    public AuthTokenModel(String authToken, String username) {
        this.authToken = authToken;
        this.username = username;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
