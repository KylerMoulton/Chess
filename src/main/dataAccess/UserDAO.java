package dataAccess;

import model.UserModel;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDAO extends Database {

    public UserDAO() {
    }

    public void CreateUser(String inputUsername, String inputPassword, String inputEmail) throws SQLException, DataAccessException {
        Connection con = getConnection();
        try (var preparedStatement = con.prepareStatement("Insert INTO user(username,password,email) values (?,?,?)")) {
            preparedStatement.setString(1, inputUsername);
            preparedStatement.setString(2, inputPassword);
            preparedStatement.setString(3, inputEmail);
            preparedStatement.executeUpdate();
            closeConnection(con);
        } catch (SQLException e) {
            String message = e.getMessage();
            System.out.printf(message);
            throw new SQLException(message);
        }
    }

    public void DeleteUsers() throws SQLException, DataAccessException {
        Connection con = getConnection();
        try (var preparedStatement = con.prepareStatement("DELETE FROM user")) {
            preparedStatement.executeUpdate();
            closeConnection(con);
        } catch (SQLException e) {
            String message = e.getMessage();
            System.out.printf(message);
            throw new SQLException(message);
        }
    }

    public UserModel GetUser(String inputUsername) throws SQLException, DataAccessException {
        Connection con = getConnection();
        UserModel returnedUser = new UserModel(null, null, null);
        try (var preparedStatement = con.prepareStatement("SELECT username,password,email FROM user WHERE username=?")) {
            preparedStatement.setString(1, inputUsername);
            try (var user = preparedStatement.executeQuery()) {
                if (user.next()) {
                    var username = user.getString("username");
                    var password = user.getString("password");
                    var email = user.getString("email");
                    returnedUser.setUsername(username);
                    returnedUser.setPassword(password);
                    returnedUser.setEmail(email);
                }
                closeConnection(con);
            } catch (SQLException e) {
                String message = e.getMessage();
                System.out.printf(message);
                throw new SQLException(message);
            }
        } catch (SQLException e) {
            String message = e.getMessage();
            System.out.printf(message);
            throw new SQLException(message);
        }
        return returnedUser;
    }
}
