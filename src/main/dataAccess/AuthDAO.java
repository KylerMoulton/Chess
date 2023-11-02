package dataAccess;

import model.AuthTokenModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public class AuthDAO {
    private final Connection database;

    public AuthDAO() throws DataAccessException {
        this.database = new Database().getConnection();
    }

    public String createToken(String inputUsername) {
        String returnedToken = UUID.randomUUID().toString();
        try (var preparedStatement = database.prepareStatement("Insert INTO auth(id,username) values (?,?)")) {
            preparedStatement.setString(1, returnedToken);
            preparedStatement.setString(2, inputUsername);
            preparedStatement.executeUpdate();
            database.close();
        } catch (SQLException e) {
            String message = e.getMessage();
            System.out.printf(message);
        }
        return returnedToken;
    }

    public AuthTokenModel getToken(String InputAuthToken) {
        AuthTokenModel returnedAuth = new AuthTokenModel(null, null);
        try (var preparedStatement = database.prepareStatement("SELECT id,username FROM user WHERE id=?")) {
            preparedStatement.setString(1, InputAuthToken);
            try (var user = preparedStatement.executeQuery()) {
                var authToken = user.getString("id");
                var username = user.getString("username");
                returnedAuth.setAuthToken(authToken);
                returnedAuth.setUsername(username);
                database.close();
            } catch (SQLException e) {
                String message = e.getMessage();
                System.out.printf(message);
            }
        } catch (SQLException e) {
            String message = e.getMessage();
            System.out.printf(message);
        }
        return returnedAuth;
    }

    public HashMap<String, AuthTokenModel> getCreatedAuthTokens() {
        HashMap<String, AuthTokenModel> createdAuthTokens = new HashMap<>();
        AuthTokenModel returnedAuth = new AuthTokenModel(null, null);
        try (var preparedStatement = database.prepareStatement("SELECT id,username FROM user")) {
            try (var user = preparedStatement.executeQuery()) {
                var authToken = user.getString("id");
                var username = user.getString("username");
                returnedAuth.setAuthToken(authToken);
                returnedAuth.setUsername(username);
                createdAuthTokens.put(returnedAuth.getAuthToken(), returnedAuth);
                database.close();
            } catch (SQLException e) {
                String message = e.getMessage();
                System.out.printf(message);
            }
        } catch (SQLException e) {
            String message = e.getMessage();
            System.out.printf(message);
        }
        return createdAuthTokens;

    }

    public void deleteToken(String token) {
        try (var preparedStatement = database.prepareStatement("DELETE FROM auth WHERE id=?")) {
            preparedStatement.setString(1, token);
            preparedStatement.executeUpdate();
            database.close();
        } catch (SQLException e) {
            String message = e.getMessage();
            System.out.printf(message);
        }
    }

    public void clearTokens() {
        try (var preparedStatement = database.prepareStatement("DELETE FROM auth")) {
            preparedStatement.executeUpdate();
            database.close();
        } catch (SQLException e) {
            String message = e.getMessage();
            System.out.printf(message);
        }
    }
}

