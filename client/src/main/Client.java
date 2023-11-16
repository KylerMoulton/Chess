import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import request.*;
import result.*;
import web.ServerFacade;

public class Client {
    public static ServerFacade server = new ServerFacade();

    public static void main(String[] args) throws Exception {
        System.out.printf("Welcome to Almost Chess. It's almost like chess, but its not.%n\u001b[5mPlease type Start%n\u001b[0m>>> ");
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        while (!Objects.equals(line, "Start")) {
            System.out.printf("Invalid Command: To start please type Start%n>>> ");
            line = scanner.nextLine();
        }
        preLoginUI();

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
        preLoginUI();
    }

    private static void observe() {
    }

    private static void join() {
    }

    private static void list() {
    }

    private static void create() {
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