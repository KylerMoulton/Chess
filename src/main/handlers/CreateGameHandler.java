package handlers;

import com.google.gson.Gson;
import request.CreateGameRequest;
import result.CreateGameResult;
import service.CreateGameService;
import spark.Request;
import spark.Response;

public class CreateGameHandler {
    private Gson gson;
    public Object handleRequest(Request req, Response res) throws Exception {
        CreateGameRequest request = gson.fromJson(req.body(), CreateGameRequest.class);
        CreateGameService service = new CreateGameService();
        CreateGameResult result = service.createGame(request);
        //set status
        res.status(200);
        res.status(400);
        res.status(401);
        res.status(500);
        return gson.toJson(result);
    }
}
