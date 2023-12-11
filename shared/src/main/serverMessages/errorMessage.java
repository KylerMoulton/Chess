package serverMessages;

import webSocketMessages.serverMessages.ServerMessage;

public class errorMessage extends ServerMessage {
    private final String errorMessage;

    public errorMessage(String errorMessage) {
        super(ServerMessageType.ERROR);
        this.errorMessage = errorMessage;
    }

    public String getNotificationMessage() {
        return errorMessage;
    }
}