package server;

import handlers.*;
import spark.Spark;
public class Server {

    public static void main(String[] args) {
        new Server().run();
    }

    private void run() {
        // Specify the port you want the server to listen on
        Spark.port(8080);

        // Register a directory for hosting static files
        Spark.externalStaticFileLocation("C:\\Users\\kyler\\BYUSophomore\\FallSemester\\CS240\\Chess\\web");

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
                (new LogoutHandler()).handleRequest(
                        res));
        Spark.get("/game", (req, res) ->
                (new ListGamesHandler()).handleRequest(
                        res));
        Spark.post("/game", (req, res) ->
                (new CreateGameHandler()).handleRequest(req,
                        res));
        Spark.put("/game", (req, res) ->
                (new JoinGameHandler()).handleRequest(req,
                        res));
    }
}
