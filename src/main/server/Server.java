package server;

import handlers.*;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import server.websocket.WebSocketHandler;
import spark.Spark;

@WebSocket
public class Server {

    public static void main(String[] args) {
        new Server().run();
    }

    private void run() {
        // Specify the port you want the server to listen on
        Spark.port(8080);

        // Register a directory for hosting static files
        Spark.externalStaticFileLocation("C:\\Users\\kyler\\BYUSophomore\\FallSemester\\CS240\\Chess\\web");
        Spark.webSocket("/connect", WebSocketHandler.class);

        // Register handlers for each endpoint using the method reference syntax
        Spark.delete("/db", (req, res) ->
                (new ClearHandler()).handleRequest(
                        res));
        Spark.post("/user", (req, res) ->
                (new RegisterHandler()).handleRequest(req,
                        res));
        Spark.post("/session", (req, res) ->
                (new LoginHandler()).handleRequest(req,
                        res));
        Spark.delete("/session", (req, res) ->
                (new LogoutHandler()).handleRequest(req,
                        res));
        Spark.get("/game", (req, res) ->
                (new ListGamesHandler()).handleRequest(req,
                        res));
        Spark.post("/game", (req, res) ->
                (new CreateGameHandler()).handleRequest(req,
                        res));
        Spark.put("/game", (req, res) ->
                (new JoinGameHandler()).handleRequest(req,
                        res));
        Spark.get("/echo/:msg", (req, res) -> "HTTP response: " + req.params("msg"));

    }
}
