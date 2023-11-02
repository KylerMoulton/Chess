package dataAccess;

import model.UserModel;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDAO {
    private final Connection database;

    public UserDAO() throws DataAccessException {
        this.database = new Database().getConnection();
    }

    public void CreateUser(String inputUsername, String inputPassword, String inputEmail) throws SQLException {
        try (var preparedStatement = database.prepareStatement("Insert INTO user(username,password,email) values (?,?,?)")) {
            preparedStatement.setString(1, inputUsername);
            preparedStatement.setString(2, inputPassword);
            preparedStatement.setString(3, inputEmail);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            String message = e.getMessage();
            System.out.printf(message);
        }
    }

    public void DeleteUsers() throws SQLException {
        try (var preparedStatement = database.prepareStatement("DELETE FROM user")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            String message = e.getMessage();
            System.out.printf(message);
        }
    }

    public UserModel GetUser(String inputUsername) throws SQLException {
        UserModel returnedUser = new UserModel(null, null, null);
        try (var preparedStatement = database.prepareStatement("SELECT username,password,email FROM user WHERE username=?")) {
            preparedStatement.setString(1, inputUsername);
            try (var user = preparedStatement.executeQuery()) {
                var username = user.getString("username");
                var password = user.getString("password");
                returnedUser.setUsername(username);
                returnedUser.setPassword(password);
            } catch (SQLException e) {
                String message = e.getMessage();
                System.out.printf(message);
            }
        } catch (SQLException e) {
            String message = e.getMessage();
            System.out.printf(message);
        }
        return returnedUser;
    }
}
