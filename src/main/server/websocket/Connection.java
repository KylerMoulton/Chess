package server.websocket;

import org.eclipse.jetty.websocket.api.Session;

import java.io.IOException;

public class Connection {
    public String auth;
    public Session session;

    public Connection(String auth, Session session) {
        this.auth = auth;
        this.session = session;
    }

    public String getAuth() {
        return auth;
    }

    public Session getSession() {
        return session;
    }

    public void send(String msg) throws IOException {
        session.getRemote().sendString(msg);
        System.out.print(msg);
    }
}