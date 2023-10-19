package handlers;

import com.google.gson.Gson;
import result.ListGamesResult;
import service.ListGamesService;
import spark.Response;

public class ListGamesHandler {
    private Gson gson;
    public Object handleRequest(Response res) throws Exception {
        ListGamesService service = new ListGamesService();
        ListGamesResult result = service.listGames();
        //set status
        res.status(200);
        res.status(401);
        res.status(500);
        return gson.toJson(result);
    }
}
