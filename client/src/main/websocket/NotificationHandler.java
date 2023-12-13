package websocket;

import chess.ChessGame;
import com.google.gson.Gson;
import serverMessages.errorMessage;
import serverMessages.loadGame;
import serverMessages.notificationMessage;
import webSocketMessages.serverMessages.ServerMessage;
import printBoard.DrawChessBoard;

public class NotificationHandler {
    public NotificationHandler(String color) {
        this.playerColor = color;
    }

    private String playerColor;

    public void notify(ServerMessage notification, ChessGame game, Error errorMessage) {
        switch (notification.getServerMessageType()) {
            case ERROR -> System.out.print(errorMessage.getMessage());
            case NOTIFICATION -> System.out.print(((notificationMessage) notification).getNotificationMessage());
            case LOAD_GAME -> DrawChessBoard.drawBoard(game.getBoard(), ChessGame.TeamColor.valueOf(playerColor));
        }
    }
}
