package websocket;

import chess.ChessGame;
import chess.ChessMove;
import chess.gameImple;
import com.google.gson.Gson;
import exception.ResponseException;
import model.GameModel;
import serverMessages.loadGame;
import webSocketMessages.serverMessages.ServerMessage;
import webSocketMessages.userCommands.UserGameCommand;

import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

//need to extend Endpoint for websocket to work properly
public class WebSocketFacade extends Endpoint {

    Session session;
    NotificationHandler notificationHandler;


    public WebSocketFacade(String url, NotificationHandler notificationHandler) throws ResponseException {
        try {
            url = url.replace("http", "ws");
            URI socketURI = new URI(url + "/connect");
            this.notificationHandler = notificationHandler;

            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            this.session = container.connectToServer(this, socketURI);

            //set message handler
            this.session.addMessageHandler(new MessageHandler.Whole<String>() {
                @Override
                public void onMessage(String message) {
                    NotificationHandler.notify(message);
                    //System.out.print(notification.getServerMessageType());
                }
//                @Override
//                public void onMessage(String message) {
//                    ChessGame gameNotification = gameImple.serialization().fromJson(message, ChessGame.class);
//                    ServerMessage notification = new Gson().fromJson(message, ServerMessage.class);
//                    if (notification.getServerMessageType() == ServerMessage.ServerMessageType.ERROR) {
//                        Error error = new Gson().fromJson(message, Error.class);
//                        notificationHandler.notify(notification, null, error);
//                    }
//                    notificationHandler.notify(notification, gameNotification, null);
//                    //System.out.print(notification.getServerMessageType());
//                }
            });
        } catch (DeploymentException | IOException | URISyntaxException ex) {
            throw new ResponseException(500, ex.getMessage());
        }
    }

    //Endpoint requires this method, but you don't have to do anything
    @Override
    public void onOpen(Session session, EndpointConfig endpointConfig) {
    }


    public void leave(Integer gameID, String authToken, ChessGame.TeamColor color) throws ResponseException {
        try {
            var action = new UserGameCommand(authToken, gameID, color, null, UserGameCommand.CommandType.LEAVE);
            this.session.getBasicRemote().sendText(new Gson().toJson(action));
        } catch (IOException ex) {
            throw new ResponseException(500, ex.getMessage());
        }
    }

    public void joinGame(Integer gameID, String authToken, ChessGame.TeamColor color) throws ResponseException {
        try {
            var action = new UserGameCommand(authToken, gameID, color, null, UserGameCommand.CommandType.JOIN_PLAYER);
            this.session.getBasicRemote().sendText(new Gson().toJson(action));
        } catch (IOException ex) {
            throw new ResponseException(500, ex.getMessage());
        }
    }

    public void observeGame(Integer gameID, String authToken, ChessGame.TeamColor color) throws ResponseException {
        try {
            var action = new UserGameCommand(authToken, gameID, color, null, UserGameCommand.CommandType.JOIN_OBSERVER);
            this.session.getBasicRemote().sendText(new Gson().toJson(action));
        } catch (IOException ex) {
            throw new ResponseException(500, ex.getMessage());
        }
    }

    public void makeMove(Integer gameID, String authToken, ChessMove move) throws ResponseException {
        try {
            var action = new UserGameCommand(authToken, gameID, null, move, UserGameCommand.CommandType.MAKE_MOVE);
            this.session.getBasicRemote().sendText(new Gson().toJson(action));
        } catch (IOException ex) {
            throw new ResponseException(500, ex.getMessage());
        }
    }

    public void resign(Integer gameID, String authToken, ChessMove move) throws ResponseException {
        try {
            var action = new UserGameCommand(authToken, gameID, null, move, UserGameCommand.CommandType.RESIGN);
            this.session.getBasicRemote().sendText(new Gson().toJson(action));
        } catch (IOException ex) {
            throw new ResponseException(500, ex.getMessage());
        }
    }

//    public void leavePetShop(String visitorName) throws ResponseException {
//        try {
//            var action = new Action(Action.Type.EXIT, visitorName);
//            this.session.getBasicRemote().sendText(new Gson().toJson(action));
//            this.session.close();
//        } catch (IOException ex) {
//            throw new ResponseException(500, ex.getMessage());
//        }
//    }

}

