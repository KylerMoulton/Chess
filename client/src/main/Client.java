import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import model.GameModel;
import request.*;
import result.*;
import web.ServerFacade;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;

import static ui.EscapeSequences.*;

public class Client {
    public static ServerFacade server = new ServerFacade();
    private static String auth;

    private static final int BOARD_SIZE_IN_SQUARES = 8;
    private static final int SQUARE_SIZE_IN_CHARS = 8;
    private static final int LINE_WIDTH_IN_CHARS = 1;
    private static final String EMPTY = " ";
    private static Random rand = new Random();

    public static void main(String[] args) throws Exception {
        //drawBoard();
        System.out.printf("Welcome to Almost Chess. It's almost like chess, but its not.%n\u001b[5mPlease type Start%n\u001b[0m>>> ");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        while (!Objects.equals(line, "Start")) {
            System.out.printf("Invalid Command: To start please type Start%n>>> ");
            line = scanner.nextLine();
        }
        preLoginUI();

    }

    public static void drawBoard() {
        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        out.print(ERASE_SCREEN);
        out.print(SET_BG_COLOR_DARK_GREY);
        out.print(SET_TEXT_COLOR_BLACK);
        out.print(SET_TEXT_BOLD);
        drawHeaders(out);
        drawSquares(out);
        out.print("\u001b[39:49;0m");
    }

    private static void drawSquares(PrintStream out) {

    }

    private static void drawHeaders(PrintStream out) {
        out.print("   ");
        String[] headers = {"a", "b", "c", "d", "e", "f", "g", "h"};
        for (int boardCol = 0; boardCol < BOARD_SIZE_IN_SQUARES; ++boardCol) {
            drawHeader(out, headers[boardCol]);

            if (boardCol < BOARD_SIZE_IN_SQUARES - 1) {
                out.print(EMPTY.repeat(LINE_WIDTH_IN_CHARS));
            }
        }
        out.print("   ");
        out.print(RESET_BG_COLOR);
        out.print("\u001b[49m");
        out.println();
    }

    private static void drawHeader(PrintStream out, String headerText) {
        int prefixLength = 1;
        int suffixLength = 1;

        out.print(EMPTY.repeat(prefixLength));
        printHeaderText(out, headerText);
        out.print(EMPTY.repeat(suffixLength));
    }

    private static void printHeaderText(PrintStream out, String player) {
        out.print(SET_BG_COLOR_DARK_GREY);
        out.print(SET_TEXT_COLOR_BLACK);

        out.print(player);

    }


    public static void preLoginUI() throws IOException {
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

    public static void postLoginUI() throws IOException {
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
                preLoginUI();
            }
        }
    }

    private static void logout() throws IOException {
        System.out.print("  Thanks for playing Almost Chess\n");
        LogoutResult logoutResult = server.logoutUser(auth);
        preLoginUI();
    }

    private static void observe() throws IOException {
        System.out.printf(" Please enter the Game ID of the game you wish to observe%n>>> ");
        Scanner joinGameScanner = new Scanner(System.in);
        String GameID = joinGameScanner.nextLine();
        JoinGameResult joinGameResult = server.joinGame(new JoinGameRequest(auth, null, Integer.parseInt(GameID)));
        drawBoard();
        postLoginUI();
    }

    private static void join() throws IOException {
        System.out.printf(" Please enter the Game ID of the game you wish to join%n>>> ");
        Scanner joinGameScanner = new Scanner(System.in);
        String GameID = joinGameScanner.nextLine();
        System.out.printf(" Please enter the Team Color you wish to join: WHITE/BLACK%n>>> ");
        Scanner joinGameColorScanner = new Scanner(System.in);
        String Color = joinGameColorScanner.nextLine();
        JoinGameResult joinGameResult = server.joinGame(new JoinGameRequest(auth, Color, Integer.parseInt(GameID)));
        drawBoard();
        postLoginUI();
    }

    private static void list() throws IOException {
        System.out.printf(" Listing games...%n");
        ListGamesResult listGamesResult = server.listGames(auth);
        for (GameModel game : listGamesResult.getGamesList()) {
            System.out.printf(Integer.toString(game.getGameID()) + " : " + game.getGameName() + " : White Username-" + game.getWhiteUsername() + " : Black Username-" + game.getBlackUsername() + "\n");
        }
        //System.out.print(listGamesResult.getGamesList());
        System.out.print("\nHere is the list of games\n");
        postLoginUI();
    }

    private static void create() throws IOException {
        System.out.printf(" Thanks for choosing to create a game: Please enter the name of your game%n>>> ");
        Scanner createGameScanner = new Scanner(System.in);
        String gameName = createGameScanner.nextLine();
        CreateGameResult createGameResult = server.createGame(new CreateGameRequest(gameName, auth));
        drawBoard();
        postLoginUI();
    }

    public static void preHelp() {
        System.out.print("\u001b[35m");
        System.out.print("  register <USERNAME> <PASSWORD> <EMAIL> - Register a new account\n");
        System.out.print("  login <USERNAME> <PASSWORD> - Login to your account\n");
        System.out.print("  quit - Quit the application\n");
        System.out.print("  help - View possible commands\n\n");
        System.out.print("\u001b[0m");
        System.out.print(" >>> ");
    }

    public static void register() throws IOException {
        System.out.printf(" Thanks for choosing to register: Please choose your username%n>>> ");
        Scanner usernameScanner = new Scanner(System.in);
        String username = usernameScanner.nextLine();
        System.out.printf(" Please choose your password%n>>> ");
        Scanner passwordScanner = new Scanner(System.in);
        String password = passwordScanner.nextLine();
        System.out.printf(" Please enter your email%n>>> ");
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

    public static void login() throws IOException {
        System.out.printf(" To login please enter your username%n>>> ");
        Scanner usernameScanner = new Scanner(System.in);
        String username = usernameScanner.nextLine();
        System.out.printf(" Please enter your password%n>>> ");
        Scanner passwordScanner = new Scanner(System.in);
        String password = passwordScanner.nextLine();
        LoginResult loginResult = server.loginUser(new LoginRequest(username, password));
        auth = loginResult.getAuthToken();
        System.out.printf("Logged in as " + loginResult.getUsername() + "\n");
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
        System.out.print("  spectate <ID> - Spectate a game\n");
        System.out.print("  logout - Logout of your account\n");
        System.out.print("  quit - Quit the application\n");
        System.out.print("  help - View possible commands\n\n");
        System.out.print("\u001b[0m");
        System.out.print(" >>> ");
    }

}