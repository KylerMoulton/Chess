package model;

import java.util.Objects;

/**
 * Creates an AuthToken
 */
public class AuthTokenModel {
    /**
     * Variable for the Universally Unique Identifier that acts as authToken
     */
    private String authToken;
    /**
     * Variable for the users username
     */
    private String username;

    /**
     * Constructor for the AuthTokenModel
     *
     * @param authToken Takes in what to set the authToken as
     * @param username  Takes in the username of the user associated with the authToken
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthTokenModel that = (AuthTokenModel) o;
        return Objects.equals(authToken, that.authToken) && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authToken, username);
    }
}
