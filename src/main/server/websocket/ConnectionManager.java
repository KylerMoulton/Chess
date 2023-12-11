package server.websocket;

import com.google.gson.Gson;
import org.eclipse.jetty.websocket.api.Session;
import webSocketMessages.serverMessages.ServerMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectionManager {
    public final ConcurrentHashMap<Integer, List<Connection>> connections = new ConcurrentHashMap<>();
    //set of gameID, authtoken,connection

    public void add(Integer gameID, String auth, Session session) {
        Connection connection = new Connection(auth, session);
        if (connections.containsKey(gameID)) {
            List<Connection> connectionList = connections.get(gameID);
            connectionList.add(connection);
            connections.put(gameID, connectionList);
        } else {
            List<Connection> connectionList = new ArrayList<>();
            connectionList.add(connection);
            connections.put(gameID, connectionList);
        }

    }

    public void remove(String visitorName) {
        connections.remove(visitorName);
    }

    public ConcurrentHashMap<Integer, List<Connection>> getConnections() {
        return connections;
    }

    //    public void broadcast(Integer gameID, String authToken, ServerMessage notification) throws IOException {
//        var removeList = new ArrayList<Connection>();
//        if (notification.getServerMessageType() == ServerMessage.ServerMessageType.LOAD_GAME || notification.getServerMessageType() == ServerMessage.ServerMessageType.ERROR) {
//            for (List<Connection> c : connections.values()) {
//                if (c.get(gameID).session.isOpen()) {
//                    if (c.get(gameID).session.isOpen()) {
//                        if (c.get(gameID).auth.equals(authToken)) {
//                            c.get(gameID).send(new Gson().toJson(notification));
//                        }
//                    }
//                } else {
//                    removeList.add(c.get(gameID));
//                }
//            }
//        } else if (notification.getServerMessageType() == ServerMessage.ServerMessageType.NOTIFICATION) {
//            for (var c : connections.values()) {
//                if (c.get(gameID).session.isOpen()) {
//                    if (c.get(gameID).session.isOpen()) {
//                        if (!c.get(gameID).auth.equals(authToken)) {
//                            c.get(gameID).send(new Gson().toJson(notification));
//                        }
//                    }
//                } else {
//                    removeList.add(c.get(gameID));
//                }
//            }
//        }
//    }
}
