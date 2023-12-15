package server.websocket;

import org.eclipse.jetty.websocket.api.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectionManager {
    public final ConcurrentHashMap<Integer, List<Connection>> connections = new ConcurrentHashMap<>();
    //set of gameID, authtoken,connection

    public void add(Integer gameID, String auth, Session session) {
        Connection connection = new Connection(auth, session);
        List<Connection> connectionList;
        if (connections.containsKey(gameID)) {
            connectionList = connections.get(gameID);
        } else {
            connectionList = new ArrayList<>();
        }
        connectionList.add(connection);
        connections.put(gameID, connectionList);

    }

    public void remove(String authToken) {
        for (List<Connection> connections : connections.values()) {
            connections.removeIf(connection -> connection.getAuth().equals(authToken));
        }
    }

    public ConcurrentHashMap<Integer, List<Connection>> getConnections() {
        return connections;
    }

}
