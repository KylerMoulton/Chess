import chess.*;
import exception.ResponseException;
import model.GameModel;
import printBoard.DrawChessBoard;
import request.CreateGameRequest;
import request.JoinGameRequest;
import request.LoginRequest;
import request.RegisterRequest;
import result.*;
import web.ServerFacade;
import websocket.NotificationHandler;
import websocket.WebSocketFacade;

import java.io.IOException;
import java.util.Collection;
import java.util.Objects;
import java.util.Scanner;

public class Client {
    public static ServerFacade server = new ServerFacade();
    private static WebSocketFacade webSocketFacade;
    private static String auth;

    private static Collection<GameModel> games;

    private static String playerColor;


    public static void main(String[] args) throws Exception {
//        drawBoard(board);
        System.out.printf("Welcome to Almost Chess. It's almost like chess, but its not.%n\u001b[5mPlease type Start%n\u001b[0m");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        while (!Objects.equals(line, "Start")) {
            System.out.printf("Invalid Command: To start please type Start%n");
            line = scanner.nextLine();
        }
        preLoginUI();

    }


    public static void preLoginUI() throws IOException, ResponseException {
        preHelp();
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        switch (line) {
            case "register" -> register();
            case "login" -> login();
            case "quit" -> quit();
            case "help" -> preLoginUI();
            default -> {
                System.out.print("Invalid Command:");
                preLoginUI();
            }
        }
    }

    public static void postLoginUI() throws IOException, ResponseException {
        postHelp();
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        switch (line) {
            case "create" -> create();
            case "list" -> list();
            case "join" -> join();
            case "observe" -> observe();
            case "logout" -> logout();
            case "quit" -> quit();
            case "help" -> postLoginUI();
            default -> {
                System.out.print("Invalid Command:");
                postLoginUI();
            }
        }
    }

    private static void logout() throws IOException, ResponseException {
        System.out.print("  Thanks for playing Almost Chess\n");
        server.logoutUser(auth);
        preLoginUI();
    }

    private static void observe() throws IOException, ResponseException {
        ListGamesResult listGamesResult = server.listGames(auth);
        games = listGamesResult.getGamesList();
        System.out.printf(" Please enter the Game ID of the game you wish to observe%n");
        Scanner joinGameScanner = new Scanner(System.in);
        String GameID = joinGameScanner.nextLine();
        server.joinGame(new JoinGameRequest(auth, null, Integer.parseInt(GameID)));
        playerColor = null;
        for (GameModel game : games) {
            if (Objects.equals(game.getGameID(), Integer.parseInt(GameID))) {
                webSocketFacade = new WebSocketFacade("http://localhost:8080", new NotificationHandler(playerColor));
                webSocketFacade.observeGame(Integer.parseInt(GameID), auth, null);
                //drawBoard(game.getGame().getBoard(), null);
                observerPostJoin(game, null);
            }
        }
        postLoginUI();
    }

    private static void join() throws IOException, ResponseException {
        ListGamesResult listGamesResult = server.listGames(auth);
        games = listGamesResult.getGamesList();
        System.out.printf(" Please enter the Game ID of the game you wish to join%n ");
        Scanner joinGameScanner = new Scanner(System.in);
        String GameID = joinGameScanner.nextLine();
        System.out.printf(" Please enter the Team Color you wish to join: WHITE/BLACK%n ");
        Scanner joinGameColorScanner = new Scanner(System.in);
        String Color = joinGameColorScanner.nextLine();
        playerColor = Color;
        server.joinGame(new JoinGameRequest(auth, Color, Integer.parseInt(GameID)));
        for (GameModel game : games) {
            if (Objects.equals(game.getGameID(), Integer.parseInt(GameID))) {
                webSocketFacade = new WebSocketFacade("http://localhost:8080", new NotificationHandler(playerColor));
                webSocketFacade.joinGame(Integer.parseInt(GameID), auth, ChessGame.TeamColor.valueOf(Color));
                //drawBoard(game.getGame().getBoard(), ChessGame.TeamColor.valueOf(Color));
                postJoin(game, Color);
            }
        }
        postLoginUI();
    }

    private static void observerPostJoin(GameModel game, String Color) throws ResponseException, IOException {
        joinHelpObserver();
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        switch (line) {
            case "R" -> {
                DrawChessBoard.drawBoard(game.getGame().getBoard(), null);
                observerPostJoin(game, Color);
            }
            case "Leave" -> leave(game, Color);
            case "help" -> observerPostJoin(game, Color);
            default -> {
                System.out.print("Invalid Command:");
                observerPostJoin(game, Color);
            }
        }
    }

    private static void postJoin(GameModel game, String Color) throws ResponseException, IOException {
        joinHelp();
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        switch (line) {
            case "R" -> {
                DrawChessBoard.drawBoard(game.getGame().getBoard(), ChessGame.TeamColor.valueOf(Color));
                postJoin(game, Color);
            }
            case "H" -> list();
            case "M" -> makeMove(game, Color);
            case "Resign" -> resign(game, Color);
            case "Leave" -> leave(game, Color);
            case "help" -> postJoin(game, Color);
            default -> {
                System.out.print("Invalid Command:");
                postJoin(game, Color);
            }
        }
    }

    private static void leave(GameModel game, String Color) throws ResponseException, IOException {
        System.out.printf("Are you sure you would like to leave the game? (Y/N)%n");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        if (Objects.equals(line, "N")) {
            postJoin(game, Color);
        } else if (Objects.equals(line, "Y")) {
            webSocketFacade = new WebSocketFacade("http://localhost:8080", new NotificationHandler(Color));
            webSocketFacade.leave(game.getGameID(), auth, null);
            postLoginUI();
        }
    }

    private static void resign(GameModel game, String Color) throws ResponseException, IOException {
        System.out.printf("Are you sure you would like to resign? (Y/N)%n");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        if (Objects.equals(line, "N")) {
            postJoin(game, Color);
        } else if (Objects.equals(line, "Y")) {
            webSocketFacade = new WebSocketFacade("http://localhost:8080", new NotificationHandler(Color));
            webSocketFacade.resign(game.getGameID(), auth, null);
            postJoin(game, Color);
        }
    }

    private static void makeMove(GameModel game, String Color) throws ResponseException, IOException {
        System.out.printf("Where is the piece you want to move: ex(1a)%n");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int row = line.charAt(0) - 48;
        int col = line.charAt(1) - 'a' + 1;
        ChessPosition startPosition = new positionImple(row, col);
        System.out.printf("Where do you want to move: ex(1a)%n");
        Scanner scanner2 = new Scanner(System.in);
        String line2 = scanner2.nextLine();
        int row2 = line2.charAt(0) - 48;
        int col2 = line2.charAt(1) - 'a' + 1;
        ChessPosition endPosition = new positionImple(row2, col2);
        ChessPiece.PieceType pieceType = game.getGame().getBoard().getPiece(startPosition).getPieceType();
        ChessMove move = new moveImple(startPosition, endPosition, pieceType);
        webSocketFacade = new WebSocketFacade("http://localhost:8080", new NotificationHandler(Color));
        webSocketFacade.makeMove(game.getGameID(), auth, move);
        //drawBoard(game.getGame().getBoard(), ChessGame.TeamColor.valueOf(Color));
        postJoin(game, Color);
    }

    public static void joinHelp() {
        System.out.print("\u001b[35m");
        System.out.print("  R - Redraw Chess Board\n");
        System.out.print("  H - Highlight Legal Moves\n");
        System.out.print("  M - Make Move\n");
        System.out.print("  Resign\n");
        System.out.print("  Leave\n");
        System.out.print("  help - View possible commands\n\n");
        System.out.print("\u001b[0m");
    }

    public static void joinHelpObserver() {
        System.out.print("\u001b[35m");
        System.out.print("  R - Redraw Chess Board\n");
        System.out.print("  Leave\n");
        System.out.print("  help - View possible commands\n\n");
        System.out.print("\u001b[0m");
    }

    private static void list() throws IOException, ResponseException {
        System.out.printf(" Listing games...%n");
        ListGamesResult listGamesResult = server.listGames(auth);
        games = listGamesResult.getGamesList();
        for (GameModel game : listGamesResult.getGamesList()) {
            System.out.printf(game.getGameID() + " : " + game.getGameName() + " : White Username-" + game.getWhiteUsername() + " : Black Username-" + game.getBlackUsername() + "\n");
        }
        //System.out.print(listGamesResult.getGamesList());
        System.out.print("\nHere is the list of games\n");
        postLoginUI();
    }

    private static void create() throws IOException, ResponseException {
        System.out.printf(" Thanks for choosing to create a game: Please enter the name of your game%n ");
        Scanner createGameScanner = new Scanner(System.in);
        String gameName = createGameScanner.nextLine();
        server.createGame(new CreateGameRequest(gameName, auth));
        postLoginUI();
    }

    public static void preHelp() {
        System.out.print("\u001b[35m");
        System.out.print("  register <USERNAME> <PASSWORD> <EMAIL> - Register a new account\n");
        System.out.print("  login <USERNAME> <PASSWORD> - Login to your account\n");
        System.out.print("  quit - Quit the application\n");
        System.out.print("  help - View possible commands\n\n");
        System.out.print("\u001b[0m");
    }

    public static void register() throws IOException, ResponseException {
        System.out.printf(" Thanks for choosing to register: Please choose your username%n ");
        Scanner usernameScanner = new Scanner(System.in);
        String username = usernameScanner.nextLine();
        System.out.printf(" Please choose your password%n");
        Scanner passwordScanner = new Scanner(System.in);
        String password = passwordScanner.nextLine();
        System.out.printf(" Please enter your email%n");
        Scanner emailScanner = new Scanner(System.in);
        String email = emailScanner.nextLine();
        RegisterResult registerResult = server.registerUser(new RegisterRequest(username, password, email));
        if (registerResult != null) {
            auth = registerResult.getAuthToken();
        } else {
            System.out.print("\nUnable to register: Please check your username, password, and email and try again\n");
            System.out.print("If you have already registered please try logging in instead\n");
            preLoginUI();
        }
        assert registerResult != null;
        System.out.print(" Thanks for registering with us " + registerResult.getUsername() + "! Don't worry we only share this information to those who subscribe to our information access subscription\n");
        postLoginUI();
    }

    public static void login() throws IOException, ResponseException {
        System.out.printf(" To login please enter your username%n");
        Scanner usernameScanner = new Scanner(System.in);
        String username = usernameScanner.nextLine();
        System.out.printf(" Please enter your password%n");
        Scanner passwordScanner = new Scanner(System.in);
        String password = passwordScanner.nextLine();
        LoginResult loginResult = server.loginUser(new LoginRequest(username, password));
        if (loginResult != null) {
            auth = loginResult.getAuthToken();
        } else {
            System.out.print("\nUnable to login: Please check your username and password and try again\n");
            preLoginUI();
        }
        if (loginResult != null) {
            System.out.printf("Logged in as " + loginResult.getUsername() + "\n");
        }
        postLoginUI();
    }

    public static void quit() {

        System.out.print("  Thanks for playing Almost Chess");
    }

    public static void postHelp() {
        System.out.print("\u001b[35m");
        System.out.print("  create <NAME> - Creates a new game\n");
        System.out.print("  list - Lists all games\n");
        System.out.print("  join <ID> [WHITE|BLACK] - Join a game\n");
        System.out.print("  observe <ID> - Spectate a game\n");
        System.out.print("  logout - Logout of your account\n");
        System.out.print("  quit - Quit the application\n");
        System.out.print("  help - View possible commands\n\n");
        System.out.print("\u001b[0m");
    }
}