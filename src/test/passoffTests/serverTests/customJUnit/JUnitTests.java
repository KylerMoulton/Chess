package passoffTests.serverTests.customJUnit;


import chess.ChessGame;
import chess.gameImple;
import exeptions.AlreadyTakenException;
import exeptions.BadReqException;
import exeptions.UnauthorizedException;
import model.*;
import service.*;
import request.*;
import result.*;
import org.junit.jupiter.api.*;
import passoffTests.obfuscatedTestClasses.TestServerFacade;
import passoffTests.testClasses.TestModels;
import spark.utils.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JUnitTests {
    private static final UserModel User = new UserModel("Kyler", "Moulton", "email");
    private static final UserModel User2 = new UserModel("Kaleb", "Moulton", "email");

    private static final GameModel Game = new GameModel(1,null,null,"My Game", new gameImple());
    @BeforeEach
    public void reset() throws Exception {
        new ClearService().clear();
    }
    @Test
    @Order(1)
    @DisplayName("Valid Registration")
    public void successRegister() throws Exception {
        RegisterRequest newRequest = new RegisterRequest(User.getUsername(), User.getPassword(), User.getEmail());
        RegisterService registerNewUser = new RegisterService();
        RegisterResult newResult = registerNewUser.register(newRequest);

        Assertions.assertEquals(newResult.getUsername(), User.getUsername(), "Usernames did not match");
        Assertions.assertNotNull(newResult.getAuthToken(), "Authtoken was null");
        Assertions.assertNull(newResult.getMessage(), "Incorrect Error Message");
    }

    @Test
    @Order(2)
    @DisplayName("Invalid Registration")
    public void unseccessRegister() throws Exception {
        RegisterResult newResult = null;
        try {
            RegisterRequest newRequest = new RegisterRequest(null, User.getPassword(), User.getEmail());
            RegisterService registerNewUser = new RegisterService();
            newResult = registerNewUser.register(newRequest);

        } catch (BadReqException e) {
            Assertions.assertEquals(e.getMessage(), "Error: bad request", "Incorrect Error Thrown");
        }
    }
    @Test
    @Order(3)
    @DisplayName("Valid Login")
    public void successLogin() throws Exception {
        RegisterRequest newRequest = new RegisterRequest(User.getUsername(), User.getPassword(), User.getEmail());
        RegisterService registerNewUser = new RegisterService();
        RegisterResult newResult = registerNewUser.register(newRequest);

        LoginRequest newLoginRequest = new LoginRequest(newResult.getUsername(), newRequest.getPassword());
        LoginService loginUser = new LoginService();
        LoginResult loginResult = loginUser.login(newLoginRequest);

        Assertions.assertEquals(loginResult.getUsername(),User.getUsername(),"Invalid Username");
        Assertions.assertNotNull(newResult.getAuthToken(), "Authtoken was null");
        Assertions.assertNull(loginResult.getMessage(), "Incorrect Error Message");
    }
    @Test
    @Order(4)
    @DisplayName("Invalid Login")
    public void UnsuccessLogin() throws Exception {
        LoginResult loginResult = null;
        try {
            RegisterRequest newRequest = new RegisterRequest(User.getUsername(), User.getPassword(), User.getEmail());
            RegisterService registerNewUser = new RegisterService();
            RegisterResult newResult = registerNewUser.register(newRequest);

            LoginRequest newLoginRequest = new LoginRequest(newResult.getUsername(), "WrongPassword");
            LoginService loginUser = new LoginService();
            loginResult = loginUser.login(newLoginRequest);

        } catch (UnauthorizedException e) {
            Assertions.assertEquals(e.getMessage(), "Error: unauthorized", "Incorrect Error Thrown");
        }
    }
    @Test
    @Order(5)
    @DisplayName("Valid Logout")
    public void successLogout() throws Exception {
        RegisterRequest newRequest = new RegisterRequest(User.getUsername(), User.getPassword(), User.getEmail());
        RegisterService registerNewUser = new RegisterService();
        RegisterResult newResult = registerNewUser.register(newRequest);

        LoginRequest newLoginRequest = new LoginRequest(newResult.getUsername(), newRequest.getPassword());
        LoginService loginUser = new LoginService();
        LoginResult loginResult = loginUser.login(newLoginRequest);

        LogoutService logoutUser = new LogoutService();
        LogoutResult logoutResult = logoutUser.logout(loginResult.getAuthToken());

        Assertions.assertNull(logoutResult.getMessage(), "Returned an Error Message");
    }
    @Test
    @Order(6)
    @DisplayName("Invalid Logout")
    public void unsuccessLogout() throws Exception {
        try {
            RegisterRequest newRequest = new RegisterRequest(User.getUsername(), User.getPassword(), User.getEmail());
            RegisterService registerNewUser = new RegisterService();
            RegisterResult newResult = registerNewUser.register(newRequest);

            LoginRequest newLoginRequest = new LoginRequest(newResult.getUsername(), newRequest.getPassword());
            LoginService loginUser = new LoginService();
            LoginResult loginResult = loginUser.login(newLoginRequest);

            LogoutService logoutUser = new LogoutService();
            LogoutResult logoutResult = logoutUser.logout(loginResult.getAuthToken()+1);

        } catch (UnauthorizedException e) {
            Assertions.assertEquals(e.getMessage(), "Error: unauthorized", "Incorrect Error Thrown");
        }
    }
    @Test
    @Order(7)
    @DisplayName("Valid Create Game")
    public void successCreateGame() throws Exception {
        RegisterRequest newRequest = new RegisterRequest(User.getUsername(), User.getPassword(), User.getEmail());
        RegisterService registerNewUser = new RegisterService();
        RegisterResult newResult = registerNewUser.register(newRequest);

        CreateGameRequest newGameRequest = new CreateGameRequest(Game.getGameName(),newResult.getAuthToken());
        CreateGameService createNewGame = new CreateGameService();
        CreateGameResult newGame = createNewGame.createGame(newGameRequest, newResult.getAuthToken());

        Assertions.assertNotNull((Integer) newGame.getGameID(),"No gameID returned");
        Assertions.assertNotNull(newResult.getAuthToken(), "Authtoken was null");
        Assertions.assertNull(newGame.getMessage(), "Returned an Error Message");
    }
    @Test
    @Order(8)
    @DisplayName("Invalid Create Game")
    public void unsuccessCreateGame() throws Exception {
        try {
            RegisterRequest newRequest = new RegisterRequest(User.getUsername(), User.getPassword(), User.getEmail());
            RegisterService registerNewUser = new RegisterService();
            RegisterResult newResult = registerNewUser.register(newRequest);

            CreateGameRequest newGameRequest = new CreateGameRequest(Game.getGameName(), newResult.getAuthToken());
            CreateGameService createNewGame = new CreateGameService();
            CreateGameResult newGame = createNewGame.createGame(newGameRequest, newResult.getAuthToken()+1);

        } catch (UnauthorizedException e) {
            Assertions.assertEquals(e.getMessage(), "Error: unauthorized", "Incorrect Error Thrown");
        }
    }
    @Test
    @Order(9)
    @DisplayName("Valid Join Game")
    public void sucessJoinGame() throws Exception {
        RegisterRequest newRequest = new RegisterRequest(User.getUsername(), User.getPassword(), User.getEmail());
        RegisterService registerNewUser = new RegisterService();
        RegisterResult newResult = registerNewUser.register(newRequest);

        RegisterRequest newRequest2 = new RegisterRequest(User2.getUsername(), User2.getPassword(), User2.getEmail());
        RegisterService registerNewUser2 = new RegisterService();
        RegisterResult newResult2 = registerNewUser.register(newRequest2);

        CreateGameRequest newGameRequest = new CreateGameRequest(Game.getGameName(), newResult.getAuthToken());
        CreateGameService createNewGame = new CreateGameService();
        CreateGameResult newGame = createNewGame.createGame(newGameRequest, newResult.getAuthToken());

        JoinGameRequest newJoinGameRequest = new JoinGameRequest(newResult.getAuthToken(),"WHITE",newGame.getGameID());
        JoinGameService createJoinGame = new JoinGameService();
        JoinGameResult joinGame = createJoinGame.joinGame(newJoinGameRequest,newResult.getAuthToken());

        JoinGameRequest newJoinGameRequest2 = new JoinGameRequest(newResult2.getAuthToken(),"BLACK",newGame.getGameID());
        JoinGameService createJoinGame2 = new JoinGameService();
        JoinGameResult joinGame2 = createJoinGame.joinGame(newJoinGameRequest2,newResult2.getAuthToken());

        Assertions.assertNull(newGame.getMessage(), "Returned an Error Message");
    }
    @Test
    @Order(10)
    @DisplayName("Invalid Join Game")
    public void unsucessJoinGame() throws Exception {
        try {
            RegisterRequest newRequest = new RegisterRequest(User.getUsername(), User.getPassword(), User.getEmail());
            RegisterService registerNewUser = new RegisterService();
            RegisterResult newResult = registerNewUser.register(newRequest);

            RegisterRequest newRequest2 = new RegisterRequest(User2.getUsername(), User2.getPassword(), User2.getEmail());
            RegisterService registerNewUser2 = new RegisterService();
            RegisterResult newResult2 = registerNewUser.register(newRequest2);

            CreateGameRequest newGameRequest = new CreateGameRequest(Game.getGameName(), newResult.getAuthToken());
            CreateGameService createNewGame = new CreateGameService();
            CreateGameResult newGame = createNewGame.createGame(newGameRequest, newResult.getAuthToken());

            JoinGameRequest newJoinGameRequest = new JoinGameRequest(newResult.getAuthToken(), "WHITE", newGame.getGameID());
            JoinGameService createJoinGame = new JoinGameService();
            JoinGameResult joinGame = createJoinGame.joinGame(newJoinGameRequest, newResult.getAuthToken());

            JoinGameRequest newJoinGameRequest2 = new JoinGameRequest(newResult2.getAuthToken(), "WHITE", newGame.getGameID());
            JoinGameService createJoinGame2 = new JoinGameService();
            JoinGameResult joinGame2 = createJoinGame.joinGame(newJoinGameRequest2, newResult2.getAuthToken());
        } catch (AlreadyTakenException e){
            Assertions.assertEquals(e.getMessage(), "Error: already taken", "Incorrect Error Thrown");
        }
    }
    @Test
    @Order(11)
    @DisplayName("Valid List Games")
    public void successListGames() throws Exception {
        RegisterRequest newRequest = new RegisterRequest(User.getUsername(), User.getPassword(), User.getEmail());
        RegisterService registerNewUser = new RegisterService();
        RegisterResult newResult = registerNewUser.register(newRequest);

        CreateGameRequest newGameRequest = new CreateGameRequest(Game.getGameName(), newResult.getAuthToken());
        CreateGameService createNewGame = new CreateGameService();
        CreateGameResult newGame = createNewGame.createGame(newGameRequest, newResult.getAuthToken());

        CreateGameRequest newGameRequest2 = new CreateGameRequest(Game.getGameName(), newResult.getAuthToken());
        CreateGameService createNewGame2 = new CreateGameService();
        CreateGameResult newGame2 = createNewGame2.createGame(newGameRequest2, newResult.getAuthToken());

        CreateGameRequest newGameRequest3 = new CreateGameRequest(Game.getGameName(), newResult.getAuthToken());
        CreateGameService createNewGame3 = new CreateGameService();
        CreateGameResult newGame3 = createNewGame3.createGame(newGameRequest3, newResult.getAuthToken());

        CreateGameRequest newGameRequest4 = new CreateGameRequest(Game.getGameName(), newResult.getAuthToken());
        CreateGameService createNewGame4 = new CreateGameService();
        CreateGameResult newGame4 = createNewGame4.createGame(newGameRequest4, newResult.getAuthToken());

        ListGamesService createListGames = new ListGamesService();
        ListGamesResult listGames = createListGames.listGames(newResult.getAuthToken());

        //Expected games
        GameModel game1 = new GameModel(newGame.getGameID(),null,null,Game.getGameName(),Game.getGame());
        GameModel game2 = new GameModel(newGame2.getGameID(),null,null,Game.getGameName(),Game.getGame());
        GameModel game3 = new GameModel(newGame3.getGameID(),null,null,Game.getGameName(),Game.getGame());
        GameModel game4 = new GameModel(newGame4.getGameID(),null,null,Game.getGameName(),Game.getGame());
        HashSet<GameModel> setOfGames = new HashSet<>();
        setOfGames.add(game1);
        setOfGames.add(game2);
        setOfGames.add(game3);
        setOfGames.add(game4);
        ArrayList<GameModel> ExpectedGames = new ArrayList<>(setOfGames);
        Collections.sort(ExpectedGames);
        ArrayList<GameModel> CreatedGames = new ArrayList<>(listGames.getGamesList());
        Collections.sort(CreatedGames);
        Assertions.assertEquals(ExpectedGames,CreatedGames,"Sets of Game Not equall to each other");
    }
    @Test
    @Order(12)
    @DisplayName("Invalid List Games")
    public void unsuccessListGames() throws Exception {
        try {
            RegisterRequest newRequest = new RegisterRequest(User.getUsername(), User.getPassword(), User.getEmail());
            RegisterService registerNewUser = new RegisterService();
            RegisterResult newResult = registerNewUser.register(newRequest);

            CreateGameRequest newGameRequest = new CreateGameRequest(Game.getGameName(), newResult.getAuthToken() + 1);
            CreateGameService createNewGame = new CreateGameService();
            CreateGameResult newGame = createNewGame.createGame(newGameRequest, newResult.getAuthToken());

            CreateGameRequest newGameRequest2 = new CreateGameRequest(Game.getGameName(), newResult.getAuthToken());
            CreateGameService createNewGame2 = new CreateGameService();
            CreateGameResult newGame2 = createNewGame2.createGame(newGameRequest2, newResult.getAuthToken());

            CreateGameRequest newGameRequest3 = new CreateGameRequest(Game.getGameName(), newResult.getAuthToken());
            CreateGameService createNewGame3 = new CreateGameService();
            CreateGameResult newGame3 = createNewGame3.createGame(newGameRequest3, newResult.getAuthToken());

            CreateGameRequest newGameRequest4 = new CreateGameRequest(Game.getGameName(), newResult.getAuthToken());
            CreateGameService createNewGame4 = new CreateGameService();
            CreateGameResult newGame4 = createNewGame4.createGame(newGameRequest4, newResult.getAuthToken());

            ListGamesService createListGames = new ListGamesService();
            ListGamesResult listGames = createListGames.listGames(newResult.getAuthToken());

            //Expected games
            GameModel game1 = new GameModel(newGame.getGameID(), null, null, Game.getGameName(), Game.getGame());
            GameModel game2 = new GameModel(newGame2.getGameID(), null, null, Game.getGameName(), Game.getGame());
            GameModel game3 = new GameModel(newGame3.getGameID(), null, null, Game.getGameName(), Game.getGame());
            GameModel game4 = new GameModel(newGame4.getGameID(), null, null, Game.getGameName(), Game.getGame());
            HashSet<GameModel> setOfGames = new HashSet<>();
            setOfGames.add(game1);
            setOfGames.add(game2);
            setOfGames.add(game3);
            setOfGames.add(game4);
            ArrayList<GameModel> ExpectedGames = new ArrayList<>(setOfGames);
            Collections.sort(ExpectedGames);
            ArrayList<GameModel> CreatedGames = new ArrayList<>(listGames.getGamesList());
            Collections.sort(CreatedGames);
        } catch (UnauthorizedException e) {
            Assertions.assertEquals(e.getMessage(), "Error: unauthorized", "Incorrect Error Thrown");
        }
    }
    @Test
    @Order(13)
    @DisplayName("Valid Clear")
    public void successClear() throws Exception {
        ClearService clear = new ClearService();
        ClearResult clearResult = clear.clear();
        Assertions.assertNull(clearResult.getMessage(), "Incorrect Error Message");
    }
}
