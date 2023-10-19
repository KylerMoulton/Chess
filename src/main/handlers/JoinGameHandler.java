package handlers;

import com.google.gson.Gson;
import request.JoinGameRequest;
import result.JoinGameResult;
import service.JoinGameService;
import spark.Request;
import spark.Response;

public class JoinGameHandler {
    private Gson gson;
    public Object handleRequest(Request req, Response res) throws Exception {
        JoinGameRequest request = gson.fromJson(req.body(), JoinGameRequest.class);
        JoinGameService service = new JoinGameService();
        JoinGameResult result = service.joinGame(request);
        //set status
        res.status(200);
        res.status(400);
        res.status(401);
        res.status(403);
        res.status(500);
        return gson.toJson(result);
    }
}
