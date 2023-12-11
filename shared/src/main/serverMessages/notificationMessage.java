package serverMessages;

import webSocketMessages.serverMessages.ServerMessage;

public class notificationMessage extends ServerMessage {
    private final String message;

    public notificationMessage(String notificationMessage) {
        super(ServerMessageType.NOTIFICATION);
        this.message = notificationMessage;
    }

    public String getNotificationMessage() {
        return message;
    }
}
