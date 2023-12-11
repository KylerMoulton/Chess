import java.io.IOException;
import java.util.Collection;
import java.util.Objects;
import java.util.Scanner;

import chess.*;
import exception.ResponseException;
import model.GameModel;
import request.*;
import result.*;
import web.ServerFacade;
import websocket.WebSocketFacade;
import websocket.NotificationHandler;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.Random;

import static ui.EscapeSequences.*;

public class Client {
    public static ServerFacade server = new ServerFacade();
    private final String serverUrl;
    private static NotificationHandler notificationHandler;
    private static WebSocketFacade webSocketFacade;
    private static String auth;

    private static boolean whiteSquare = true;

    private static Collection<GameModel> games;

    private static final int BOARD_SIZE_IN_SQUARES = 8;
    private static final int SQUARE_SIZE_IN_CHARS = 8;
    private static final int LINE_WIDTH_IN_CHARS = 1;
    private static final String EMPTY = " ";
    private static Random rand = new Random();
    private static ChessBoard board = new boardImple();

    public Client(String serverUrl, NotificationHandler notificationHandler) {
        this.serverUrl = serverUrl;
        this.notificationHandler = notificationHandler;
    }

    public static void main(String[] args) throws Exception {
//        drawBoard(board);
        System.out.printf("Welcome to Almost Chess. It's almost like chess, but its not.%n\u001b[5mPlease type Start%n\u001b[0m>>> ");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        while (!Objects.equals(line, "Start")) {
            System.out.printf("Invalid Command: To start please type Start%n>>> ");
            line = scanner.nextLine();
        }
        preLoginUI();

    }

    public static void drawBoard(ChessBoard game) {
        var out = new PrintStream(System.out, true, StandardCharsets.UTF_8);
        drawHeaders(out);
        drawSquares(out, game);
        drawHeaders(out);
        out.print("\u001b[39:49;0m");
        out.println();
        drawHeaders2(out);
        drawSquares2(out, game);
        drawHeaders2(out);
    }

    private static void drawSquares(PrintStream out, ChessBoard game) {
        out.print(ERASE_SCREEN);
        out.print(SET_BG_COLOR_DARK_GREY);
        out.print(SET_TEXT_COLOR_WHITE);
        out.print(SET_TEXT_BOLD);
        for (int row = 1; row <= 8; row++) {
            out.print(ERASE_SCREEN);
            out.print(SET_BG_COLOR_DARK_GREY);
            out.print(SET_TEXT_COLOR_WHITE);
            out.print(SET_TEXT_BOLD);
            out.print(" " + row + " ");
            for (int col = 0; col <= 7; col++) {
                ChessPiece curPiece = game.getPiece(new positionImple(row - 1, col));
                String s = switchPiece(curPiece);
                if (curPiece != null) {
                    if (whiteSquare) {
                        out.print(SET_BG_COLOR_WHITE);
                        if (curPiece.getTeamColor() == ChessGame.TeamColor.WHITE) {
                            out.print(SET_TEXT_COLOR_BLUE);
                        } else {
                            out.print(SET_TEXT_COLOR_RED);
                        }
                        out.print(" " + s + " ");
                        //out.print(" " + game.getPiece(new positionImple(row - 1, col)).getPieceType() + " ");
                        whiteSquare = false;
                    } else {
                        out.print(SET_BG_COLOR_BLACK);
                        if (curPiece.getTeamColor() == ChessGame.TeamColor.WHITE) {
                            out.print(SET_TEXT_COLOR_BLUE);
                        } else {
                            out.print(SET_TEXT_COLOR_RED);
                        }
                        out.print(" " + s + " ");
                        //out.print(" " + game.getPiece(new positionImple(row - 1, col)) + " ");
                        whiteSquare = true;
                    }
                } else {
                    if (whiteSquare) {
                        out.print(SET_BG_COLOR_WHITE);
                        out.print("   ");
                        whiteSquare = false;
                    } else {
                        out.print(SET_BG_COLOR_BLACK);
                        out.print("   ");
                        whiteSquare = true;
                    }

                }

            }
            out.print(ERASE_SCREEN);
            out.print(SET_BG_COLOR_DARK_GREY);
            out.print(SET_TEXT_COLOR_WHITE);
            out.print(SET_TEXT_BOLD);
            out.print(" " + row + " ");
            out.print(RESET_BG_COLOR);
            out.print("\u001b[49m");
            out.println();
            whiteSquare = !whiteSquare;
        }
//        out.print("   ");
        out.print(RESET_BG_COLOR);
        out.print("\u001b[49m");
//        out.println();
    }

    private static void drawSquares2(PrintStream out, ChessBoard game) {
        out.print(ERASE_SCREEN);
        out.print(SET_BG_COLOR_DARK_GREY);
        out.print(SET_TEXT_COLOR_WHITE);
        out.print(SET_TEXT_BOLD);
        for (int row = 8; row >= 1; row--) {
            out.print(ERASE_SCREEN);
            out.print(SET_BG_COLOR_DARK_GREY);
            out.print(SET_TEXT_COLOR_WHITE);
            out.print(SET_TEXT_BOLD);
            out.print(" " + row + " ");
            for (int col = 7; col >= 0; col--) {
                ChessPiece curPiece = game.getPiece(new positionImple(row - 1, col));
                String s = switchPiece(curPiece);
                if (curPiece != null) {
                    if (whiteSquare) {
                        out.print(SET_BG_COLOR_WHITE);
                        if (curPiece.getTeamColor() == ChessGame.TeamColor.WHITE) {
                            out.print(SET_TEXT_COLOR_BLUE);
                        } else {
                            out.print(SET_TEXT_COLOR_RED);
                        }
                        out.print(" " + s + " ");
                        //out.print(" " + game.getPiece(new positionImple(row - 1, col)).getPieceType() + " ");
                        whiteSquare = false;
                    } else {
                        out.print(SET_BG_COLOR_BLACK);
                        if (curPiece.getTeamColor() == ChessGame.TeamColor.WHITE) {
                            out.print(SET_TEXT_COLOR_BLUE);
                        } else {
                            out.print(SET_TEXT_COLOR_RED);
                        }
                        out.print(" " + s + " ");
                        //out.print(" " + game.getPiece(new positionImple(row - 1, col)) + " ");
                        whiteSquare = true;
                    }
                } else {
                    if (whiteSquare) {
                        out.print(SET_BG_COLOR_WHITE);
                        out.print("   ");
                        whiteSquare = false;
                    } else {
                        out.print(SET_BG_COLOR_BLACK);
                        out.print("   ");
                        whiteSquare = true;
                    }

                }

            }
            out.print(ERASE_SCREEN);
            out.print(SET_BG_COLOR_DARK_GREY);
            out.print(SET_TEXT_COLOR_WHITE);
            out.print(SET_TEXT_BOLD);
            out.print(" " + row + " ");
            out.print(RESET_BG_COLOR);
            out.print("\u001b[49m");
            out.println();
            whiteSquare = !whiteSquare;
        }
//        out.print("   ");
        out.print(RESET_BG_COLOR);
        out.print("\u001b[49m");
//        out.println();
    }

    private static String switchPiece(ChessPiece piece) {
        if (piece == null) {
            return " ";
        }
        return switch (piece.getPieceType()) {
            case PAWN -> "P";
            case ROOK -> "R";
            case KNIGHT -> "N";
            case KING -> "K";
            case BISHOP -> "B";
            case QUEEN -> "Q";
        };
    }

    private static void drawHeaders(PrintStream out) {
        out.print(ERASE_SCREEN);
        out.print(SET_BG_COLOR_DARK_GREY);
        out.print(SET_TEXT_COLOR_WHITE);
        out.print(SET_TEXT_BOLD);
        out.print("   ");
        String[] headers = {"a", "b", "c", "d", "e", "f", "g", "h"};
        for (int boardCol = 0; boardCol < BOARD_SIZE_IN_SQUARES; ++boardCol) {
            drawHeader(out, headers[boardCol]);
//            if (boardCol < BOARD_SIZE_IN_SQUARES - 1) {
//                out.print(EMPTY.repeat(LINE_WIDTH_IN_CHARS));
//            }
        }
        out.print("   ");
        out.print(RESET_BG_COLOR);
        out.print("\u001b[49m");
        out.println();
    }

    private static void drawHeaders2(PrintStream out) {
        out.print(ERASE_SCREEN);
        out.print(SET_BG_COLOR_DARK_GREY);
        out.print(SET_TEXT_COLOR_WHITE);
        out.print(SET_TEXT_BOLD);
        out.print("   ");
        String[] headers = {"h", "g", "f", "e", "d", "c", "b", "a"};
        for (int boardCol = 0; boardCol < BOARD_SIZE_IN_SQUARES; ++boardCol) {
            drawHeader(out, headers[boardCol]);
//            if (boardCol < BOARD_SIZE_IN_SQUARES - 1) {
//                out.print(EMPTY.repeat(LINE_WIDTH_IN_CHARS));
//            }
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
        out.print(SET_TEXT_COLOR_WHITE);

        out.print(player);

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
        LogoutResult logoutResult = server.logoutUser(auth);
        preLoginUI();
    }

    private static void observe() throws IOException, ResponseException {
        System.out.printf(" Please enter the Game ID of the game you wish to observe%n>>> ");
        Scanner joinGameScanner = new Scanner(System.in);
        String GameID = joinGameScanner.nextLine();
        JoinGameResult joinGameResult = server.joinGame(new JoinGameRequest(auth, null, Integer.parseInt(GameID)));
        for (GameModel game : games) {
            if (Objects.equals(game.getGameID(), Integer.parseInt(GameID))) {
                webSocketFacade = new WebSocketFacade("http://localhost:8080", notificationHandler);
                webSocketFacade.observeGame(Integer.parseInt(GameID), auth, null);
                drawBoard(board);
                postJoin(game);
            }
        }
        postLoginUI();
    }

    private static void join() throws IOException, ResponseException {
        ListGamesResult listGamesResult = server.listGames(auth);
        games = listGamesResult.getGamesList();
        System.out.printf(" Please enter the Game ID of the game you wish to join%n>>> ");
        Scanner joinGameScanner = new Scanner(System.in);
        String GameID = joinGameScanner.nextLine();
        System.out.printf(" Please enter the Team Color you wish to join: WHITE/BLACK%n>>> ");
        Scanner joinGameColorScanner = new Scanner(System.in);
        String Color = joinGameColorScanner.nextLine();
        JoinGameResult joinGameResult = server.joinGame(new JoinGameRequest(auth, Color, Integer.parseInt(GameID)));
        for (GameModel game : games) {
            if (Objects.equals(game.getGameID(), Integer.parseInt(GameID))) {
                webSocketFacade = new WebSocketFacade("http://localhost:8080", notificationHandler);
                webSocketFacade.joinGame(Integer.parseInt(GameID), auth, ChessGame.TeamColor.valueOf(Color));
                drawBoard(game.getGame().getBoard());
                postJoin(game);
            }
        }
        postLoginUI();
    }

    private static void postJoin(GameModel game) throws ResponseException, IOException {
        joinHelp();
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        switch (line) {
            case "R" -> drawBoard(game.getGame().getBoard());
            case "H" -> list();
            case "M" -> makeMove(game);
            case "Resign" -> observe();
            case "Leave" -> logout();
            case "help" -> postJoin(game);
            default -> {
                System.out.print("Invalid Command:");
                postJoin(game);
            }
        }
    }

    private static void makeMove(GameModel game) throws ResponseException, IOException {
        System.out.print("Where is the piece you want to move: ex(1a)");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int row = line.charAt(0);
        int col = line.charAt(1) - 'a';
        ChessPosition startPosition = new positionImple(row, col);
        System.out.print("Where do you want to move: ex(1a)");
        Scanner scanner2 = new Scanner(System.in);
        String line2 = scanner2.nextLine();
        int row2 = line2.charAt(0);
        int col2 = line2.charAt(1) - 'a';
        ChessPosition endPosition = new positionImple(row2, col2);
        ChessPiece.PieceType pieceType = game.getGame().getBoard().getPiece(startPosition).getPieceType();
        ChessMove move = new moveImple(startPosition, endPosition, pieceType);
        webSocketFacade = new WebSocketFacade("http://localhost:8080", notificationHandler);
        webSocketFacade.makeMove(game.getGameID(), auth, move);
        drawBoard(game.getGame().getBoard());
        postJoin(game);
    }

    public static void joinHelp() {
        System.out.print("\u001b[35m");
        System.out.print("  R - Redraw Chess Board\n");
        System.out.print("  H - Highlight Legal Moves");
        System.out.print("  M - Make Move\n");
        System.out.print("  Resign\n");
        System.out.print("  Leave\n");
        System.out.print("  help - View possible commands\n\n");
        System.out.print("\u001b[0m");
        System.out.print(" >>> ");
    }

    private static void list() throws IOException, ResponseException {
        System.out.printf(" Listing games...%n");
        ListGamesResult listGamesResult = server.listGames(auth);
        games = listGamesResult.getGamesList();
        for (GameModel game : listGamesResult.getGamesList()) {
            System.out.printf(Integer.toString(game.getGameID()) + " : " + game.getGameName() + " : White Username-" + game.getWhiteUsername() + " : Black Username-" + game.getBlackUsername() + "\n");
        }
        //System.out.print(listGamesResult.getGamesList());
        System.out.print("\nHere is the list of games\n");
        postLoginUI();
    }

    private static void create() throws IOException, ResponseException {
        System.out.printf(" Thanks for choosing to create a game: Please enter the name of your game%n>>> ");
        Scanner createGameScanner = new Scanner(System.in);
        String gameName = createGameScanner.nextLine();
        CreateGameResult createGameResult = server.createGame(new CreateGameRequest(gameName, auth));
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

    public static void register() throws IOException, ResponseException {
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

    public static void login() throws IOException, ResponseException {
        System.out.printf(" To login please enter your username%n>>> ");
        Scanner usernameScanner = new Scanner(System.in);
        String username = usernameScanner.nextLine();
        System.out.printf(" Please enter your password%n>>> ");
        Scanner passwordScanner = new Scanner(System.in);
        String password = passwordScanner.nextLine();
        LoginResult loginResult = server.loginUser(new LoginRequest(username, password));
        if (loginResult != null) {
            auth = loginResult.getAuthToken();
        } else {
            System.out.print("\nUnable to login: Please check your username and password and try again\n");
            preLoginUI();
        }
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
        System.out.print("  observe <ID> - Spectate a game\n");
        System.out.print("  logout - Logout of your account\n");
        System.out.print("  quit - Quit the application\n");
        System.out.print("  help - View possible commands\n\n");
        System.out.print("\u001b[0m");
        System.out.print(" >>> ");
    }

}