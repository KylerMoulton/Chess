import model.GameModel;
import org.junit.jupiter.api.*;
import request.*;
import result.*;
import web.ServerFacade;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

public class ServerFacadeTest {
    private ServerFacade server;

    @BeforeEach
    public void setup() throws Exception {
        server = new ServerFacade();
    }

    @Test
    @Order(1)
    @DisplayName("Valid Register User")
    public void ValidRegisterUser() throws IOException {
        RegisterRequest request = new RegisterRequest(UUID.randomUUID().toString(), "Password", "email");
        RegisterResult result = server.registerUser(request);
        Assertions.assertNotNull(result.getAuthToken());
        Assertions.assertEquals(request.getUsername(), result.getUsername());
    }

    @Test
    @Order(2)
    @DisplayName("Invalid Register User")
    public void InvalidRegisterUser() throws IOException {
        RegisterRequest request = new RegisterRequest("Kyler", "Password", "email");
        RegisterResult result = server.registerUser(request);
        Assertions.assertNull(result);
    }

    @Test
    @Order(3)
    @DisplayName("Valid Login User")
    public void ValidLoginUser() throws IOException {
        LoginRequest request = new LoginRequest("Kyler", "Moulton");
        LoginResult result = server.loginUser(request);
        Assertions.assertNotNull(result.getAuthToken());
        Assertions.assertEquals(request.getUsername(), result.getUsername());
    }

    @Test
    @Order(4)
    @DisplayName("Invalid Login User")
    public void InvalidLoginUser() throws IOException {
        LoginRequest request = new LoginRequest(UUID.randomUUID().toString(), "Password");
        LoginResult result = server.loginUser(request);
        Assertions.assertNull(result);
    }

    @Test
    @Order(5)
    @DisplayName("Valid Logout User")
    public void ValidLogoutUser() throws IOException {
        LoginRequest request = new LoginRequest("Kyler", "Moulton");
        LoginResult result = server.loginUser(request);
        String auth = result.getAuthToken();
        LogoutResult logout = server.logoutUser(auth);
        Assertions.assertNull(server.logoutUser(auth));
        Assertions.assertEquals(result.getAuthToken(), logout.getAuthToken());
    }

    @Test
    @Order(6)
    @DisplayName("Invalid Logout User")
    public void InvalidLogoutUser() throws IOException {
        LoginRequest request = new LoginRequest(UUID.randomUUID().toString(), "Password");
        server.loginUser(request);
        Assertions.assertNull(server.logoutUser("NotAnAuthToken"));
    }

    @Test
    @Order(7)
    @DisplayName("Valid Create Game")
    public void ValidCreateGame() throws IOException {
        LoginRequest request = new LoginRequest("Kyler", "Moulton");
        LoginResult result = server.loginUser(request);
        String gameName = UUID.randomUUID().toString();
        CreateGameRequest gameRequest = new CreateGameRequest(gameName, result.getAuthToken());
        CreateGameResult gameResult = server.createGame(gameRequest);
        int gameID = 0;
        for (GameModel game : server.listGames(result.getAuthToken()).getGamesList()) {
            if (game.getGameID() == gameResult.getGameID()) {
                gameID = game.getGameID();
            }
        }
        Assertions.assertEquals(gameResult.getGameID(), gameID);
    }

    @Test
    @Order(8)
    @DisplayName("Invalid Create Game")
    public void InvalidCreateGame() throws IOException {
        LoginRequest request = new LoginRequest(UUID.randomUUID().toString(), "Password");
        server.loginUser(request);
        String gameName = UUID.randomUUID().toString();
        CreateGameRequest gameRequest = new CreateGameRequest(gameName, "NotAnAuthToken");
        Assertions.assertNull(server.createGame(gameRequest));
    }

    @Test
    @Order(9)
    @DisplayName("Valid List Games")
    public void ValidListGames() throws IOException {
        LoginRequest request = new LoginRequest("Kyler", "Moulton");
        LoginResult result = server.loginUser(request);
        int beforeListLength = server.listGames(result.getAuthToken()).getGamesList().toArray().length;
        String gameName = UUID.randomUUID().toString();
        CreateGameRequest gameRequest = new CreateGameRequest(gameName, result.getAuthToken());
        server.createGame(gameRequest);
        int afterListLength = server.listGames(result.getAuthToken()).getGamesList().toArray().length;
        Assertions.assertNotEquals(beforeListLength, afterListLength);
    }

    @Test
    @Order(10)
    @DisplayName("Invalid List Games")
    public void InvalidListGames() throws IOException {
        LoginRequest request = new LoginRequest("Kyler", "Moulton");
        LoginResult result = server.loginUser(request);
        String gameName = UUID.randomUUID().toString();
        CreateGameRequest gameRequest = new CreateGameRequest(gameName, result.getAuthToken());
        server.createGame(gameRequest);
        Assertions.assertNull(server.listGames("notAnAuthToken"));
    }

    @Test
    @Order(11)
    @DisplayName("Valid Join Game")
    public void ValidJoinGame() throws IOException {
        LoginRequest request = new LoginRequest("Kyler", "Moulton");
        LoginResult result = server.loginUser(request);
        String gameName = UUID.randomUUID().toString();
        CreateGameRequest gameRequest = new CreateGameRequest(gameName, result.getAuthToken());
        CreateGameResult gameResult = server.createGame(gameRequest);
        JoinGameRequest joinRequest = new JoinGameRequest(result.getAuthToken(), "WHITE", gameResult.getGameID());
        JoinGameResult joinResult = server.joinGame(joinRequest);
        String playerName = null;
        for (GameModel game : server.listGames(result.getAuthToken()).getGamesList()) {
            if (Objects.equals(game.getWhiteUsername(), result.getUsername())) {
                playerName = game.getWhiteUsername();
            }
        }
        Assertions.assertEquals(result.getUsername(), playerName);
    }

    @Test
    @Order(12)
    @DisplayName("Invalid Join Game")
    public void InvalidJoinGame() throws IOException {
        LoginRequest request = new LoginRequest("Kyler", "Moulton");
        LoginResult result = server.loginUser(request);
        String gameName = UUID.randomUUID().toString();
        CreateGameRequest gameRequest = new CreateGameRequest(gameName, result.getAuthToken());
        CreateGameResult gameResult = server.createGame(gameRequest);
        JoinGameRequest joinRequest = new JoinGameRequest(result.getAuthToken(), "WHITE", gameResult.getGameID());
        JoinGameResult joinResult = server.joinGame(joinRequest);
        Assertions.assertNull(server.joinGame(joinRequest));
    }
}
