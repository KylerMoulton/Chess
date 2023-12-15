package websocket;

import chess.ChessGame;
import chess.gameImple;
import com.google.gson.Gson;
import serverMessages.errorMessage;
import serverMessages.loadGame;
import serverMessages.notificationMessage;
import webSocketMessages.serverMessages.ServerMessage;
import printBoard.DrawChessBoard;

public class NotificationHandler {
    public NotificationHandler(String color) {
        playerColor = color;
    }

    private static String playerColor;

    public static void notify(String message) {
        try {
            ServerMessage serverMessage = new Gson().fromJson(message, ServerMessage.class);
            switch (serverMessage.getServerMessageType()) {
                case ERROR -> {
                    errorMessage error = new Gson().fromJson(message, errorMessage.class);
                    System.out.print(error.getNotificationMessage() + "\n");
                }
                case NOTIFICATION -> {
                    notificationMessage notification = new Gson().fromJson(message, notificationMessage.class);
                    System.out.print(notification.getNotificationMessage() + "\n");

                }
                case LOAD_GAME -> {
                    ChessGame gameNotification = gameImple.serialization().fromJson(message, ChessGame.class);
                    if (playerColor == null) {
                        DrawChessBoard.drawBoard(gameNotification.getBoard(), ChessGame.TeamColor.WHITE);

                    } else {
                        DrawChessBoard.drawBoard(gameNotification.getBoard(), ChessGame.TeamColor.valueOf(playerColor));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable t) {
            System.out.println(t.getMessage());
        }
    }
}
