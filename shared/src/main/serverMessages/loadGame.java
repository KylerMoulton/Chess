package serverMessages;

import chess.ChessGame;
import webSocketMessages.serverMessages.ServerMessage;

public class loadGame extends ServerMessage {
    private final ChessGame game;

    public loadGame(ChessGame game) {
        super(ServerMessageType.LOAD_GAME);
        this.game = game;
    }

    public ChessGame getGame() {
        return game;
    }
}
