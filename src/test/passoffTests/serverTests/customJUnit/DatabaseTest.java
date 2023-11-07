package passoffTests.serverTests.customJUnit;

import dataAccess.DataAccessException;
import dataAccess.*;
import model.*;
import org.junit.jupiter.api.*;
import service.ClearService;

import java.sql.SQLException;

public class DatabaseTest {
    private static final UserModel User = new UserModel("Kyler", "Moulton", "email");

    @BeforeEach
    public void reset() throws Exception {
        new ClearService().clear();
    }

    @Test
    @Order(1)
    @DisplayName("Valid Create User")
    public void successfulCreateUser() throws DataAccessException, SQLException {
        UserDAO userDAO = new UserDAO();
        userDAO.CreateUser(User.getUsername(), User.getPassword(), User.getEmail());
        Assertions.assertEquals(User, userDAO.GetUser(User.getUsername()));
    }

    @Test
    @Order(2)
    @DisplayName("Invalid Create User")
    public void UnsuccessfulCreateUser() throws DataAccessException {
        try {
            UserDAO userDAO = new UserDAO();
            userDAO.CreateUser(User.getUsername(), User.getPassword(), User.getEmail());
            userDAO.CreateUser(null, User.getPassword(), User.getEmail());
        } catch (SQLException e) {
            Assertions.assertEquals(e.getMessage(), "Column 'username' cannot be null");
        }
    }

    @Test
    @Order(3)
    @DisplayName("Valid Delete Users")
    public void SuccessfulDeleteUsers() throws DataAccessException, SQLException {
        UserDAO userDAO = new UserDAO();
        userDAO.CreateUser(User.getUsername(), User.getPassword(), User.getEmail());
        userDAO.CreateUser(User.getUsername() + 1, User.getPassword(), User.getEmail());
        userDAO.CreateUser(User.getUsername() + 2, User.getPassword(), User.getEmail());
        userDAO.DeleteUsers();

        Assertions.assertNull(userDAO.GetUser(User.getUsername()).getUsername(), (String) null);
        Assertions.assertNull(userDAO.GetUser(User.getUsername() + 1).getUsername(), (String) null);
        Assertions.assertNull(userDAO.GetUser(User.getUsername() + 2).getUsername(), (String) null);
    }

    @Test
    @Order(4)
    @DisplayName("Valid Get User")
    public void SuccessfulGetUser() throws DataAccessException, SQLException {
        UserDAO userDAO = new UserDAO();
        userDAO.CreateUser(User.getUsername(), User.getPassword(), User.getEmail());
        var gotUser = userDAO.GetUser(User.getUsername());

        Assertions.assertEquals(User, gotUser);
    }

    @Test
    @Order(5)
    @DisplayName("Invalid Get User")
    public void UnsuccessfulGetUser() throws DataAccessException, SQLException {
        UserDAO userDAO = new UserDAO();
        userDAO.CreateUser(User.getUsername(), User.getPassword(), User.getEmail());
        var gotUser = userDAO.GetUser("Invalid username");

        Assertions.assertNull(userDAO.GetUser(gotUser.getUsername()).getUsername(), (String) null);
    }

    @Test
    @Order(6)
    @DisplayName("Valid Create Auth")
    public void SuccessfulCreateAuth() throws DataAccessException, SQLException {
        AuthDAO authDAO = new AuthDAO();
        var token = authDAO.createToken(User.getUsername());
        Assertions.assertEquals(token, authDAO.getToken(token).getAuthToken());
    }

    @Test
    @Order(7)
    @DisplayName("Invalid Create Auth")
    public void UnsuccessfulCreateAuth() throws DataAccessException {
        try {
            AuthDAO authDAO = new AuthDAO();
            authDAO.createToken(null);
        } catch (SQLException e) {
            Assertions.assertEquals(e.getMessage(), "Column 'username' cannot be null");
        }
    }

    @Test
    @Order(8)
    @DisplayName("Valid Get Auth")
    public void SuccessfulGetAuth() throws DataAccessException, SQLException {
        AuthDAO authDAO = new AuthDAO();
        var newAuthToken = authDAO.createToken(User.getUsername());
        var gotAuthToken = authDAO.getToken(newAuthToken).getAuthToken();

        Assertions.assertEquals(newAuthToken, gotAuthToken, "Auth Tokens do not match");
    }

    @Test
    @Order(9)
    @DisplayName("Invalid Get Auth")
    public void UnsuccessfulGetAuth() throws DataAccessException {
        try {
            AuthDAO authDAO = new AuthDAO();
            authDAO.getToken("Not a Valid Authtoken");
        } catch (SQLException e) {
            Assertions.assertEquals(e.getMessage(), "Invalid Authtoken");
        }
    }

    @Test
    @Order(10)
    @DisplayName("Valid Get All Auth")
    public void SuccessfulGetAllAuth() throws DataAccessException, SQLException {
        AuthDAO authDAO = new AuthDAO();
        var authToken1 = authDAO.createToken(User.getUsername());
        var authToken2 = authDAO.createToken("Username2");
        var returnedAuths = authDAO.getCreatedAuthTokens();

        Assertions.assertEquals(authToken1, returnedAuths.get(authToken1).getAuthToken());
        Assertions.assertEquals(authToken2, returnedAuths.get(authToken2).getAuthToken());
    }

    @Test
    @Order(11)
    @DisplayName("Invalid Get All Auth")
    public void UnsuccessfulGetAllAuth() throws DataAccessException, SQLException {
        //I took Michael's advice from slack and just made sure I got back an empty array if I didn't insert anything
        AuthDAO authDAO = new AuthDAO();
        var returnedAuths = authDAO.getCreatedAuthTokens();

        Assertions.assertTrue(returnedAuths.isEmpty());
    }

    @Test
    @Order(12)
    @DisplayName("Valid Delete Token")
    public void SuccessfulDeleteToken() throws DataAccessException {
        try {
            AuthDAO authDAO = new AuthDAO();
            var authToken1 = authDAO.createToken(User.getUsername());
            authDAO.deleteToken(authToken1);
            authDAO.getToken(authToken1);
        } catch (SQLException e) {
            Assertions.assertEquals(e.getMessage(), "Invalid Authtoken");
        }
    }

    @Test
    @Order(13)
    @DisplayName("Invalid Delete Token")
    public void UnsuccessfulDeleteToken() throws DataAccessException, SQLException {
        AuthDAO authDAO = new AuthDAO();
        var authToken1 = authDAO.createToken(User.getUsername());
        authDAO.deleteToken("Wrong Token");
        Assertions.assertEquals(authToken1, authDAO.getToken(authToken1).getAuthToken());
    }

    @Test
    @Order(3)
    @DisplayName("Valid Delete Auths")
    public void SuccessfulDeleteAuths() throws DataAccessException, SQLException {
        AuthDAO authDAO = new AuthDAO();
        authDAO.createToken(User.getUsername());
        authDAO.createToken(User.getUsername() + 1);
        authDAO.createToken(User.getUsername() + 2);
        authDAO.clearTokens();

        Assertions.assertTrue(authDAO.getCreatedAuthTokens().isEmpty());
    }
}
